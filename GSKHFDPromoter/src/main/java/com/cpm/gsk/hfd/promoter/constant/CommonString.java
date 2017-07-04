package com.cpm.gsk.hfd.promoter.constant;

import android.os.Environment;

/**
 * Created by jeevanp on 24-04-2017.
 */

public class CommonString {
    public static final String KEY_COMMONID = "common_id";
    // preferenec keys
    public static final String KEY_QUESTION_CD = "question_cd";
    public static final String KEY_ANSWER_CD = "answer_cd";
    public static final String KEY_IS_QUIZ_DONE = "is_quiz_done";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_REMEMBER = "remember";
    public static final String KEY_RIGHT_NAME = "right_name";
    public static final String KEY_PATH = "path";

    public static final String KEY_VERSION = "version";
    public static final String METHOD_UPLOAD_XML = "DrUploadXml";
    public static final String MEHTOD_UPLOAD_COVERAGE_STATUS = "Upload_Status";

    public static final String KEY_USER_TYPE = "RIGHTNAME";

    public static final String KEY_DATE = "date";
    public static final String KEY_STORE_INTIME = "inTimeStore";

    public static final String MID = "MID";
    public static final String KEY_P = "P";
    public static final String KEY_D = "D";
    public static final String KEY_U = "U";
    public static final String KEY_CHECK_IN = "I";
    public static final String SOAP_ACTION = "http://tempuri.org/";
    public static final String KEY_SUCCESS = "Success";
    public static final String KEY_FAILURE = "Failure";
    public static final String KEY_FALSE = "False";
    public static final String KEY_FAIL = "Fail";
    public static final String KEY_CHANGED = "Changed";

    public static final String KEY_NO_DATA = "NoData";

    public static final String KEY_MID = "MID";
    public static final String KEY_SKU_STOCK = "SKU_STOCK";
    public static final String KEY_SKU_ID = "SKU_ID";
    public static final String KEY_QUANTITY = "QUANTITY";

    public static final String KEY_ENTRY = "ENTRY";
    public static final String KEY_PROCESS_ID = "PROCESS_ID";
    public static final String KEY_EMP_ID = "EMP_ID";
    public static final String KEY_PACK_ENABLE = "PACK_ENABLE";
    public static final String KEY_IMAGE = "IMAGE1";

    public static final String TABLE_SKU_SALES_DATA = "SKU_SALES_DATA";
    public static final String CREATE_TABLE_SKU_SALES_DATA = "CREATE TABLE IF NOT" +
            " EXISTS SKU_SALES_DATA(Common_Id INTEGER, SKU_ID INTEGER,STORE_ID VARCHAR, SKU VARCHAR, QUANTITY INTEGER, BRAND_ID INTEGER,BRAND VARCHAR)";

    public static final String TABLE_INSERT_SKU_BRAND_DATA = "SKU_BRAND_DATA";
    public static final String CREATE_TABLE_SKU_BRAND_DATA = "CREATE TABLE IF " +
            "NOT EXISTS SKU_BRAND_DATA(Common_Id INTEGER,STORE_ID VARCHAR, BRAND_ID INTEGER,BRAND VARCHAR)";
    public static final String NAMESPACE = "http://tempuri.org/";
    public static final String URL = "http://gskmtm.parinaam.in/GskmtService.asmx";
    public static final String URL_Notice_Board = "http://ki.parinaam.in/notice/notice.html";

    public static final String METHOD_LOGIN = "UserLoginDetail";
    public static final String SOAP_ACTION_LOGIN = "http://tempuri.org/"
            + METHOD_LOGIN;

    public static final String METHOD_UPLOAD_STORE_STATUS = "InsertUserCurrentLocation";

    public static final String METHOD_UPLOAD_STATUS = "DEVIATION_APPROVAL_SUP_STATUS";


    public static final String SOAP_ACTION_UPLOAD_STORE_STATUS = "http://tempuri.org/"
            + METHOD_UPLOAD_STATUS;

