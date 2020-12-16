package com.example.streamingandrouas.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.streamingandrouas.R;
import com.example.streamingandrouas.data.model.Like;
import com.example.streamingandrouas.data.model.ListVideo;
import com.example.streamingandrouas.utils.DateConverter;

public class LikeAdapter extends ArrayAdapter<Like> {
    public LikeAdapter(@NonNull Context context, @NonNull List<Like> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Like like = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_list, parent, false);
        }

        TextView txtListId = convertView.findViewById(R.id.txtListId);
        TextView txtTitle = convertView.findViewById(R.id.txtTitle);
        TextView txtDate = convertView.findViewById(R.id.txtDate);
        TextView txtViews = convertView.findViewById(R.id.txtViews);

        txtListId.setText(like.getList_id());
        txtTitle.setText(like.getTitle());
        txtDate.setText(DateConverter.convert(like.getLike_created()));
        txtViews.setText(like.getView() + " views");

        return convertView;
    }
}
