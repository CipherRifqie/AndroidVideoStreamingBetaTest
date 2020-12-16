package com.example.streamingandrouas;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.streamingandrouas.adapter.LikeAdapter;
import com.example.streamingandrouas.adapter.ListAdapter;
import com.example.streamingandrouas.data.Constant;
import com.example.streamingandrouas.data.model.CallResponse;
import com.example.streamingandrouas.data.model.Like;
import com.example.streamingandrouas.data.model.ListVideo;
import com.example.streamingandrouas.rest.Api;
import com.example.streamingandrouas.rest.ApiInterface;
import com.example.streamingandrouas.utils.ImgDownload;
import com.example.streamingandrouas.utils.MenuDialog;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LikeActivity extends AppCompatActivity {

    ListView listView;
    ProgressBar progressBar;
    TextView txtInfo;

    private void getList() {
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);

        Call<CallResponse> call = apiInterface.getLike(MainActivity.ANDROID_ID);
        call.enqueue(new Callback<CallResponse>() {
            @Override
            public void onResponse(Call<CallResponse> call, Response<CallResponse> response) {
                //Log.e("_resp", response.toString());

                final List<Like> list = response.body().getLikes();
                if (list.size() > 0) {
                    progressBar.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);

                    listView.setAdapter(new LikeAdapter(LikeActivity.this, list));
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            MenuDialog dialog = new MenuDialog();
                            dialog.showDialog(
                                    LikeActivity.this, list.get(position).getList_id(),
                                    list.get(position).getFilename(), false);
                        }
                    });

                } else {
                    progressBar.setVisibility(View.GONE);
                    txtInfo.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<CallResponse> call, Throwable t) {
                Log.e('_err', t.toString());
            }
        });
    }

            @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like);

        listView    = findViewById(R.id.listView);
        progressBar = findViewById(R.id.progressBar);
        txtInfo     = findViewById(R.id.txtInfo);

        getSupportActionBar().setTitle("Like");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getList();
    }

    @Override
    public void onResume(){
        super.onResume();
        getList();
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
    }