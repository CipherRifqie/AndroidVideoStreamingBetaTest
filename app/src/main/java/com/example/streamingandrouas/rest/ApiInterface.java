package com.example.streamingandrouas.rest;

import com.example.streamingandrouas.data.model.CallResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("video/auth")
    Call<CallResponse> postAuth(
            @Field("android_id") String android_id);

    @GET("video")
    Call<CallResponse> getVideo(
            @Query("title") String title);

    @GET("video")
    Call<CallResponse> getList(
            @Query("video_id") String video_id);

    @GET("video/category")
    Call<CallResponse> getCategory();

    @FormUrlEncoded
    @POST("video/category")
    Call<CallResponse> postCategory(
            @Field("category") String category);

    @FormUrlEncoded
    @POST("video/view")
    Call<CallResponse> postView(
            @Field("list_id") String list_id);

    @FormUrlEncoded
    @POST("video/like")
    Call<CallResponse> postLike(
            @Field("android_id") String android_id,
            @Field("list_id") String list_id);

    @FormUrlEncoded
    @POST("video/unlike")
    Call<CallResponse> postUnlike(
            @Field("android_id") String android_id,
            @Field("list_id") String list_id);

    @GET("video/like")
    Call<CallResponse> getLike(
            @Query("android_id") String android_id);

}

