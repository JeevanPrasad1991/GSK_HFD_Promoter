package com.cpm.gsk.hfd.promoter.gettersetter;

import java.util.ArrayList;

/**
 * Created by jeevanp on 24-04-2017.
 */

public class Skugettersetter {
    String sku_master_table;
    ArrayList<String >sku_id=new ArrayList<>();
    ArrayList<String >sku=new ArrayList<>();
    ArrayList<String >brand_id=new ArrayList<>();
    ArrayList<String >category_id=new ArrayList<>();
    ArrayList<String >category=new ArrayList<>();
    ArrayList<String >brand_sequence=new ArrayList<>();
    ArrayList<String >sku_sequence=new ArrayList<>();

    public ArrayList<String> getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id.add(store_id);
    }

    ArrayList<String >store_id=new ArrayList<>();


    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    String quantity;
    public ArrayList<String> getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand.add(brand);
    }

    ArrayList<String >brand=new ArrayList<>();


    public String getSku_master_table() {
        return sku_master_table;
    }

    public void setSku_master_table(String sku_master_table) {
        this.sku_master_table = sku_master_table;
    }

    public ArrayList<String> getSku_id() {
        return sku_id;
    }

    public void setSku_id(String sku_id) {
        this.sku_id.add(sku_id);
    }

    public ArrayList<String> getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku.add(sku);
    }

    public ArrayList<String> getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id.add(brand_id);
    }

    public ArrayList<String> getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id.add(category_id);
    }

    public ArrayList<String> getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category.add(category);
    }

    public ArrayList<String> getBrand_sequence() {
        return brand_sequence;
    }

    public void setBrand_sequence(String brand_sequence) {
        this.brand_sequence.add(brand_sequence);
    }

    public ArrayList<String> getSku_sequence() {
        return sku_sequence;
    }

    public void setSku_sequence(String sku_sequence) {
        this.sku_sequence.add(sku_sequence);
    }
}
