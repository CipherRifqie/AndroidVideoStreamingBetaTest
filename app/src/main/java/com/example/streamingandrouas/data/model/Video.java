package com.example.streamingandrouas.data.model;

import com.google.gson.annotations.SerializedName;

public class Video {

    public String getVideo_id() {
        return video_id;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getCover() {
        return cover;
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

    @SerializedName("video_id")  private String video_id;
    @SerializedName("title")  private String title;
    @SerializedName("summary")  private String summary;
    @SerializedName("cover")  private String cover;
    @SerializedName("category")  private String category;
    @SerializedName("created")  private String created;
    @SerializedName("updated")  private String updated;
}