    // public static final String METHOD_NAME_JCP = "DownLoadStoreJcp";
    public static final String METHOD_NAME_JCP = "DownLoadStoreJcp_Special";
    public static final String SOAP_ACTION_JCP = "http://tempuri.org/"
            + METHOD_NAME_JCP;

    public static final String METHOD_NAME_STORE_LAYOUT = "DownLoad_Store_Layout";
    public static final String SOAP_ACTION_STORE_LAYOUT = "http://tempuri.org/"
            + METHOD_NAME_STORE_LAYOUT;

    public static final String METHOD_NAME_STORE_SIZE = "DownLoad_Store_Size";
    public static final String SOAP_ACTION_STORE_SIZE = "http://tempuri.org/"
            + METHOD_NAME_STORE_SIZE;

    public static final String METHOD_NAME_UPLOAD_GEOTAG_IMAGE = "Upload_StoreGeoTag_IMAGES";
    public static final String SOAP_ACTION_UPLOAD_GEOTAG_IMAGE = "http://tempuri.org/"
            + METHOD_NAME_UPLOAD_GEOTAG_IMAGE;

    public static final String METHOD_NAME_PLANOGRAM_IMAGES = "DownLoad_PlanoGramMapping";
    public static final String SOAP_ACTION_PLANOGRAM_IMAGES = "http://tempuri.org/"
            + METHOD_NAME_PLANOGRAM_IMAGES;

    public static final String METHOD_NAME_delete_coverage = "DeleteChekoutAndCoverage";
    public static final String SOAP_ACTION_delete_coverage = "http://tempuri.org/"
            + METHOD_NAME_delete_coverage;

    public static final String METHOD_Checkout_StatusNew = "Upload_Store_ChecOut_Status_V1";
    public static final String SOAP_ACTION_Checkout_StatusNew = "http://tempuri.org/"
            + METHOD_Checkout_StatusNew;


    // String value for promotional master

    public static final String METHOD_NAME_DownLoad_Promotional_Master = "DownLoad_Promotional_Master";
    public static final String SOAP_ACTION_Promotional_Master = "http://tempuri.org/"
            + METHOD_NAME_DownLoad_Promotional_Master;

    // String value for SKU master

    public static final String METHOD_NAME_DOWNLOAD_SKU_MASTER = "DownLoad_SKU_Master";
    public static final String SOAP_ACTION_DOWNLAOD_SKU_MASTER = "http://tempuri.org/"
            + METHOD_NAME_DOWNLOAD_SKU_MASTER;
    // string value for Master
    public static final String METHOD_NAME_DOWNLOAD_NON_WORKING_MASTER = "DownLoad_NonWorkingReason_Master";
    public static final String METHOD_NAME_DOWNLOAD_NON_WORKING_MASTER_subReason = "DownLoad_NonWorkingSubReason_ByReason";

    public static final String SOAP_ACTION_DOWNLAOD_NON_WORKING_MASTER = "http://tempuri.org/"
            + METHOD_NAME_DOWNLOAD_NON_WORKING_MASTER;

    public static final String SOAP_ACTION_DOWNLAOD_NON_WORKING_MASTER_SUBREASON = "http://tempuri.org/"
            + METHOD_NAME_DOWNLOAD_NON_WORKING_MASTER_subReason;

    public static final String METHOD_NAME_DOWNLOAD_sku_mapping = "DownLoad_SKU_By_Mapping";
    public static final String SOAP_ACTION_DOWNLAOD_sku_mapping = "http://tempuri.org/"
            + METHOD_NAME_DOWNLOAD_sku_mapping;

    // string value for DowloadComplaince

    public static final String METHOD_NAME_DOWNLOAD_COMPLIANCE = "DowloadComplaince";
    public static final String SOAP_ACTION_DOWNLAOD_COMPLIANCE = "http://tempuri.org/"
            + METHOD_NAME_DOWNLOAD_COMPLIANCE;

