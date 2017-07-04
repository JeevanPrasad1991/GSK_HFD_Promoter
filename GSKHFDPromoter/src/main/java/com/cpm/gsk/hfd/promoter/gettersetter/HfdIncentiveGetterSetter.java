package com.cpm.gsk.hfd.promoter.gettersetter;

import java.util.ArrayList;

/**
 * Created by jeevanp on 09-05-2017.
 */

public class HfdIncentiveGetterSetter {
    public String getTable_incentive() {
        return table_incentive;
    }

    public void setTable_incentive(String table_incentive) {
        this.table_incentive = table_incentive;
    }

    String table_incentive;
    ArrayList<String> incentive = new ArrayList<>();
    ArrayList<String> month_name = new ArrayList<>();


    public ArrayList<String> getIncentive() {
        return incentive;
    }

    public void setIncentive(String incentive) {
        this.incentive.add(incentive);
    }

    public ArrayList<String> getMonth_name() {
        return month_name;
    }

    public void setMonth_name(String month_name) {
        this.month_name.add(month_name);
    }

    public ArrayList<String> getPrevious_month() {
        return previous_month;
    }

    public void setPrevious_month(String previous_month) {
        this.previous_month.add(previous_month);
    }

    ArrayList<String> previous_month = new ArrayList<>();
}
