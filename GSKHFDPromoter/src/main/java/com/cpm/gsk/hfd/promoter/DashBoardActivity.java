package com.cpm.gsk.hfd.promoter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.cpm.gsk.hfd.promoter.constant.CommonString;
import com.cpm.gsk.hfd.promoter.database.GSKDatabase;
import com.cpm.gsk.hfd.promoter.download.CompleteDownloadActivity;
import com.cpm.gsk.hfd.promoter.gettersetter.CallsReportGetterSetter;
import com.cpm.gsk.hfd.promoter.gettersetter.CoverageBean;
import com.cpm.gsk.hfd.promoter.gettersetter.HfdIncentiveGetterSetter;
import com.cpm.gsk.hfd.promoter.gettersetter.JourneyPlanGetterSetter;
import com.cpm.gsk.hfd.promoter.gettersetter.SalesReportGetterSetter;
import com.cpm.gsk.hfd.promoter.upload.PreviousDataUploadActivity;
import com.cpm.gsk.hfd.promoter.upload.UploadDataActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

/**
 * Created by jeevanp on 25-04-2017.
 */

public class DashBoardActivity extends AppCompatActivity implements View.OnClickListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    Button dwn_btn, upld_btn, cycle_btn, export_db;
    TextView calltxt, callmtb_txt, tvstorename, tvkeyaccount, tvcity;
    ImageView imgtag;
    GSKDatabase database;
    ArrayList<JourneyPlanGetterSetter> jcplist;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor = null;
    String user_name, visit_date;
    String lat = "0.0", lon = "0.0";
    GoogleApiClient mGoogleApiClient;
    ArrayList<CoverageBean> coverage_list;
    LinearLayout ll_up, ll_listLayout, rl_down, nodata_linear,sale_lLayout;
    FloatingActionButton fab;
    CardView cardView;
    String calls_cycle, sales_cycle;
    ArrayList<SalesReportGetterSetter> salesReportList = new ArrayList<>();
    CallsReportGetterSetter callsReportGetterSetter;
    ListView sales_list, incentive_list;
    ArrayList<HfdIncentiveGetterSetter>incentiveGetterSetterArrayList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbrd);
        dwn_btn = (Button) findViewById(R.id.dwn_btn);
        cycle_btn = (Button) findViewById(R.id.cycle_btn);
        upld_btn = (Button) findViewById(R.id.upld_btn);
        export_db = (Button) findViewById(R.id.export_db);
        imgtag = (ImageView) findViewById(R.id.imgtag);
        export_db.setOnClickListener(this);
        calltxt = (TextView) findViewById(R.id.calltxt);
        callmtb_txt = (TextView) findViewById(R.id.callmtb_txt);
        tvstorename = (TextView) findViewById(R.id.tvstorename);
        tvkeyaccount = (TextView) findViewById(R.id.tvkeyaccount);
        tvcity = (TextView) findViewById(R.id.tvcity);
        nodata_linear = (LinearLayout) findViewById(R.id.no_data_lay);
        ll_up = (LinearLayout) findViewById(R.id.ll_up);
        ll_listLayout = (LinearLayout) findViewById(R.id.ll);
        sale_lLayout= (LinearLayout) findViewById(R.id.sale_lLayout);
        rl_down = (LinearLayout) findViewById(R.id.rl_down);
        cardView = (CardView) findViewById(R.id.card_view);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        sales_list = (ListView) findViewById(R.id.sales_list);
        incentive_list = (ListView) findViewById(R.id.incentive_list);
        fab.setOnClickListener(this);
        dwn_btn.setOnClickListener(this);
        cycle_btn.setOnClickListener(this);
        upld_btn.setOnClickListener(this);
        database = new GSKDatabase(this);
        database.open();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        visit_date = preferences.getString(CommonString.KEY_DATE, null);
        user_name = preferences.getString(CommonString.KEY_USERNAME, "");
        calls_cycle = preferences.getString(CommonString.KEY_CYCLE_COUNT, null);
        sales_cycle = preferences.getString(CommonString.KEY_SALES_COUNT, null);
        editor = preferences.edit();
        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(getApplicationContext(),
                        android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getApplicationContext(),
                        android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        jcplist = database.getJCPData(visit_date);
        salesReportList = database.getSalesReportMasterData();
        incentiveGetterSetterArrayList = database.getIncentiveMasterData();
        callsReportGetterSetter = database.getCallsReportMasterData();
        if (jcplist.size() > 0) {
            for (int i = 0; i < jcplist.size(); i++) {
                tvstorename.setText(jcplist.get(i).getStore_name().get(0));
                tvcity.setText(jcplist.get(i).getCity().get(0));
                tvkeyaccount.setText(jcplist.get(i).getKEY_ID().get(0));
               /* if (jcplist.get(i).getUploadStatus().get(0).equalsIgnoreCase("U")) {
                    imgtag.setVisibility(View.VISIBLE);
                    imgtag.setBackgroundResource(R.drawable.tick_u);

                }*/
                nodata_linear.setVisibility(View.GONE);
                fab.setVisibility(View.GONE);
                ll_up.setVisibility(View.VISIBLE);
                ll_listLayout.setVisibility(View.VISIBLE);
                rl_down.setVisibility(View.VISIBLE);
                cardView.setVisibility(View.VISIBLE);
            }
            if (salesReportList.size() > 0) {
                sales_list.setAdapter(new MyAdapterSales());
            }
            if (callsReportGetterSetter.getMtd_call() != null) {
                callmtb_txt.setText(callsReportGetterSetter.getMtd_call());
                calltxt.setText(callsReportGetterSetter.getToday_call());
            }
            if (incentiveGetterSetterArrayList.size() > 0) {
                incentive_list.setAdapter(new MyAdapterIncentive());
            }
        }

    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.dwn_btn:
                if (checkNetIsAvailable()) {
                    Intent startDownload = new Intent(DashBoardActivity.this, CompleteDownloadActivity.class);
                    startActivity(startDownload);
                    finish();
                } else {
                    Snackbar.make(dwn_btn, "No Network Available", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                }
                break;
            case R.id.fab:
                if (checkNetIsAvailable()) {
                    if (database.isCoverageDataFilled(visit_date) && calls_cycle != null || sales_cycle != null) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle("Parinaam");
                        builder.setMessage("Please Upload Previous Data First")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent i = new Intent(DashBoardActivity.this, PreviousDataUploadActivity.class);
                                        startActivity(i);
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    } else {
                        try {
                            database.deleteAllSpecificTable();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Intent startDownload = new Intent(getApplicationContext(), CompleteDownloadActivity.class);
                        startActivity(startDownload);
                        finish();
                    }
                } else {
                    Snackbar.make(dwn_btn, "No Network Available", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                }
                break;
            case R.id.cycle_btn:
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(CommonString.KEY_LATITUDE, lat);
                editor.putString(CommonString.KEY_LONGITUDE, lon);
                editor.putString(CommonString.KEY_STORE_INTIME, getCurrentTime());
                editor.putString(CommonString.KEY_STORE_ID, jcplist.get(0).getStore_cd().get(0));
                editor.putString(CommonString.KEY_PROCESS_ID, jcplist.get(0).getPROCESS_ID().get(0));
                editor.putString(CommonString.KEY_EMP_ID, jcplist.get(0).getEmp_cd().get(0));
                editor.putString(CommonString.KEY_PACK_ENABLE, jcplist.get(0).getPKD_ENABLE().get(0));
                editor.commit();
                startActivity(new Intent(DashBoardActivity.this, StartActivity.class));
                break;
            case R.id.upld_btn:
                coverage_list = database.getCoverageData(visit_date);
                if (coverage_list.size() > 0) {
                    if (checkNetIsAvailable()) {
                        if (calls_cycle != null || sales_cycle != null) {
                            Intent i = new Intent(getBaseContext(), UploadDataActivity.class);
                            startActivity(i);
                        } else {
                            Snackbar.make(dwn_btn, "No data found", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                        }
                    } else {
                        Snackbar.make(dwn_btn, "No Network Available", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                    }
                } else {
                    Snackbar.make(dwn_btn, "No data found", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                }

                break;
            case R.id.export_db:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(
                        DashBoardActivity.this);
                builder1.setMessage(
                        "Are you sure you want to take the backup of your data")
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        try {
                                            File file = new File(Environment.getExternalStorageDirectory(), "HFD_DETAILER_BACKUP");
                                            if (!file.isDirectory()) {
                                                file.mkdir();
                                            }
                                            File sd = Environment.getExternalStorageDirectory();
                                            File data = Environment.getDataDirectory();
                                            if (sd.canWrite()) {
                                                String currentDBPath = "//data//com.cpm.gsk.hfd.promoter//databases//" + database.DATABASE_NAME;
                                                String backupDBPath = "HFD_DETAILER_BACKUP" + visit_date.replace('/', '-');
                                                File currentDB = new File(data, currentDBPath);
                                                File backupDB = new File("/mnt/sdcard/HFD_DETAILER_BACKUP/", backupDBPath);
                                                Snackbar.make(dwn_btn, "Database Exported Successfully", Snackbar.LENGTH_SHORT).show();
                                                if (currentDB.exists()) {
                                                    FileChannel src = new FileInputStream(currentDB).getChannel();
                                                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                                                    dst.transferFrom(src, 0, src.size());
                                                    src.close();
                                                    dst.close();
                                                }
                                            }
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                        }

                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alert1 = builder1.create();
                alert1.show();
                break;

        }
    }

    public boolean checkNetIsAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    public String getCurrentTime() {
        Calendar m_cal = Calendar.getInstance();
        String intime = m_cal.get(Calendar.HOUR_OF_DAY) + ":"
                + m_cal.get(Calendar.MINUTE) + ":" + m_cal.get(Calendar.SECOND);
        return intime;
    }

    private class MyAdapterSales extends BaseAdapter {
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return salesReportList.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.row_sales, null);
                holder.brand = (TextView) convertView.findViewById(R.id.brand_txt);
                holder.today_sale = (TextView) convertView.findViewById(R.id.today_txt);
                holder.mtd_sale = (TextView) convertView.findViewById(R.id.mtd_txt);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.brand.setText(salesReportList.get(position).getBrand().get(0));
            holder.today_sale.setText(salesReportList.get(position).getToday_sales().get(0));
            holder.mtd_sale.setText(salesReportList.get(position).getMtd_sale().get(0));
            return convertView;
        }

        private class ViewHolder {
            TextView brand, today_sale, mtd_sale;

        }


    }


    private class MyAdapterIncentive extends BaseAdapter {
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return incentiveGetterSetterArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.row_incentive, null);
                holder.txt_monthname = (TextView) convertView.findViewById(R.id.txt_monthname);
                holder.txt_incentive = (TextView) convertView.findViewById(R.id.txt_incentive);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.txt_incentive.setText(incentiveGetterSetterArrayList.get(position).getIncentive().get(0));
            holder.txt_monthname.setText(incentiveGetterSetterArrayList.get(position).getMonth_name().get(0));
            return convertView;
        }
        private class ViewHolder {
            TextView txt_monthname, txt_incentive;

        }
    }
    @Override
    public void onConnected(Bundle bundle) {
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            lat = String.valueOf(mLastLocation.getLatitude());
            lon = String.valueOf(mLastLocation.getLongitude());
        }
    }
    @Override
    public void onConnectionSuspended(int i) {

    }
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(
                DashBoardActivity.this);
        builder1.setMessage(
                "Are you sure you want exit?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                startActivity(new Intent(DashBoardActivity.this, LoginActivity.class));
                                finish();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert1 = builder1.create();
        alert1.show();
    }
}
