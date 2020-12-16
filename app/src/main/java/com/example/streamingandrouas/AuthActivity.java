package com.example.streamingandrouas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.streamingandrouas.data.model.CallResponse;
import com.example.streamingandrouas.rest.Api;
import com.example.streamingandrouas.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private String ANDROID_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        progressBar = findViewById(R.id.progressBar);

        ANDROID_ID = Settings.Secure.getString(this.getApplicationContext().getContentResolver(),Settings.Secure.ANDROID_ID);
        Log.e("_logAndroidId", ANDROID_ID);

        Auth();
    }

    protected void onResume(){
        super.onResume();
        finish();
    }

    private void Auth(){
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        Call<CallResponse> call = apiInterface.postAuth(ANDROID_ID);
        call.enqueue(new Callback<CallResponse>() {
            @Override
            public void onResponse(Call<CallResponse> call, Response<CallResponse> response) {
                if(response.body().getResponse().equals("success")){
                    progressBar.setVisibility(View.GONE);
                    startActivity(new Intent(AuthActivity.this, MainActivity.class));
                }
                else{
                    Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<CallResponse> call, Throwable t) {
                Log.e("_logErr", t.toString());
            }
        });
    }
}