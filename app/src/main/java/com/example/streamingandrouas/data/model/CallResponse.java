package com.example.streamingandrouas.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CallResponse {
    @SerializedName("response")
    private String response;
    @SerializedName("videos")
    private List<Video> videos;
    @SerializedName("categories")
    private List<Category> categories;
    @SerializedName("list")
    private List<ListVideo> list;
    @SerializedName("likes")
    private List<Like> likes;

    public List<Like> getLikes(){
        return likes;
    }

    public List<ListVideo> getList() {
        return list;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public String getResponse() {
        return response;
    }

    public List<Category> getCategories() {
        return categories;
    }
}