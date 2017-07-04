package com.cpm.gsk.hfd.promoter.gettersetter;

import java.util.ArrayList;

/**
 * Created by jeevanp on 25-04-2017.
 */

public class JourneyPlanGetterSetter {
    String table_journey_plan;
    ArrayList<String> store_cd = new ArrayList<String>();
    ArrayList<String> emp_cd = new ArrayList<String>();
    ArrayList<String> VISIT_DATE = new ArrayList<String>();
    ArrayList<String> store_name = new ArrayList<String>();
    ArrayList<String> city = new ArrayList<String>();
    ArrayList<String> store_type = new ArrayList<String>();
    ArrayList<String> uploadStatus = new ArrayList<String>();
    ArrayList<String> checkOutStatus = new ArrayList<String>();
    ArrayList<String> STORETYPE_CD  = new ArrayList<String>();
    ArrayList<String> PROCESS_ID  = new ArrayList<String>();
    ArrayList<String> REGION_ID  = new ArrayList<String>();
    ArrayList<String> KEY_ID  = new ArrayList<String>();
    ArrayList<String> PKD_ENABLE  = new ArrayList<String>();
    public ArrayList<String> getKEY_ID() {
        return KEY_ID;
    }

    public void setKEY_ID(String KEY_ID) {
        this.KEY_ID.add(KEY_ID);
    }

    public ArrayList<String> getPKD_ENABLE() {
        return PKD_ENABLE;
    }

    public void setPKD_ENABLE(String PKD_ENABLE) {
        this.PKD_ENABLE.add(PKD_ENABLE);
    }

    public ArrayList<String> getREGION_ID() {
        return REGION_ID;
    }

    public void setREGION_ID(String REGION_ID) {
        this.REGION_ID.add(REGION_ID);
    }

    public ArrayList<String> getPROCESS_ID() {
        return PROCESS_ID;
    }

    public void setPROCESS_ID(String PROCESS_ID) {
        this.PROCESS_ID.add(PROCESS_ID);
    }









    public ArrayList<String> getSTORETYPE_CD() {
        return STORETYPE_CD;
    }
    public void setSTORETYPE_CD(String STORETYPE_CD) {
        this.STORETYPE_CD.add(STORETYPE_CD);
    }
    public ArrayList<String> getCheckOutStatus() {
        return checkOutStatus;
    }
    public void setCheckOutStatus(String checkOutStatus) {
        this.checkOutStatus.add(checkOutStatus);}
    public ArrayList<String> getVISIT_DATE() {
        return VISIT_DATE;
    }
    public void setVISIT_DATE(String vISIT_DATE) {
        this.VISIT_DATE.add(vISIT_DATE);
    }
    public ArrayList<String> getStore_cd() {
        return store_cd;
    }
    public void setStore_cd(String store_cd) {
        this.store_cd.add(store_cd);
    }
    public ArrayList<String> getEmp_cd() {
        return emp_cd;
    }
    public void setEmp_cd(String emp_cd) {
        this.emp_cd.add(emp_cd);
    }

    public ArrayList<String> getStore_name() {
        return store_name;
    }
    public void setStore_name(String store_name) {
        this.store_name.add(store_name);
    }
    public ArrayList<String> getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city.add(city);
    }
    public ArrayList<String> getStore_type() {
        return store_type;
    }
    public void setStore_type(String store_type) {
        this.store_type.add(store_type);
    }

    public ArrayList<String> getUploadStatus() {
        return uploadStatus;
    }
    public void setUploadStatus(String uploadStatus) {
        this.uploadStatus.add(uploadStatus);
    }
    public String getTable_journey_plan() {
        return table_journey_plan;
    }
    public void setTable_journey_plan(String table_journey_plan) {
        this.table_journey_plan = table_journey_plan;
    }

}
