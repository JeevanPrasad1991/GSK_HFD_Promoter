package com.cpm.gsk.hfd.promoter.upload;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cpm.gsk.hfd.promoter.DashBoardActivity;
import com.cpm.gsk.hfd.promoter.R;
import com.cpm.gsk.hfd.promoter.constant.CommonString;
import com.cpm.gsk.hfd.promoter.database.GSKDatabase;
import com.cpm.gsk.hfd.promoter.gettersetter.CoverageBean;
import com.cpm.gsk.hfd.promoter.gettersetter.Skugettersetter;
import com.cpm.gsk.hfd.promoter.message.AlertMessage;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * Created by jeevanp on 08-05-2017.
 */

public class PreviousDataUploadActivity extends AppCompatActivity {
    private Dialog dialog;
    private ProgressBar pb;
    private TextView percentage, message;
    String app_ver;
    private String visit_date, username;
    private SharedPreferences preferences;
    private GSKDatabase database;
    private int factor, k;
    String datacheck = "", calls_cycle, sales_cycle;
    String[] words;
    String validity;
    Data data;
    int mid;
    boolean up_success_flag = true;
    private ArrayList<CoverageBean> coverageBeanlist = new ArrayList<CoverageBean>();
    private ArrayList<Skugettersetter> skuList = new ArrayList<>();
    private SharedPreferences.Editor editor = null;
    Button dwn_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbrd);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        dwn_btn = (Button) findViewById(R.id.dwn_btn);
        visit_date = preferences.getString(CommonString.KEY_DATE, null);
        username = preferences.getString(CommonString.KEY_USERNAME, null);
        calls_cycle = preferences.getString(CommonString.KEY_CYCLE_COUNT, null);
        sales_cycle = preferences.getString(CommonString.KEY_SALES_COUNT, null);
        app_ver = preferences.getString(CommonString.KEY_VERSION, "");
        database = new GSKDatabase(this);
        database.open();
        coverageBeanlist = database.getCoverageDataWithoutDate();
        if (coverageBeanlist != null) {
            visit_date = coverageBeanlist.get(0).getVisit_date();
        }
        new UploadTask(this).execute();
    }

    private class UploadTask extends AsyncTask<Void, Data, String> {
        private Context context;

        UploadTask(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            dialog = new Dialog(context);
            dialog.setContentView(R.layout.custom_upload);
            dialog.setTitle("Uploading Data");
            dialog.setCancelable(false);
            dialog.show();
            pb = (ProgressBar) dialog.findViewById(R.id.progressBar1);
            percentage = (TextView) dialog.findViewById(R.id.percentage);
            message = (TextView) dialog.findViewById(R.id.message);
        }

        @Override
        protected String doInBackground(Void... params) {
            // TODO Auto-generated method stub

            try {
                data = new Data();
                data.value = 10;
                data.name = "Uploading";
                publishProgress(data);
                coverageBeanlist = database.getCoverageData(visit_date);
                if (coverageBeanlist.size() > 0) {
                    for (int i = 0; i < coverageBeanlist.size(); i++) {
                        if (!coverageBeanlist.get(i).getStatus().equalsIgnoreCase(CommonString.KEY_U)) {
                            String onXML = "[DATA][USER_DATA]"
                                    + "[STORE_ID]"
                                    + coverageBeanlist.get(i).getStore_id()
                                    + "[/STORE_ID]"
                                    + "[VISIT_DATE]"
                                    + coverageBeanlist.get(i).getVisit_date()
                                    + "[/VISIT_DATE]"
                                    + "[LATITUDE]"
                                    + coverageBeanlist.get(i).getLatitute()
                                    + "[/LATITUDE][APP_VERSION]"
                                    + app_ver
                                    + "[/APP_VERSION][LONGITUDE]"
                                    + coverageBeanlist.get(i).getLongitute()
                                    + "[/LONGITUDE]"
                                    + "[IN_TIME]"
                                    + coverageBeanlist.get(i).getIntime()
                                    + "[/IN_TIME]"
                                    + "[OUT_TIME]"
                                    + coverageBeanlist.get(i).getOut_time()
                                    + "[/OUT_TIME]"
                                    + "[UPLOAD_STATUS]"
                                    + "N"
                                    + "[/UPLOAD_STATUS]"
                                    + "[USER_ID]"
                                    + username
                                    + "[/USER_ID]"
                                    + "[PROCESS_ID]"
                                    + coverageBeanlist.get(i).getProcess_id()
                                    + "[/PROCESS_ID]"
                                    + "[CREATED_BY]"
                                    + username
                                    + "[/CREATED_BY]"
                                    + "[REASON_REMARK]"
                                    + "0"
                                    + "[/REASON_REMARK]"
                                    + "[REASON_ID]"
                                    + "0"
                                    + "[/REASON_ID]"
                                    + "[SUB_REASON_ID]"
                                    + "0"
                                    + "[/SUB_REASON_ID][IMAGE_ALLOW]"
                                    + "0"
                                    + "[/IMAGE_ALLOW][STORE_IMAGE]"
                                    + "0"
                                    + "[/STORE_IMAGE]"
                                    + "[/USER_DATA][/DATA]";

                            SoapObject request = new SoapObject(CommonString.NAMESPACE, CommonString.METHOD_UPLOAD_DR_STORE_COVERAGE);
                            request.addProperty("onXML", onXML);
                            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                            envelope.dotNet = true;
                            envelope.setOutputSoapObject(request);
                            HttpTransportSE androidHttpTransport = new HttpTransportSE(CommonString.URL);
                            androidHttpTransport.call(CommonString.SOAP_ACTION + CommonString.METHOD_UPLOAD_DR_STORE_COVERAGE, envelope);
                            Object result = (Object) envelope.getResponse();
                            datacheck = result.toString();
                            datacheck = datacheck.replace("\"", "");
                            words = datacheck.split("\\;");
                            validity = (words[0]);
                            if (validity.equalsIgnoreCase(CommonString.KEY_SUCCESS)) {
                                mid = Integer.parseInt((words[1]));
                            } else {
                                if (!result.toString().equalsIgnoreCase(CommonString.KEY_SUCCESS)) {
                                    // continue;
                                    return CommonString.METHOD_UPLOAD_DR_STORE_COVERAGE;
                                }
                            }
                            data.value = 30;
                            data.name = "Uploading";
                            publishProgress(data);
                            String final_xml = "";

                            // sku data
                            final_xml = "";
                            onXML = "";
                            skuList = database.getSkuInsertedAllData(coverageBeanlist.get(i).getStore_id());
                            if (skuList.size() > 0) {
                                for (int j = 0; j < skuList.size(); j++) {
                                    onXML = "[HFD_SALES_DATA][MID]"
                                            + mid
                                            + "[/MID]"
                                            + "[CREATED_BY]"
                                            + username
                                            + "[/CREATED_BY]"
                                            + "[QUANTITY]"
                                            + skuList.get(j).getQuantity()
                                            + "[/QUANTITY]"
                                            + "[SKU_ID]"
                                            + skuList.get(j).getSku_id().get(0)
                                            + "[/SKU_ID]"
                                            + "[/HFD_SALES_DATA]";

                                    final_xml = final_xml + onXML;
                                }
                                final String sos_xml = "[DATA]" + final_xml + "[/DATA]";
                                request = new SoapObject(CommonString.NAMESPACE, CommonString.METHOD_UPLOAD_XML);
                                request.addProperty("XMLDATA", sos_xml);
                                request.addProperty("KEYS", "HFD_SALES_DATA");
                                request.addProperty("USERNAME", username);
                                request.addProperty("MID", mid);
                                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                                envelope.dotNet = true;
                                envelope.setOutputSoapObject(request);
                                androidHttpTransport = new HttpTransportSE(CommonString.URL);
                                androidHttpTransport.call(CommonString.SOAP_ACTION + CommonString.METHOD_UPLOAD_XML, envelope);
                                result = (Object) envelope.getResponse();
                                if (!result.toString().equalsIgnoreCase(CommonString.KEY_SUCCESS)) {
                                    return "HFD_SALES_DATA";
                                }
                                if (result.toString().equalsIgnoreCase(CommonString.KEY_NO_DATA)) {
                                    return CommonString.METHOD_UPLOAD_XML;
                                }
                                if (result.toString().equalsIgnoreCase(CommonString.KEY_FAILURE)) {
                                    return CommonString.METHOD_UPLOAD_XML;
                                }

                                data.value = 60;
                                data.name = "HFD_SALES_DATA";
                                publishProgress(data);
                            }

                            // calls cycle data
                            final_xml = "";
                            onXML = "";
                            int value1 = Integer.parseInt(calls_cycle);
                            if (value1 > 0) {
                                onXML = "[HFD_CALLS_DATA][MID]"
                                        + mid
                                        + "[/MID]"
                                        + "[CREATED_BY]"
                                        + username
                                        + "[/CREATED_BY]"
                                        + "[CALLS_CYCLE]"
                                        + calls_cycle
                                        + "[/CALLS_CYCLE]"
                                        + "[/HFD_CALLS_DATA]";

                                final_xml = final_xml + onXML;
                                final String sos_xml1 = "[DATA]" + final_xml + "[/DATA]";
                                request = new SoapObject(CommonString.NAMESPACE, CommonString.METHOD_UPLOAD_XML);
                                request.addProperty("XMLDATA", sos_xml1);
                                request.addProperty("KEYS", "HFD_CALLS_DATA");
                                request.addProperty("USERNAME", username);
                                request.addProperty("MID", mid);
                                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                                envelope.dotNet = true;
                                envelope.setOutputSoapObject(request);
                                androidHttpTransport = new HttpTransportSE(CommonString.URL);
                                androidHttpTransport.call(CommonString.SOAP_ACTION + CommonString.METHOD_UPLOAD_XML, envelope);
                                result = (Object) envelope.getResponse();
                                if (!result.toString().equalsIgnoreCase(CommonString.KEY_SUCCESS)) {
                                    if (result.toString().equalsIgnoreCase(CommonString.KEY_NO_DATA)) {
                                        return CommonString.METHOD_UPLOAD_XML;
                                    }
                                    if (result.toString().equalsIgnoreCase(CommonString.KEY_FAILURE)) {
                                        return CommonString.METHOD_UPLOAD_XML;
                                    }
                                    return "HFD_CALLS_DATA";
                                }
                                data.value = 90;
                                data.name = "HFD_CALLS_DATA";
                                publishProgress(data);

                            }

//////////////////////////////////////

                            // SET COVERAGE STATUS

                            final_xml = "";
                            onXML = "";
                            onXML = "[USER_DATA][STORE_ID]"
                                    + coverageBeanlist.get(i).getStore_id()
                                    + "[/STORE_ID]"
                                    + "[VISIT_DATE]"
                                    + coverageBeanlist.get(i).getVisit_date()
                                    + "[/VISIT_DATE]"
                                    + "[STATUS]"
                                    + CommonString.KEY_U
                                    + "[/STATUS]"
                                    + "[CREATED_BY]"
                                    + username
                                    + "[/CREATED_BY]"
                                    + "[/USER_DATA]";

                            final_xml = final_xml + onXML;
                            String sos_xml2 = "[DATA]" + final_xml + "[/DATA]";
                            SoapObject request1 = new SoapObject(CommonString.NAMESPACE, CommonString.MEHTOD_UPLOAD_COVERAGE_STATUS);
                            request1.addProperty("onXML", sos_xml2);
                            SoapSerializationEnvelope envelope1 = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                            envelope1.dotNet = true;
                            envelope1.setOutputSoapObject(request1);
                            HttpTransportSE androidHttpTransport1 = new HttpTransportSE(CommonString.URL);
                            androidHttpTransport1.call(CommonString.SOAP_ACTION + CommonString.MEHTOD_UPLOAD_COVERAGE_STATUS, envelope1);
                            Object result1 = (Object) envelope1.getResponse();
                            if (result1.toString().equalsIgnoreCase(CommonString.KEY_SUCCESS)) {
                                database.open();
                                database.updateStoreStatusOnLeave(coverageBeanlist.get(i).getStore_id(), coverageBeanlist.get(i).getVisit_date(), CommonString.KEY_U);
                            }
                            if (!result1.toString().equalsIgnoreCase(CommonString.KEY_SUCCESS)) {
                                return "COVERAGE_STATUS";
                            }
                            if (result1.toString().equalsIgnoreCase(CommonString.KEY_NO_DATA)) {
                                return CommonString.MEHTOD_UPLOAD_COVERAGE_STATUS;
                            }
                            if (result1.toString().equalsIgnoreCase(CommonString.KEY_FAILURE)) {
                                return CommonString.MEHTOD_UPLOAD_COVERAGE_STATUS;
                            }
                            data.value = 100;
                            data.name = "COVERAGE_STATUS";
                            publishProgress(data);


                        }

                    }
                }


            } catch (MalformedURLException e) {
                up_success_flag = false;
                final AlertMessage message = new AlertMessage(
                        PreviousDataUploadActivity.this,
                        AlertMessage.MESSAGE_EXCEPTION, "download", e);
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        message.showMessage();
                    }
                });

            } catch (IOException e) {
                up_success_flag = false;
                final AlertMessage message = new AlertMessage(PreviousDataUploadActivity.this,
                        AlertMessage.MESSAGE_SOCKETEXCEPTION, "socket", e);

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        message.showMessage();

                    }
                });
            } catch (Exception e) {
                up_success_flag = false;
                final AlertMessage message = new AlertMessage(PreviousDataUploadActivity.this,
                        AlertMessage.MESSAGE_EXCEPTION, "download", e);

                e.getMessage();
                e.printStackTrace();
                e.getCause();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        message.showMessage();
                    }
                });
            }
            if (up_success_flag == true) {
                return CommonString.KEY_SUCCESS;
            } else {
                return CommonString.KEY_FAILURE;
            }
        }

        @Override
        protected void onProgressUpdate(Data... values) {
            // TODO Auto-generated method stub
            pb.setProgress(values[0].value);
            percentage.setText(values[0].value + "%");
            message.setText(values[0].name);
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            dialog.dismiss();
            if (result.equalsIgnoreCase(CommonString.KEY_SUCCESS)) {
                editor.remove(CommonString.KEY_CYCLE_COUNT);
                editor.remove(CommonString.KEY_SALES_COUNT);
                editor.apply();
                database.deleteAllSpecificTable();
                AlertMessage message = new AlertMessage(PreviousDataUploadActivity.this, AlertMessage.MESSAGE_UPLOAD_DATA, "success", null);
                message.showMessage();
            } else if (result.equalsIgnoreCase("")) {
                AlertMessage message = new AlertMessage(PreviousDataUploadActivity.this, "Error in uploading :" + result, "success", null);
                message.showMessage();
            }
        }
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        database.close();
    }
    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        Intent i = new Intent(this, DashBoardActivity.class);
        startActivity(i);
        PreviousDataUploadActivity.this.finish();
    }

    class Data {
        int value;
        String name;
    }

}
