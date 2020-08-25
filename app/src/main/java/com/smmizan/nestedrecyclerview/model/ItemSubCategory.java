package com.smmizan.nestedrecyclerview.model;

public class ItemSubCategory {
    String Name;
    String Price;
    String Discount;


    public ItemSubCategory(String name, String price, String discount) {
        Name = name;
        Price = price;
        Discount = discount;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }
}
