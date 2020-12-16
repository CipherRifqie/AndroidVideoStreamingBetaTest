package com.example.streamingandrouas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.streamingandrouas.R;
import com.example.streamingandrouas.data.Constant;
import com.example.streamingandrouas.data.model.Video;
import com.example.streamingandrouas.utils.DateConverter;
import com.example.streamingandrouas.utils.ImgDownload;

import java.util.List;

public class MainAdapter extends ArrayAdapter <Video> {

    public MainAdapter(@NonNull Context context, @NonNull List<Video> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Video video = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_main, parent, false);
        }
        TextView txtTitle = convertView.findViewById(R.id.txtTitle);
        TextView txtCategory = convertView.findViewById(R.id.txtCategory);
        TextView txtDate = convertView.findViewById(R.id.txtDate);
        ImageView imageView = convertView.findViewById(R.id.imageView);

        txtTitle.setText(video.getTitle());
        txtCategory.setText(video.getCategory());
        txtDate.setText( DateConverter.convert (video.getCreated()) );
        ImgDownload.picasso(Constant.COVER_PATH + video.getCover(), imageView);

        return convertView;
    }
}
