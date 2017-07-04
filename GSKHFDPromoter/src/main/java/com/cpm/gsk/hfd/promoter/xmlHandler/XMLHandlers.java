package com.cpm.gsk.hfd.promoter.xmlHandler;

import com.cpm.gsk.hfd.promoter.gettersetter.CallsReportGetterSetter;
import com.cpm.gsk.hfd.promoter.gettersetter.FailureGetterSetter;
import com.cpm.gsk.hfd.promoter.gettersetter.HfdIncentiveGetterSetter;
import com.cpm.gsk.hfd.promoter.gettersetter.JourneyPlanGetterSetter;
import com.cpm.gsk.hfd.promoter.gettersetter.LoginGetterSetter;
import com.cpm.gsk.hfd.promoter.gettersetter.SalesReportGetterSetter;
import com.cpm.gsk.hfd.promoter.gettersetter.Skugettersetter;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;


public class XMLHandlers {

    // LOGIN XML HANDLER
    public static LoginGetterSetter loginXMLHandler(XmlPullParser xpp,
                                                    int eventType) {
        LoginGetterSetter lgs = new LoginGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {
                    if (xpp.getName().equals("RESULT")) {
                        lgs.setResult(xpp.nextText());
                    }
                    if (xpp.getName().equals("APP_VERSION")) {
                        lgs.setVERSION(xpp.nextText());
                    }
                    if (xpp.getName().equals("APP_PATH")) {
                        lgs.setPATH(xpp.nextText());
                    }
                    if (xpp.getName().equals("CURRENTDATE")) {
                        lgs.setDATE(xpp.nextText());
                    }

                    if (xpp.getName().equals("RIGHTNAME")) {
                        lgs.setRIGHTNAME(xpp.nextText());
                    }

                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return lgs;
    }
    // FAILURE XML HANDLER
    public static FailureGetterSetter failureXMLHandler(XmlPullParser xpp,
                                                        int eventType) {
        FailureGetterSetter failureGetterSetter = new FailureGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {
                    if (xpp.getName().equals("STATUS")) {
                        failureGetterSetter.setStatus(xpp.nextText());
                    }
                    if (xpp.getName().equals("ERRORMSG")) {
                        failureGetterSetter.setErrorMsg(xpp.nextText());
                    }

                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return failureGetterSetter;
    }

    // JCP XML HANDLER
    public static JourneyPlanGetterSetter JCPXMLHandler(XmlPullParser xpp, int eventType) {
        JourneyPlanGetterSetter jcpGetterSetter = new JourneyPlanGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {
                    if (xpp.getName().equals("META_DATA")) {
                        jcpGetterSetter.setTable_journey_plan(xpp.nextText());
                    }

                    if (xpp.getName().equals("STORE_ID")) {
                        jcpGetterSetter.setStore_cd(xpp.nextText());
                    }
                    if (xpp.getName().equals("EMP_ID")) {
                        jcpGetterSetter.setEmp_cd(xpp.nextText());
                    }
                    if (xpp.getName().equals("STORE")) {
                        jcpGetterSetter.setStore_name(xpp.nextText());
                    }
                    if (xpp.getName().equals("CITY")) {
                        jcpGetterSetter.setCity(xpp.nextText());
                    }
                    if (xpp.getName().equals("VISIT_DATE")) {
                        jcpGetterSetter.setVISIT_DATE(xpp.nextText());
                    }
                    if (xpp.getName().equals("PROCESS_ID")) {
                        jcpGetterSetter.setPROCESS_ID(xpp.nextText());
                    }
                    if (xpp.getName().equals("REGION_ID")) {
                        jcpGetterSetter.setREGION_ID(xpp.nextText());
                    }
                    if (xpp.getName().equals("KEY_ID")) {
                        jcpGetterSetter.setKEY_ID(xpp.nextText());
                    }
                    if (xpp.getName().equals("UPLOAD_STATUS")) {
                        jcpGetterSetter.setUploadStatus(xpp.nextText());
                    }

                    if (xpp.getName().equals("CHECKOUT_STATUS")) {
                        jcpGetterSetter.setCheckOutStatus(xpp.nextText());
                    }
                    if (xpp.getName().equals("STORETYPE_ID")) {
                        jcpGetterSetter.setSTORETYPE_CD(xpp.nextText());
                    }
                    if (xpp.getName().equals("PKD_ENABLE")) {
                        jcpGetterSetter.setPKD_ENABLE(xpp.nextText());
                    }

                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jcpGetterSetter;
    }

    public static Skugettersetter skuMasterXML(XmlPullParser xpp, int eventType) {
        Skugettersetter assetmaster = new Skugettersetter();
        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("META_DATA")) {
                        assetmaster.setSku_master_table(xpp.nextText());
                    }
                    if (xpp.getName().equals("SKU_ID")) {
                        assetmaster.setSku_id(xpp.nextText());
                    }
                    if (xpp.getName().equals("SKU")) {
                        assetmaster.setSku(xpp.nextText());
                    }

                    if (xpp.getName().equals("BRAND_ID")) {
                        assetmaster.setBrand_id(xpp.nextText());
                    }
                    if (xpp.getName().equals("BRAND")) {
                        assetmaster.setBrand(xpp.nextText());
                    }

                    if (xpp.getName().equals("CATEGORY_ID")) {
                        assetmaster.setCategory_id(xpp.nextText());
                    }
                    if (xpp.getName().equals("CATEGORY")) {
                        assetmaster.setCategory(xpp.nextText());
                    }

                    if (xpp.getName().equals("BRAND_SEQUENCE")) {
                        assetmaster.setBrand_sequence(xpp.nextText());
                    }

                    if (xpp.getName().equals("SKU_SEQUENCE")) {
                        assetmaster.setSku_sequence(xpp.nextText());
                    }


                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return assetmaster;
    }

    public static SalesReportGetterSetter SalesReportMaster(XmlPullParser xpp, int eventType) {
        SalesReportGetterSetter assetmaster = new SalesReportGetterSetter();
        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("META_DATA")) {
                        assetmaster.setSales_report_table(xpp.nextText());
                    }
                    if (xpp.getName().equals("BRAND")) {
                        assetmaster.setBrand(xpp.nextText());
                    }
                    if (xpp.getName().equals("TODAY_SALE")) {
                        assetmaster.setToday_sales(xpp.nextText());
                    }

                    if (xpp.getName().equals("MTD_SALE")) {
                        assetmaster.setMtd_sale(xpp.nextText());
                    }
                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return assetmaster;
    }

    public static CallsReportGetterSetter CallsReportMaster(XmlPullParser xpp,
                                                            int eventType) {
        CallsReportGetterSetter assetmaster = new CallsReportGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("META_DATA")) {
                        assetmaster.setCall_report_master_table(xpp.nextText());
                    }
                    if (xpp.getName().equals("TODAY_CALL")) {
                        assetmaster.setToday_call(xpp.nextText());
                    }
                    if (xpp.getName().equals("MTD_CALL")) {
                        assetmaster.setMtd_call(xpp.nextText());
                    }
                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return assetmaster;
    }

    public static HfdIncentiveGetterSetter IncentiveMaster(XmlPullParser xpp,
                                                             int eventType) {
        HfdIncentiveGetterSetter assetmaster = new HfdIncentiveGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("META_DATA")) {
                        assetmaster.setTable_incentive(xpp.nextText());
                    }
                    if (xpp.getName().equals("INCENTIVE")) {
                        assetmaster.setIncentive(xpp.nextText());
                    }
                  if (xpp.getName().equals("MONTH_NAME")) {
                        assetmaster.setMonth_name(xpp.nextText());
                    }
                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return assetmaster;
    }



}
