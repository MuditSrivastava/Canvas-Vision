package com.example.android.capstone.model;

/**
 * Created by DELL on 1/2/2017.
 */

public class Category {
    private String category_name;
    private String category_draw_id;
    public Category(){}

    public Category(String category_name, String category_draw_id){

        setCategory_name(category_name);
        setCategory_draw_id(category_draw_id);
    }

    public void setCategory_name(String category_name){
        this.category_name=category_name;
    }

    public String getCategory_name(){
        return category_name;
    }

    public void setCategory_draw_id(String category_draw_id){
        this.category_draw_id=category_draw_id;
    }

    public String getCategory_draw_id(){
        return category_draw_id;
    }

}
