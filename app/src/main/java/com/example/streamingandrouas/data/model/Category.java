package com.example.streamingandrouas.data.model;

import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("cat_id")  private String cat_id;
    @SerializedName("category")  private String category;

    public String getCat_id() {
        return cat_id;
    }

    public String getCategory() {
        return category;
    }
}