    // STRING VALUE FOR DowloadPromotionWithComplainceByMapping

    public static final String METHOD_NAME_DOWNLOAD_COMPLIANCE_MAPPING = "DowloadPromotionWithComplainceByMapping";
    public static final String SOAP_ACTION_DOWNLAOD_COMPLIANCE_MAPPING = "http://tempuri.org/"
            + METHOD_NAME_DOWNLOAD_COMPLIANCE_MAPPING;

    public static final String METHOD_NAME_DOWNLOAD_COMPLIANCE_MAPPING_SPECIAL = "DownLoad_PROMOTION_COMPLIANCE_MAPPING_SPECIAL";
    public static final String SOAP_ACTION_DOWNLAOD_COMPLIANCE_MAPPING_SPECIAL = "http://tempuri.org/"
            + METHOD_NAME_DOWNLOAD_COMPLIANCE_MAPPING_SPECIAL;

    public static final String METHOD_VERTICAL_MASTER = "DOWLOAD_VERTICALMASTER";
    public static final String SOAP_ACTION_VERTICAL_MASTER = "http://tempuri.org/"
            + METHOD_VERTICAL_MASTER;

    public static final String METHOD_BRAND_MASTER = "DOWLOAD_BRANDMASTER";
    public static final String SOAP_ACTION_BRAND_Master = "http://tempuri.org/"
            + METHOD_BRAND_MASTER;

    public static final String METHOD_VERTICAL_BRAND_MAPPING = "DOWLOAD_VERTICALBRANDMAPPING";
    public static final String SOAP_ACTION_VERTICAL_BRAND_Mapping = "http://tempuri.org/"
            + METHOD_VERTICAL_BRAND_MAPPING;

    public static final String METHOD_VERTICAL_SKU_MAPPING = "SKUBRANDMAPPINGDownload";
    public static final String SOAP_ACTION_VERTICAL_SKU_Mapping = "http://tempuri.org/"
            + METHOD_VERTICAL_SKU_MAPPING;

    public static final String METHOD_CATEGORY_MASTER = "DOWLOAD_CATEGORYMASTER";
    public static final String SOAP_ACTION_CATEGORY_MASTER = "http://tempuri.org/"
            + METHOD_CATEGORY_MASTER;

    public static final String METHOD_CATEGORY_SKU_MAPPING = "CATEGORYSKUMAPPINGDownload";
    public static final String SOAP_ACTION_CATEGORY_SKU_MAPPING = "http://tempuri.org/"
            + METHOD_CATEGORY_SKU_MAPPING;

    public static final String METHOD_CATEGORY_VERTICAL_MAPPING = "CATEGORYVERTICALMAPPINGDownload";
    public static final String SOAP_ACTION_CATEGORY_VERTICAL_MAPPING = "http://tempuri.org/"
            + METHOD_CATEGORY_VERTICAL_MAPPING;

    public static final String METHOD_CATEGORY_POSM_MAPPING = "POSMBRANDMAPPINGDownload";
    public static final String SOAP_ACTION_POSM_MAPPING = "http://tempuri.org/"
            + METHOD_CATEGORY_POSM_MAPPING;

    public static final String METHOD_SKU_MASTER_DOWNLOAD = "SKU_MASTERDownload";
    public static final String SOAP_ACTION_SKU_MASTER = "http://tempuri.org/"
            + METHOD_SKU_MASTER_DOWNLOAD;

    public static final String METHOD_COMPANY_MASTER_DOWNLOAD = "COMPANY_MASTERDownload";
    public static final String SOAP_ACTION_COMPANY_MASTER = "http://tempuri.org/"
            + METHOD_COMPANY_MASTER_DOWNLOAD;

