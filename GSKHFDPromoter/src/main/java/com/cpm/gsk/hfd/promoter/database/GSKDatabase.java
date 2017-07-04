package com.cpm.gsk.hfd.promoter.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.cpm.gsk.hfd.promoter.constant.CommonString;
import com.cpm.gsk.hfd.promoter.delegates.TableBean;
import com.cpm.gsk.hfd.promoter.gettersetter.CallsReportGetterSetter;
import com.cpm.gsk.hfd.promoter.gettersetter.CoverageBean;
import com.cpm.gsk.hfd.promoter.gettersetter.HfdIncentiveGetterSetter;
import com.cpm.gsk.hfd.promoter.gettersetter.JourneyPlanGetterSetter;
import com.cpm.gsk.hfd.promoter.gettersetter.SalesReportGetterSetter;
import com.cpm.gsk.hfd.promoter.gettersetter.Skugettersetter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jeevanp on 24-04-2017.
 */

public class GSKDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "GSK_RETAILER_DATABASE";
    public static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    public GSKDatabase(Context completeDownloadActivity) {
        super(completeDownloadActivity, DATABASE_NAME, null, DATABASE_VERSION);
    }// TODO Auto-generated constructor stub }

    public void open() {
        try {
            db = this.getWritableDatabase();
        } catch (Exception e) {
        }
    }

    public void close() {
        db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TableBean.getjcptable());
        sqLiteDatabase.execSQL(TableBean.getSkumastertable());
        sqLiteDatabase.execSQL(TableBean.getSalesreporttable());
        sqLiteDatabase.execSQL(TableBean.getCallreportmastertable());
        sqLiteDatabase.execSQL(TableBean.getIncentivemastertable());
        sqLiteDatabase.execSQL(CommonString.CREATE_TABLE_COVERAGE_DATA);
        sqLiteDatabase.execSQL(CommonString.CREATE_TABLE_SKU_SALES_DATA);
        sqLiteDatabase.execSQL(CommonString.CREATE_TABLE_SKU_BRAND_DATA);
    }

    public void deleteAllTable() {
        db.delete(CommonString.TABLE_SKU_SALES_DATA, null, null);
        db.delete(CommonString.TABLE_INSERT_SKU_BRAND_DATA, null, null);
    }

    public void deleteAllSpecificTable() {
        db.delete(CommonString.TABLE_COVERAGE_DATA, null, null);
        db.delete(CommonString.TABLE_SKU_SALES_DATA, null, null);
        db.delete(CommonString.TABLE_INSERT_SKU_BRAND_DATA, null, null);
        db.delete(CommonString.TABLE_HFD_SALE_REPORT, null, null);
        db.delete(CommonString.TABLE_HFD_SALE_REPORT, null, null);

    }

    public void deleteSpecificTable(String store_id) {
        db.delete(CommonString.TABLE_COVERAGE_DATA, CommonString.KEY_STORE_ID + "='" + store_id + "'", null);
        db.delete("STOCK_DATA", CommonString.KEY_STORE_ID + "='" + store_id + "'", null);
        db.delete("openingHeader_data", CommonString.KEY_STORE_CD + "='" + store_id + "'", null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldversion, int newversion) {
        onCreate(sqLiteDatabase);
    }

    //JCP data

    public void insertJCPData(JourneyPlanGetterSetter data) {
        long l = 0;
        db.delete("JOURNEY_PLAN", null, null);
        ContentValues values = new ContentValues();
        try {
            for (int i = 0; i < data.getStore_cd().size(); i++) {
                values.put("STORE_ID", Integer.parseInt(data.getStore_cd().get(i)));
                values.put("EMP_ID", Integer.parseInt(data.getEmp_cd().get(i)));
                values.put("VISIT_DATE", data.getVISIT_DATE().get(i));
                values.put("STORE", data.getStore_name().get(i));
                values.put("CITY", data.getCity().get(i));
                values.put("UPLOAD_STATUS", data.getUploadStatus().get(i));
                values.put("CHECKOUT_STATUS", data.getCheckOutStatus().get(i));
                values.put("STORETYPE_ID", data.getSTORETYPE_CD().get(i));
                values.put("PROCESS_ID", data.getPROCESS_ID().get(i));
                values.put("REGION_ID", data.getREGION_ID().get(i));
                values.put("KEY_ID", data.getKEY_ID().get(i));
                values.put("PKD_ENABLE", data.getPKD_ENABLE().get(i));

                l = db.insert("JOURNEY_PLAN", null, values);
            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert JCP Data ",
                    ex.toString());
        }


    }

    //get JCP Data
    public ArrayList<JourneyPlanGetterSetter> getJCPData(String date) {
        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<JourneyPlanGetterSetter> list = new ArrayList<JourneyPlanGetterSetter>();
        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT * from JOURNEY_PLAN where VISIT_DATE = '" + date + "'"
                    , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    JourneyPlanGetterSetter sb = new JourneyPlanGetterSetter();
                    sb.setStore_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow("STORE_ID")));
                    sb.setEmp_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow("EMP_ID")));
                    sb.setVISIT_DATE((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("VISIT_DATE"))));
                    sb.setStore_name((dbcursor.getString(dbcursor.getColumnIndexOrThrow("STORE"))));
                    sb.setCity((dbcursor.getString(dbcursor.getColumnIndexOrThrow("CITY"))));
                    sb.setUploadStatus((dbcursor.getString(dbcursor.getColumnIndexOrThrow("UPLOAD_STATUS"))));
                    sb.setCheckOutStatus((dbcursor.getString(dbcursor.getColumnIndexOrThrow("CHECKOUT_STATUS"))));
                    sb.setSTORETYPE_CD((dbcursor.getString(dbcursor.getColumnIndexOrThrow("STORETYPE_ID"))));
                    sb.setPROCESS_ID(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PROCESS_ID")));
                    sb.setREGION_ID(dbcursor.getString(dbcursor.getColumnIndexOrThrow("REGION_ID")));
                    sb.setKEY_ID((dbcursor.getString(dbcursor.getColumnIndexOrThrow("KEY_ID"))));
                    sb.setPKD_ENABLE((dbcursor.getString(dbcursor.getColumnIndexOrThrow("PKD_ENABLE"))));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching JCP!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingJCP data---------------------->Stop<-----------",
                "-------------------");
        return list;

    }

    //sku data

    public void insertSkuMasterData(Skugettersetter data) {
        long l = 0;
        db.delete("HFD_DETAILER_SKU", null, null);
        ContentValues values = new ContentValues();
        try {
            for (int i = 0; i < data.getBrand_id().size(); i++) {
                values.put("SKU_ID", Integer.parseInt(data.getSku_id().get(i)));
                values.put("BRAND_ID", Integer.parseInt(data.getBrand_id().get(i)));
                values.put("CATEGORY_ID", data.getCategory_id().get(i));
                values.put("SKU", data.getSku().get(i));
                values.put("BRAND", data.getBrand().get(i));
                values.put("CATEGORY", data.getCategory().get(i));
                values.put("BRAND_SEQUENCE", data.getBrand_sequence().get(i));
                values.put("SKU_SEQUENCE", data.getSku_sequence().get(i));
                l = db.insert("HFD_DETAILER_SKU", null, values);


            }
        } catch (Exception ex) {
            Log.d("Database Exception while Insert JCP Data ",
                    ex.toString());
        }
    }


    //HFD_SALE_REPORT data

    public void insertSalesReportMasterData(SalesReportGetterSetter data) {
        long l = 0;
        db.delete("HFD_SALE_REPORT", null, null);
        ContentValues values = new ContentValues();
        try {
            for (int i = 0; i < data.getBrand().size(); i++) {
                values.put("TODAY_SALE", data.getToday_sales().get(i));
                values.put("MTD_SALE", data.getMtd_sale().get(i));
                values.put("BRAND", data.getBrand().get(i));
                l = db.insert("HFD_SALE_REPORT", null, values);
            }
        } catch (Exception ex) {
            Log.d("Database Exception while Insert JCP Data ", ex.toString());
        }
    }
    //HFD_SALE_REPORT data
    public void insertIncentiveMasterData(HfdIncentiveGetterSetter data) {
        long l = 0;
        db.delete("HFD_INCENTIVE", null, null);
        ContentValues values = new ContentValues();
        try {
            for (int i = 0; i < data.getIncentive().size(); i++) {
                values.put("MONTH_NAME", data.getMonth_name().get(i));
                values.put("INCENTIVE", data.getIncentive().get(i));
                l = db.insert("HFD_INCENTIVE", null, values);
            }
        } catch (Exception ex) {
            Log.d("Database Exception while Insert JCP Data ",
                    ex.toString());
        }
    }

    //get HFD_INCENTIVE Master Data
    public ArrayList<HfdIncentiveGetterSetter> getIncentiveMasterData() {
        Log.d("FetchingStoredata--------------->Start<------------", "------------------");
        ArrayList<HfdIncentiveGetterSetter> list = new ArrayList<HfdIncentiveGetterSetter>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT * from HFD_INCENTIVE ", null, null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    HfdIncentiveGetterSetter sb = new HfdIncentiveGetterSetter();
                    sb.setMonth_name(dbcursor.getString(dbcursor.getColumnIndexOrThrow("MONTH_NAME")));
                    sb.setIncentive((dbcursor.getString(dbcursor.getColumnIndexOrThrow("INCENTIVE"))));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching JCP!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingJCP data---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    //get HFD_SALE_REPORT Master Data
    public ArrayList<SalesReportGetterSetter> getSalesReportMasterData() {
        Log.d("FetchingStoredata--------------->Start<------------", "------------------");
        ArrayList<SalesReportGetterSetter> list = new ArrayList<SalesReportGetterSetter>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT * from HFD_SALE_REPORT ", null, null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    SalesReportGetterSetter sb = new SalesReportGetterSetter();
                    sb.setToday_sales(dbcursor.getString(dbcursor.getColumnIndexOrThrow("TODAY_SALE")));
                    sb.setMtd_sale((dbcursor.getString(dbcursor.getColumnIndexOrThrow("MTD_SALE"))));
                    sb.setBrand((dbcursor.getString(dbcursor.getColumnIndexOrThrow("BRAND"))));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching JCP!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingJCP data---------------------->Stop<-----------",
                "-------------------");
        return list;

    }

    //HFD_CALL_REPORT data

    public void insertCallsReportMasterData(CallsReportGetterSetter data) {
        long l = 0;
        db.delete("HFD_CALL_REPORT", null, null);
        ContentValues values = new ContentValues();
        try {
                values.put("TODAY_CALL", data.getToday_call());
                values.put("MTD_CALL", data.getMtd_call());

                l = db.insert("HFD_CALL_REPORT", null, values);

        } catch (Exception ex) {
            Log.d("Database Exception while Insert JCP Data ",
                    ex.toString());
        }
    }



    //get HFD_CALL_REPORT Master Data
    public CallsReportGetterSetter getCallsReportMasterData() {
        Log.d("FetchingStoredata--------------->Start<------------", "------------------");
        CallsReportGetterSetter callsReportGetterSetter = new CallsReportGetterSetter();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT * from HFD_CALL_REPORT ", null, null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    callsReportGetterSetter.setToday_call(dbcursor.getString(dbcursor.getColumnIndexOrThrow("TODAY_CALL")));
                    callsReportGetterSetter.setMtd_call((dbcursor.getString(dbcursor.getColumnIndexOrThrow("MTD_CALL"))));
                    dbcursor.moveToNext();
                }
                dbcursor.close();

            }

        } catch (Exception e) {
            Log.d("Exception when fetching JCP!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());

        }

        Log.d("FetchingJCP data---------------------->Stop<-----------",
                "-------------------");
        return callsReportGetterSetter;

    }



    //get sku Master Data
    public ArrayList<Skugettersetter> getSkuMasterData() {
        Log.d("FetchingStoredata--------------->Start<------------", "------------------");
        ArrayList<Skugettersetter> list = new ArrayList<Skugettersetter>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT * from HFD_DETAILER_SKU ", null, null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    Skugettersetter sb = new Skugettersetter();
                    sb.setSku_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("SKU_ID")));
                    sb.setBrand_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("BRAND_ID")));
                    sb.setCategory_id((dbcursor.getString(dbcursor.getColumnIndexOrThrow("CATEGORY_ID"))));
                    sb.setSku((dbcursor.getString(dbcursor.getColumnIndexOrThrow("SKU"))));
                    sb.setBrand((dbcursor.getString(dbcursor.getColumnIndexOrThrow("BRAND"))));
                    sb.setCategory((dbcursor.getString(dbcursor.getColumnIndexOrThrow("CATEGORY"))));
                    sb.setBrand_sequence((dbcursor.getString(dbcursor.getColumnIndexOrThrow("BRAND_SEQUENCE"))));
                    sb.setSku_sequence((dbcursor.getString(dbcursor.getColumnIndexOrThrow("SKU_SEQUENCE"))));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching JCP!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingJCP data---------------------->Stop<-----------",
                "-------------------");
        return list;

    }

    public long InsertCoverageData(CoverageBean data) {
        ContentValues values = new ContentValues();
        try {
            values.put(CommonString.KEY_STORE_ID, data.getStore_id());
            values.put(CommonString.KEY_VISIT_DATE, data.getVisit_date());
            values.put(CommonString.KEY_USER_ID, data.getUser_id());
            values.put(CommonString.KEY_IN_TIME, data.getIntime());
            values.put(CommonString.KEY_OUT_TIME, data.getOut_time());
            values.put(CommonString.KEY_LATITUDE, data.getLatitute());
            values.put(CommonString.KEY_LONGITUDE, data.getLongitute());
            values.put(CommonString.KEY_COVERAGE_STATUS, data.getStatus());
            values.put(CommonString.KEY_PROCESS_ID, data.getProcess_id());
            values.put(CommonString.KEY_EMP_ID, data.getEmp_id());
            values.put(CommonString.KEY_PACK_ENABLE, data.getPakd_enable());

            return db.insert(CommonString.TABLE_COVERAGE_DATA, null, values);

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Closes Data ",
                    ex.toString());
        }
        return 0;
    }


    public void deleteCoverageData() {
        db.delete(CommonString.TABLE_COVERAGE_DATA, null, null);
    }

    public void updateCoverageOutTime(String store_id, String out_time) {

        try {
            ContentValues values = new ContentValues();
            values.put(CommonString.KEY_OUT_TIME, out_time);

            db.update(CommonString.TABLE_COVERAGE_DATA, values, CommonString.KEY_STORE_ID + "=" + store_id, null);
        } catch (Exception e) {

        }
    }

    public void updateStoreStatusOnLeave(String storeid, String visitdate, String status) {
        try {
            ContentValues values = new ContentValues();
            values.put("UPLOAD_STATUS", status);
            db.update("JOURNEY_PLAN", values, CommonString.KEY_STORE_ID + "='" + storeid + "' AND " + CommonString.KEY_VISIT_DATE + "='" + visitdate + "'", null);
        } catch (Exception e) {

        }
    }


    // getCoverageData
    public ArrayList<CoverageBean> getCoverageData(String visitdate) {
        ArrayList<CoverageBean> list = new ArrayList<CoverageBean>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT  * from " + CommonString.TABLE_COVERAGE_DATA + " where "
                    + CommonString.KEY_VISIT_DATE + "='" + visitdate + "'", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    CoverageBean sb = new CoverageBean();
                    sb.setStore_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
                    sb.setUser_id((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_USER_ID))));
                    sb.setIntime(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_IN_TIME)))));
                    sb.setOut_time(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_OUT_TIME)))));
                    sb.setVisit_date((((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_VISIT_DATE))))));
                    sb.setLatitute(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_LATITUDE)))));
                    sb.setLongitute(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_LONGITUDE)))));
                    sb.setStatus((((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_COVERAGE_STATUS))))));
                    sb.setProcess_id((((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_PROCESS_ID))))));
                    sb.setEmp_id((((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_EMP_ID))))));
                    sb.setPakd_enable((((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_PACK_ENABLE))))));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Coverage Data!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());

        }

        return list;

    }

    // getCoverageData
    public ArrayList<CoverageBean> getCoverageDataWithoutDate() {
        ArrayList<CoverageBean> list = new ArrayList<CoverageBean>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT  * from " + CommonString.TABLE_COVERAGE_DATA, null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    CoverageBean sb = new CoverageBean();
                    sb.setStore_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
                    sb.setUser_id((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_USER_ID))));
                    sb.setIntime(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_IN_TIME)))));
                    sb.setOut_time(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_OUT_TIME)))));
                    sb.setVisit_date((((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_VISIT_DATE))))));
                    sb.setLatitute(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_LATITUDE)))));
                    sb.setLongitute(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_LONGITUDE)))));
                    sb.setStatus((((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_COVERAGE_STATUS))))));
                    sb.setProcess_id((((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_PROCESS_ID))))));
                    sb.setEmp_id((((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_EMP_ID))))));
                    sb.setPakd_enable((((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_PACK_ENABLE))))));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Coverage Data!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());

        }

        return list;

    }

    //check if table is empty
    public boolean isCoverageDataFilled(String visit_date) {
        boolean filled = false;

        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT * FROM COVERAGE_DATA " + "where "
                    + CommonString.KEY_VISIT_DATE + "<>'" + visit_date + "'", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                int icount = dbcursor.getInt(0);
                dbcursor.close();
                if (icount > 0) {
                    filled = true;
                } else {
                    filled = false;
                }

            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return filled;
        }

        return filled;
    }
    //get Brand Data
    public ArrayList<Skugettersetter> getBrandData(String category_id) {
        Log.d("FetchingStoredata--------------->Start<------------", "------------------");
        ArrayList<Skugettersetter> list = new ArrayList<Skugettersetter>();
        Cursor dbcursor = null;
        try {
            // dbcursor = db.rawQuery("SELECT DISTINCT BRAND , BRAND_CD from Mapping_PromotionPost_data " + " WHERE STORE_CD =" + Store_id + " ORDER BY BRAND_SEQUENCE ASC", null);

            dbcursor = db.rawQuery("SELECT DISTINCT BRAND_ID,BRAND from HFD_DETAILER_SKU " + " WHERE CATEGORY_ID =" + category_id + " ORDER BY BRAND_SEQUENCE ASC", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    Skugettersetter sb = new Skugettersetter();
                    sb.setBrand_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("BRAND_ID")));
                    sb.setBrand((dbcursor.getString(dbcursor.getColumnIndexOrThrow("BRAND"))));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching JCP!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingJCP data---------------------->Stop<-----------",
                "-------------------");
        return list;

    }

    //get Brand Data
    public ArrayList<Skugettersetter> getSkuDataByBrand(String brand_id) {
        Log.d("FetchingStoredata--------------->Start<------------", "------------------");
        ArrayList<Skugettersetter> list = new ArrayList<Skugettersetter>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT * from HFD_DETAILER_SKU WHERE BRAND_ID= '" + brand_id + "' ORDER BY SKU_SEQUENCE ASC ", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    Skugettersetter sb = new Skugettersetter();
                    sb.setSku_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("SKU_ID")));
                    sb.setSku((dbcursor.getString(dbcursor.getColumnIndexOrThrow("SKU"))));
                    sb.setQuantity("");
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching JCP!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingJCP data---------------------->Stop<-----------",
                "-------------------");
        return list;

    }

    //get sku Master Data
    public ArrayList<Skugettersetter> getCategoryIdData() {
        Log.d("FetchingStoredata--------------->Start<------------", "------------------");
        ArrayList<Skugettersetter> list = new ArrayList<Skugettersetter>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT DISTINCT CATEGORY_ID,CATEGORY from HFD_DETAILER_SKU ", null, null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    Skugettersetter sb = new Skugettersetter();
                    sb.setCategory_id((dbcursor.getString(dbcursor.getColumnIndexOrThrow("CATEGORY_ID"))));
                    sb.setCategory((dbcursor.getString(dbcursor.getColumnIndexOrThrow("CATEGORY"))));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching JCP!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingJCP data---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public void InsertSkulistData(String storeid,
                                  HashMap<Skugettersetter, List<Skugettersetter>> data, List<Skugettersetter> save_listDataHeader) {
        long l1;
        ContentValues values = new ContentValues();
        ContentValues values1 = new ContentValues();

        try {

            db.beginTransaction();
            for (int i = 0; i < save_listDataHeader.size(); i++) {
                values.put(CommonString.KEY_STORE_ID, storeid);
                values.put(CommonString.KEY_BRAND_ID, save_listDataHeader.get(i).getBrand_id().get(0));
                values.put(CommonString.KEY_BRAND, save_listDataHeader.get(i).getBrand().get(0));
                long l = db.insert(CommonString.TABLE_INSERT_SKU_BRAND_DATA, null, values);

                for (int j = 0; j < data.get(save_listDataHeader.get(i)).size(); j++) {
                    String val=data.get(save_listDataHeader.get(i)).get(j).getQuantity();
                    if (val.equalsIgnoreCase("")){
                        values1.put(CommonString.KEY_QUANTITY, "0");
                    }else {
                        values1.put(CommonString.KEY_QUANTITY, val);
                    }
                    values1.put(CommonString.KEY_COMMONID, l);
                    values1.put(CommonString.KEY_STORE_ID, storeid);
                    values1.put(CommonString.SKU, data.get(save_listDataHeader.get(i)).get(j).getSku().get(0));
                    values1.put(CommonString.KEY_SKU_ID, data.get(save_listDataHeader.get(i)).get(j).getSku_id().get(0));
                    values1.put(CommonString.KEY_BRAND_ID, save_listDataHeader.get(i).getBrand_id().get(0));
                    values1.put(CommonString.KEY_BRAND, save_listDataHeader.get(i).getBrand().get(0));
                    l1 = db.insert(CommonString.TABLE_SKU_SALES_DATA, null, values1);
                }
            }
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (Exception ex) {
            Log.d("Database Exception while Insert Posm Master Data ", ex.toString());
        }

    }

    //get sku Master Data
    public ArrayList<Skugettersetter> getSkuInsertedAllData(String store_id) {
        Log.d("FetchingStoredata--------------->Start<------------", "------------------");
        ArrayList<Skugettersetter> list = new ArrayList<Skugettersetter>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT SKU_ID, SUM(QUANTITY) as SUM from SKU_SALES_DATA WHERE " + CommonString.KEY_STORE_ID + "='" + store_id + "'GROUP BY SKU_ID HAVING SUM(QUANTITY)>0", null);
           // select SKU_ID, sum(QUANTITY)as SUM from SKU_SALES_DATA WHERE STORE_ID = 3608 GROUP BY SKU_ID HAVING  sum(QUANTITY)>0
            //dbcursor = db.rawQuery("SELECT * from SKU_SALES_DATA WHERE " + CommonString.KEY_STORE_ID + "='" + store_id + "'", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    Skugettersetter sb = new Skugettersetter();
                  //  sb.setStore_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
                    sb.setSku_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_SKU_ID)));
                   // sb.setBrand_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_BRAND_ID)));
                   // sb.setSku((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.SKU))));
                   // sb.setBrand((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_BRAND))));
                    sb.setQuantity((dbcursor.getString(dbcursor.getColumnIndexOrThrow("SUM"))));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching JCP!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        int d = Log.d("FetchingJCP data---------------------->Stop<-----------",
                "-------------------");
        return list;

    }
}

