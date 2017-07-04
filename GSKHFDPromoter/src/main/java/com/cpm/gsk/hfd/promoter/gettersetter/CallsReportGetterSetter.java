package com.cpm.gsk.hfd.promoter.gettersetter;

import java.util.ArrayList;

/**
 * Created by jeevanp on 08-05-2017.
 */

public class CallsReportGetterSetter {
  public String today_call;

    public String getCall_report_master_table() {
        return call_report_master_table;
    }

    public void setCall_report_master_table(String call_report_master_table) {
        this.call_report_master_table = call_report_master_table;
    }

    public String call_report_master_table;
    public String getToday_call() {
        return today_call;
    }

    public void setToday_call(String today_call) {
        this.today_call = today_call;
    }

    public String getMtd_call() {
        return mtd_call;
    }

    public void setMtd_call(String mtd_call) {
        this.mtd_call = mtd_call;
    }

    public String mtd_call;


}