    // Shahab
    public static final String METHOD_NONSKU_REASON = "DOWLOAD_NON_STOCK_REASON_MASTER";
    public static final String SOAP_ACTION_NONSKU_REASON = "http://tempuri.org/"
            + METHOD_NONSKU_REASON;

    public static final String METHOD_SKU_FOCUS_DOWNLOAD = "SKUAVALIBILITY_FOCUS";
    public static final String SOAP_ACTION_SKU_FOCUS = "http://tempuri.org/"
            + METHOD_SKU_FOCUS_DOWNLOAD;

    public static final String METHOD_MAPPING_COMPETITOR = "DOWLOAD_MAPPINGCOMPEPITORBRAND";
    public static final String SOAP_ACTION_MAPPING_COMPETITOR = "http://tempuri.org/"
            + METHOD_MAPPING_COMPETITOR;

    public static final String METHOD_POSM_MASTER_DOWNLOAD = "DOWLOAD_POSMMASTER";
    public static final String SOAP_ACTION_POSM_MASTER_DOWNLOAD = "http://tempuri.org/"
            + METHOD_POSM_MASTER_DOWNLOAD;

    // Upload Coverage
    public static final String METHOD_UPLOAD_DR_STORE_COVERAGE = "UPLOAD_STORE_COVERAGE_WSC";
    public static final String METHOD_UPLOAD_DR_STORE_COVERAGE_LOC = "UPLOAD_COVERAGE_SUP";

    public static final String METHOD_UPLOAD_DR_STORE_GEO_Location = "UPLOAD_Geo_location";
    public static final String SOAP_ACTION_UPLOAD_DR_STORE_COVERAGE = "http://tempuri.org/" + METHOD_UPLOAD_DR_STORE_COVERAGE_LOC;

    public static final String METHOD_GENERIC_UPLOAD = "DrUploadXml";
    public static final String METHOD_UPLOAD_DR_STORE_DATA = "Upload_Store_Layout_V1";
    public static final String SOAP_ACTION_UPLOAD_DR_STORE_DATA = "http://tempuri.org/"
            + METHOD_UPLOAD_DR_STORE_DATA;

    public static final String SOAP_ACTION_METHOD_GENERIC_UPLOAD = "http://tempuri.org/"
            + METHOD_GENERIC_UPLOAD;

    public static final String METHOD_UPLOAD_DR_RETAILER_INFO = "Upload_DR_STORE_PAYMENT";
    public static final String SOAP_ACTION_UPLOAD_DR_RETAILER_INFO = "http://tempuri.org/"
            + METHOD_UPLOAD_DR_RETAILER_INFO;

    public static final String METHOD_UPLOAD_ASSET = "Upload_Stock_Availiablity_V1";
    public static final String SOAP_ACTION_UPLOAD_ASSET = "http://tempuri.org/"
            + METHOD_UPLOAD_ASSET;

    public static final String METHOD_UPLOAD_SEC_SKU = "Upload_Stock_Availiablity_SECONDARY";
    public static final String SOAP_ACTION_UPLOAD_SEC_SKU = "http://tempuri.org/"
            + METHOD_UPLOAD_SEC_SKU;

    public static final String METHOD_UPLOAD_PCKGE_SKU = "Upload_DR_CORE_SKU_PACKAGING";
    public static final String SOAP_ACTION_UPLOAD_PCKGE_SKU = "http://tempuri.org/"
            + METHOD_UPLOAD_PCKGE_SKU;

    public static final String METHOD_UPLOAD_POSM = "Upload_Posm_Deployed";
    public static final String SOAP_ACTION_UPLOAD_POSM = "http://tempuri.org/"
            + METHOD_UPLOAD_POSM;

    public static final String METHOD_UPLOAD_COMPLIANCE = "Upload_Promotion_WindowExists";
    public static final String SOAP_ACTION_COMPLIANCE = "http://tempuri.org/"
            + METHOD_UPLOAD_COMPLIANCE;

