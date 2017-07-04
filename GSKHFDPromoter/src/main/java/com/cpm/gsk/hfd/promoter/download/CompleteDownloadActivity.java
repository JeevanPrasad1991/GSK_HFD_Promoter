package com.cpm.gsk.hfd.promoter.download;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cpm.gsk.hfd.promoter.DashBoardActivity;
import com.cpm.gsk.hfd.promoter.R;
import com.cpm.gsk.hfd.promoter.constant.CommonString;
import com.cpm.gsk.hfd.promoter.database.GSKDatabase;
import com.cpm.gsk.hfd.promoter.delegates.TableBean;
import com.cpm.gsk.hfd.promoter.gettersetter.CallsReportGetterSetter;
import com.cpm.gsk.hfd.promoter.gettersetter.HfdIncentiveGetterSetter;
import com.cpm.gsk.hfd.promoter.gettersetter.JourneyPlanGetterSetter;
import com.cpm.gsk.hfd.promoter.gettersetter.SalesReportGetterSetter;
import com.cpm.gsk.hfd.promoter.gettersetter.Skugettersetter;
import com.cpm.gsk.hfd.promoter.message.AlertMessage;
import com.cpm.gsk.hfd.promoter.xmlHandler.XMLHandlers;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;

public class CompleteDownloadActivity extends AppCompatActivity {
    private Dialog dialog;
    private ProgressBar pb;
    private TextView percentage, message;
    private Data data;
    int eventType;
    JourneyPlanGetterSetter jcpgettersetter;
    GSKDatabase db;
    TableBean tb;
    String _UserId;
    private SharedPreferences preferences;
    Skugettersetter skugettersetter;
    SalesReportGetterSetter salesReportGetterSetter;
    CallsReportGetterSetter callsReportGetterSetter;
    HfdIncentiveGetterSetter incentiveGetterSetter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbrd);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        _UserId = preferences.getString(CommonString.KEY_USERNAME, "");
        tb = new TableBean();
        db = new GSKDatabase(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        new BackgroundTask(this).execute();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        // db.open();
        /*FragmentManager fragmentManager = getSupportFragmentManager();
        MainFragment cartfrag = new MainFragment();
		fragmentManager.beginTransaction()
				.replace(R.id.frame_layout, cartfrag)
				.commit();*/

    }

    //	@Override
    //	protected void onStop() {
    //		// TODO Auto-generated method stub
    //		super.onStop();
    ////		db.close();
    //	}
    class Data {
        int value;
        String name;
    }

    private class BackgroundTask extends AsyncTask<Void, Data, String> {
        private Context context;

        BackgroundTask(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.custom);
            //dialog.setTitle("Download Files");
            dialog.setCancelable(false);
            dialog.show();
            pb = (ProgressBar) dialog.findViewById(R.id.progressBar1);
            percentage = (TextView) dialog.findViewById(R.id.percentage);
            message = (TextView) dialog.findViewById(R.id.message);

        }

        @Override
        protected String doInBackground(Void... params) {
            // TODO Auto-generated method stub
            String resultHttp = "";
            try {

                data = new Data();

                // JCP

                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();

                SoapObject request = new SoapObject(CommonString.NAMESPACE,
                        CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", _UserId);
                request.addProperty("Type", "JOURNEYPLAN");

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                        SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                HttpTransportSE androidHttpTransport = new HttpTransportSE(CommonString.URL);
                androidHttpTransport.call(CommonString.SOAP_ACTION_UNIVERSAL, envelope);
                Object result = (Object) envelope.getResponse();

                if (result.toString() != null) {

                    xpp.setInput(new StringReader(result.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    jcpgettersetter = XMLHandlers.JCPXMLHandler(xpp, eventType);
                    if (jcpgettersetter.getStore_cd().size() > 0) {
                        resultHttp = CommonString.KEY_SUCCESS;
                        String jcpTable = jcpgettersetter.getTable_journey_plan();
                        TableBean.setjcptable(jcpTable);

                    } else {
                        return "JOURNEY_PLAN";
                    }

                    data.value = 10;
                    data.name = "JCP Data Downloading";

                }

                publishProgress(data);


                // HFD SKU data

                request = new SoapObject(CommonString.NAMESPACE,
                        CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD);

                request.addProperty("UserName", _UserId);
                request.addProperty("Type", "HFD_DETAILER_SKU");

                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                androidHttpTransport = new HttpTransportSE(CommonString.URL);

                androidHttpTransport.call(
                        CommonString.SOAP_ACTION_UNIVERSAL, envelope);
                Object resultHFD_DETAILER_SKU = (Object) envelope.getResponse();

                if (resultHFD_DETAILER_SKU.toString() != null) {
                    xpp.setInput(new StringReader(resultHFD_DETAILER_SKU.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    skugettersetter = XMLHandlers.skuMasterXML(xpp, eventType);
                    if (skugettersetter.getBrand_id().size() > 0) {
                        resultHttp = CommonString.KEY_SUCCESS;
                        String table = skugettersetter.getSku_master_table();
                        TableBean.setSkumastertable(table);
                    } else {
                        return "HFD_DETAILER_SKU";
                    }
                    data.value = 30;
                    data.name = "HFD_DETAILER_SKU Data Downloading";

                }
                publishProgress(data);

                // HFD_SALE_REPORT data
                request = new SoapObject(CommonString.NAMESPACE, CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", _UserId);
                request.addProperty("Type", "HFD_SALE_REPORT");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString.URL);
                androidHttpTransport.call(CommonString.SOAP_ACTION_UNIVERSAL, envelope);
                Object resultHFD_SALE_REPORT = (Object) envelope.getResponse();
                if (resultHFD_SALE_REPORT.toString() != null) {
                    xpp.setInput(new StringReader(resultHFD_SALE_REPORT.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    salesReportGetterSetter = XMLHandlers.SalesReportMaster(xpp, eventType);
                    if (salesReportGetterSetter.getBrand().size() > 0) {
                        resultHttp = CommonString.KEY_SUCCESS;
                        String table = salesReportGetterSetter.getSales_report_table();
                        TableBean.setSalesreporttable(table);
                    } else {
                        return "HFD_SALE_REPORT";
                    }
                    data.value = 50;
                    data.name = "HFD_SALE_REPORT Data Downloading";
                }
                publishProgress(data);

                // HFD_CALL_REPORT data
                request = new SoapObject(CommonString.NAMESPACE, CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", _UserId);
                request.addProperty("Type", "HFD_CALL_REPORT");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString.URL);
                androidHttpTransport.call(CommonString.SOAP_ACTION_UNIVERSAL, envelope);
                Object resultHFD_CALL_REPORT = (Object) envelope.getResponse();
                if (resultHFD_CALL_REPORT.toString() != null) {
                    xpp.setInput(new StringReader(resultHFD_CALL_REPORT.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    callsReportGetterSetter = XMLHandlers.CallsReportMaster(xpp, eventType);
                    if (callsReportGetterSetter.getMtd_call()!=null) {
                        resultHttp = CommonString.KEY_SUCCESS;
                        String table = callsReportGetterSetter.getCall_report_master_table();
                        TableBean.setCallreportmastertable(table);
                    } else {
                        return "HFD_CALL_REPORT";
                    }
                    data.value = 70;
                    data.name = "HFD_CALL_REPORT Data Downloading";
                }
                publishProgress(data);


                // HFD_INCENTIVE data
                request = new SoapObject(CommonString.NAMESPACE, CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", _UserId);
                request.addProperty("Type", "HFD_INCENTIVE");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString.URL);
                androidHttpTransport.call(CommonString.SOAP_ACTION_UNIVERSAL, envelope);
                Object resultHFD_INCENTIVE = (Object) envelope.getResponse();
                if (resultHFD_INCENTIVE.toString() != null) {
                    xpp.setInput(new StringReader(resultHFD_INCENTIVE.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                incentiveGetterSetter = XMLHandlers.IncentiveMaster(xpp, eventType);
                    if (incentiveGetterSetter.getIncentive().size()>0) {
                        resultHttp = CommonString.KEY_SUCCESS;
                        String table = incentiveGetterSetter.getTable_incentive();
                        TableBean.setIncentivemastertable(table);
                    } else {
                        return "HFD_INCENTIVE";
                    }
                    data.value = 90;
                    data.name = "HFD_INCENTIVE Data Downloading";
                }

                publishProgress(data);
                db.open();
                db.insertJCPData(jcpgettersetter);
                db.insertSkuMasterData(skugettersetter);
                db.insertSalesReportMasterData(salesReportGetterSetter);
                db.insertIncentiveMasterData(incentiveGetterSetter);
                db.insertCallsReportMasterData(callsReportGetterSetter);

                data.value = 100;
                data.name = "Finishing";
                publishProgress(data);
                return resultHttp;
            } catch (MalformedURLException e) {
                final AlertMessage message = new AlertMessage(
                        CompleteDownloadActivity.this,
                        AlertMessage.MESSAGE_EXCEPTION, "download", e);
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        message.showMessage();
                    }
                });

            } catch (IOException e) {
                final AlertMessage message = new AlertMessage(
                        CompleteDownloadActivity.this,
                        AlertMessage.MESSAGE_SOCKETEXCEPTION, "socket", e);
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        message.showMessage();

                    }
                });

            } catch (Exception e) {
                final AlertMessage message = new AlertMessage(
                        CompleteDownloadActivity.this,
                        AlertMessage.MESSAGE_EXCEPTION + e, "download", e);

                e.getMessage();
                e.printStackTrace();
                e.getCause();
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        message.showMessage();
                    }
                });
            }

            return "";
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
            if (result.equals(CommonString.KEY_SUCCESS)) {
                AlertMessage message = new AlertMessage(
                        CompleteDownloadActivity.this,
                        AlertMessage.MESSAGE_DOWNLOAD, "success", null);
                message.showMessage();
            } else {
                AlertMessage message = new AlertMessage(CompleteDownloadActivity.this, AlertMessage.MESSAGE_JCP_FALSE + result,
                        "success", null);
                message.showMessage();
            }
        }
    }

}
