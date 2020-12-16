package com.example.streamingandrouas.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.streamingandrouas.R;
import com.example.streamingandrouas.data.model.Category;
import com.example.streamingandrouas.data.model.ListVideo;
import com.example.streamingandrouas.utils.DateConverter;

public class ListAdapter extends ArrayAdapter<ListVideo> {


    {

    public ListAdapter(Context context, @NonNull List < ListVideo > objects) {
        super(context, 0, objects);
    }

        @NonNull
        @Override
        public View getView ( int position, @Nullable View convertView, @NonNull ViewGroup parent){

        ListVideo listVideo = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_list, parent, false);
        }

        TextView txtListId = convertView.findViewById(R.id.txtListId);
        TextView txtTitle = convertView.findViewById(R.id.txtTitle);
        TextView txtDate = convertView.findViewById(R.id.txtDate);
        TextView txtViews = convertView.findViewById(R.id.txtViews);

        txtListId.setText(listVideo.getList_id());
        txtTitle.setText(listVideo.getTitle());
        txtDate.setText(DateConverter.convert(listVideo.getCreated()));
        txtViews.setText(listVideo.getView() + " views");

        return convertView;
    }
    }
}