package com.example.streamingandrouas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.streamingandrouas.adapter.CategoryAdapter;
import com.example.streamingandrouas.adapter.MainAdapter;
import com.example.streamingandrouas.data.model.CallResponse;
import com.example.streamingandrouas.data.model.Category;
import com.example.streamingandrouas.data.model.Video;
import com.example.streamingandrouas.rest.Api;
import com.example.streamingandrouas.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity {

    private ListView listView;

    private void getCategory() {
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);

        Call<CallResponse> call = apiInterface.getCategory();
        call.enqueue(new Callback<CallResponse>() {
            @Override
            public void onResponse(Call<CallResponse> call, Response<CallResponse> response) {
                Log.e("_resp", response.toString());
                List<Category> categories = response.body().getCategories();

                /*for (int i=0; i< categories.size(); i++){
                    Log.e("_logCategory", categories.get(i).getCategory());
                    }
                 */
                    listView.setAdapter(new CategoryAdapter(CategoryActivity.this, categories));
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Toast.makeText(getApplicationContext(), categories.get(position).getCategory(), Toast.LENGTH_LONG).show();
                            MainActivity.VIDEO_CATEGORY = categories.get(position).getCategory();
                            finish();
                        }
                    });
                }

            @Override
            public void onFailure(Call<CallResponse> call, Throwable t) {
                Log.e("_err", t.toString());
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        listView = findViewById(R.id.listView);
        getCategory();

        getSupportActionBar().setTitle("Kategori");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}