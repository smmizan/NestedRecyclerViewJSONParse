package com.smmizan.nestedrecyclerview.model;

import java.util.ArrayList;

public class ItemCategory {

    String CategoryName;
    ArrayList<ItemSubCategory> itemSubCategories;

    public ItemCategory(String categoryName, ArrayList<ItemSubCategory> itemSubCategories) {
        CategoryName = categoryName;
        this.itemSubCategories = itemSubCategories;
    }


    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public ArrayList<ItemSubCategory> getItemSubCategories() {
        return itemSubCategories;
    }

    public void setItemSubCategories(ArrayList<ItemSubCategory> itemSubCategories) {
        this.itemSubCategories = itemSubCategories;
    }
}
