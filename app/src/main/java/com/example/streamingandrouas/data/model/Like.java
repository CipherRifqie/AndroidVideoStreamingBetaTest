package com.example.streamingandrouas.data.model;

import com.google.gson.annotations.SerializedName;

public class Like {

    public String getLike_id() {
        return like_id;
    }

    public String getAndroid_id() {
        return android_id;
    }

    public String getList_id() {
        return list_id;
    }

    public String getCreated() {
        return created;
    }

    public String getStatus() {
        return status;
    }

    public String getVideo_id() {
        return video_id;
    }

    public String getTitle() {
        return title;
    }

    public String getFilename() {
        return filename;
    }

    public String getView() {
        return view;
    }

    public String getUpdated() {
        return updated;
    }

    public String getLike_created() {
        return like_created;
    }

    public String getList_created() {
        return list_created;
    }
    @SerializedName("like_id")  private String like_id;
    @SerializedName("android_id")  private String android_id;
    @SerializedName("list_id")  private String list_id;
    @SerializedName("created")  private String created;
    @SerializedName("status")  private String status;
    @SerializedName("video_id")  private String video_id;
    @SerializedName("title")  private String title;
    @SerializedName("filename")  private String filename;
    @SerializedName("view")  private String view;
    @SerializedName("updated")  private String updated;
    @SerializedName("like_created")  private String like_created;
    @SerializedName("list_created")  private String list_created;
}
