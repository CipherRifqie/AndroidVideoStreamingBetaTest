package com.example.streamingandrouas.utils;

import android.widget.ImageView;

import com.example.streamingandrouas.R;
import com.squareup.picasso.Picasso;

public class ImgDownload {
    public static void picasso(String URL, ImageView imageView){
        Picasso.get().load(URL).placeholder(R.drawable.no_image).error(R.drawable.no_preview).into(imageView);
    }
}
