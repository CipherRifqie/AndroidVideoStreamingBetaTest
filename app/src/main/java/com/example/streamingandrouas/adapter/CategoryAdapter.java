package com.example.streamingandrouas.adapter;

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
import com.example.streamingandrouas.data.model.Category;
import com.example.streamingandrouas.data.model.Video;
import com.example.streamingandrouas.utils.DateConverter;
import com.example.streamingandrouas.utils.ImgDownload;

public class CategoryAdapter extends ArrayAdapter<Category>{


    {

    public CategoryAdapter(Context context, @NonNull List<Category> objects) {
        super(context, 0, objects);
    }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Category category = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_category, parent, false);
        }
        TextView txtId = convertView.findViewById(R.id.txtId);
        TextView txtCategory = convertView.findViewById(R.id.txtCategory);


        txtId.setText(category.getCat_id());
        txtCategory.setText(category.getCategory());


        return convertView;
    }
}
