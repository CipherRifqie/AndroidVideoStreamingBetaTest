package com.example.streamingandrouas;

import android.media.Image;
import android.os.Bundle;

import com.example.streamingandrouas.adapter.CategoryAdapter;
import com.example.streamingandrouas.adapter.ListAdapter;
import com.example.streamingandrouas.data.Constant;
import com.example.streamingandrouas.data.model.CallResponse;
import com.example.streamingandrouas.data.model.Category;
import com.example.streamingandrouas.data.model.ListVideo;
import com.example.streamingandrouas.rest.Api;
import com.example.streamingandrouas.rest.ApiInterface;
import com.example.streamingandrouas.utils.ImgDownload;
import com.example.streamingandrouas.utils.MenuDialog;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView txtVideos, txtSummary, txtShow, txtInfo;
    ImageView imageView;
    ListView lstVideo;

    private void getList() {
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);

        Call<CallResponse> call = apiInterface.getList(bundle.getString("VIDEO_ID"));
        call.enqueue(new Callback<CallResponse>() {
            @Override
            public void onResponse(Call<CallResponse> call, Response<CallResponse> response) {
                //Log.e("_resp", response.toString());

                final List<ListVideo> list = response.body().getList();
                if (list.size() > 0) {
                    progressBar.setVisibility(View.GONE);
                    lstVideo.setVisibility(View.VISIBLE);

                    lstVideo.setAdapter(new ListAdapter(ListActivity.this, list));
                    lstVideo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            MenuDialog dialog = new MenuDialog();
                            dialog.showDialog(ListActivity.this, list.get(position).getList_id(), list.get(position).getFilename(),
                                    true);
                        }
                    });

                    txtVideos.setText(String.valueOf(list.size()) + "videos");
                } else {
                    progressBar.setVisibility(View.GONE);
                    txtInfo.setVisibility(View.VISIBLE);
                }
                final List<Video> videos = response.body().getVideos();

                for (int i = 0; i < videos.size(); i++) {
                    txtSummary.setText(videos.get(i).getSummary);
                    ImgDownload.picasso(Constant.COVER_PATH + videos.get(i).getCover(), imageView);
                }
            }


            @Override
            public void onFailure(Call<CallResponse> call, Throwable t) {
                Log.e("_err", t.toString());
            }
        });
    }
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bundle = getIntent().getExtras();

            progressBar = findViewById(R.id.progressBar);
            txtVideos = findViewById(R.id.txtVideos);
            txtSummary = findViewById(R.id.txtSummary);
            txtShow = findViewById(R.id.txtShow);
            txtInfo = findViewById(R.id.txtInfo);
            imageView = findViewById(R.id.imageviewplaceholder);
            lstVideo = findViewById(R.id.lstVideo);

            txtShow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(txtShow.getText().toString().equals("More")){
                        txtShow.setText("Less");
                        txtSummary.setSingleLine(false);
                    } else{
                        txtShow.setText("More");
                        txtSummary.setSingleLine(true);
                    }
                }
            });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        getSupportActionBar().setTitle(bundle.getString("VIDEO_TITLE"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getList();
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
    }
}