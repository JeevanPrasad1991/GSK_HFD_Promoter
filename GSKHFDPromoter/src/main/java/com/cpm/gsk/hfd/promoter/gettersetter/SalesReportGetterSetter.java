package com.cpm.gsk.hfd.promoter.gettersetter;

import java.util.ArrayList;

/**
 * Created by jeevanp on 08-05-2017.
 */

public class SalesReportGetterSetter {
    public String getSales_report_table() {
        return sales_report_table;
    }

    public void setSales_report_table(String sales_report_table) {
        this.sales_report_table = sales_report_table;
    }

    String sales_report_table;
    ArrayList<String >brand=new ArrayList<>();
    ArrayList<String >today_sales=new ArrayList<>();
    ArrayList<String >mtd_sale=new ArrayList<>();

    public ArrayList<String> getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand.add(brand);
    }

    public ArrayList<String> getToday_sales() {
        return today_sales;
    }

    public void setToday_sales(String today_sales) {
        this.today_sales.add(today_sales);
    }

    public ArrayList<String> getMtd_sale() {
        return mtd_sale;
    }

    public void setMtd_sale(String mtd_sale) {
        this.mtd_sale.add(mtd_sale);
    }
}
