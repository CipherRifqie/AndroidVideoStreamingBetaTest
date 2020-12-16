package com.example.streamingandrouas.data.model;

import com.google.gson.annotations.SerializedName;

public class ListVideo {
    @SerializedName("list_id")  private String list_id;
    @SerializedName("video_id")  private String video_id;
    @SerializedName("title")  private String title;
    @SerializedName("filename")  private String filename;
    @SerializedName("view")  private String view;
    @SerializedName("category")  private String category;
    @SerializedName("created")  private String created;
    @SerializedName("updated")  private String updated;

    public String getList_id() {
        return list_id;
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

    public String getCategory() {
        return category;
    }

    public String getCreated() {
        return created;
    }

    public String getUpdated() {
        return updated;
    }
}
