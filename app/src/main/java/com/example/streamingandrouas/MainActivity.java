package com.example.streamingandrouas;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.streamingandrouas.adapter.MainAdapter;
import com.example.streamingandrouas.data.model.CallResponse;
import com.example.streamingandrouas.data.model.Category;
import com.example.streamingandrouas.data.model.Video;
import com.example.streamingandrouas.rest.Api;
import com.example.streamingandrouas.rest.ApiInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    private EditText editText;
    private ProgressBar progressBar;
    private TextView textView;

    private Menu menu;
    private boolean grid = true;

    public static String VIDEO_CATEGORY = "";

    private void getVideo(String title, String category) {
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        Call<CallResponse> call;
        if(category == ""){
            call = apiInterface.getVideo(title);
        }
        else{
            call = apiInterface.postCategory(category);
        }

        call.enqueue(new Callback<CallResponse>() {
            @Override
            public void onResponse(Call<CallResponse> call, Response<CallResponse> response) {
                Log.e("_resp", response.toString());
                List<Video> videoList = response.body().getVideos();

                if (videoList.size() > 0) {
                    textView.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);

                    gridView.setVisibility(View.VISIBLE);
                    gridView.setAdapter(new MainAdapter(MainActivity.this, videoList));
                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(MainActivity.this, ListActivity.class);
                            intent.putExtra("VIDEO_TITLE",  videoList.get(position).getTitle());
                            intent.putExtra("VIDEO_ID",     videoList.get(position).getVideo_id());
                            startActivity(intent);
                        }
                    });
                    //for (int i=0; i< videoList.size(); i++){ Log.e("VIDEO_SUMMARY", videoList.get(i).getTitle());
                } else {
                    progressBar.setVisibility(View.GONE);
                    gridView.setVisibility(View.GONE);
                    textView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<CallResponse> call, Throwable t) {
                Log.e("_err", t.toString());
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();

        if(!VIDEO_CATEGORY.equals("")){
           editText.setText(""); getVideo("", "VIDEO_CATEGORY");
           VIDEO_CATEGORY = "";
        }
    }

    public static String ANDROID_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ANDROID_ID = Settings.Secure.getString(this.getApplicationContext().getContentResolver(),Settings.Secure.ANDROID_ID);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);



        gridView = findViewById(R.id.gridView);
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        progressBar = findViewById(R.id.progressBar);

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    getVideo(editText.getText().toString(), "");
                    return true;
                }
                return false;
            }
        });

        getVideo("", "");

    }
    public void onBackForward(){
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_custom, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_list) {
            if (grid) {
                gridView.setNumColumns(1);
                menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_menu_grid));
                grid = false;
            } else {
                gridView.setNumColumns(2);
                menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_menu_grid));
                grid = true;
            }

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")

    public boolean onNavigationItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.nav_home){
            editText.setText("");getVideo("", "");
        } else if(id == R.id.nav_category){
            startActivity(new Intent(MainActivity.this, CategoryActivity.class));
        } else if(id == R.id.nav_like){
            startActivity(new Intent(MainActivity.this, LikeActivity.class));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}