    public static final String METHOD_UPLOAD_COMPLIANCE_SPECIAL = "Upload_Promotion_Special";
    public static final String SOAP_ACTION_COMPLIANCE_SPECIAL = "http://tempuri.org/"
            + METHOD_UPLOAD_COMPLIANCE_SPECIAL;

    public static final String METHOD_NON_WORKING_MASTER = "DOWLOAD_NONWORKINGREGIONMASTER";
    public static final String SOAP_ACTION_NONWORKING = "http://tempuri.org/"
            + METHOD_NON_WORKING_MASTER;

    public static final String METHOD_SET_COVERAGE_STATUS = "Upload_Status";
    public static final String SOAP_ACTION_SET_COVERAGE_STATUS = "http://tempuri.org/"
            + METHOD_SET_COVERAGE_STATUS;

    public static final String METHOD_SET_UPLOAD_GEODATA = "Upload_Store_Geo_Tag";
    public static final String SOAP_ACTION_UPLOAD_GEODATA = "http://tempuri.org/"
            + METHOD_SET_UPLOAD_GEODATA;

    // database

    public static final String TABLE_COVERAGE_DATA = "COVERAGE_DATA";
    public static final String TABLE_HFD_SALE_REPORT = "HFD_SALE_REPORT";
    public static final String TABLE_HFD_CALLS_REPORT = "HFD_CALL_REPORT";
    // FOR JCP DOWNLOAD

    public static final String KEY_ID = "_id";
    public static final String KEY_STORE_ID = "STORE_ID";
    public static final String KEY_BRAND_ID = "BRAND_ID";
    public static final String KEY_BRAND = "BRAND";
    public static final String KEY_USER_ID = "USER_ID";
    public static final String KEY_IN_TIME = "IN_TIME";
    public static final String KEY_OUT_TIME = "OUT_TIME";
    public static final String KEY_VISIT_DATE = "VISIT_DATE";
    public static final String KEY_LATITUDE = "LATITUDE";
    public static final String KEY_LONGITUDE = "LONGITUDE";
    public static final String KEY_COVERAGE_STATUS = "Coverage";
    public static final String KEY_REASON_ID = "REASON_ID";
    public static final String KEY_SUB_REASON_ID = "SUB_REASON_ID";
    public static final String KEY_MEET = "MEET";
    public static final String KEY_ISSUE = "ISSUE";
    public static final String KEY_REASON = "REASON";
    public static final String KEY_STATUS = "STATUS";
    public static final String KEY_CHECKOUT_STATUS = "CHECKOUT_STATUS";

    public static final String KEY_STORE_CD = "STORE_CD";
    public static final String KEY_CYCLE_COUNT = "CYCLE_COUNT";
    public static final String KEY_SALES_COUNT = "SALES_COUNT";
    public static final String KEY_FOOD_STORE = "FOOD_STORE";

    // For
    public static final String SKU = "SKU";
    public static final String METHOD_NAME_UNIVERSAL_DOWNLOAD = "Download_Universal";
    public static final String SOAP_ACTION_UNIVERSAL = "http://tempuri.org/"
            + METHOD_NAME_UNIVERSAL_DOWNLOAD;
    public static final String CREATE_TABLE_COVERAGE_DATA = "CREATE TABLE  IF NOT EXISTS "
            + TABLE_COVERAGE_DATA + " (" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_STORE_ID
            + " VARCHAR,USER_ID VARCHAR, " + KEY_IN_TIME + " VARCHAR,"
            + KEY_OUT_TIME + " VARCHAR," + KEY_VISIT_DATE + " VARCHAR,"
            + KEY_LATITUDE + " VARCHAR," + KEY_LONGITUDE + " VARCHAR," + KEY_PROCESS_ID + " INTEGER,"
            + KEY_COVERAGE_STATUS + " VARCHAR," + KEY_EMP_ID + " INTEGER,"
            + KEY_PACK_ENABLE + " VARCHAR)";


}
