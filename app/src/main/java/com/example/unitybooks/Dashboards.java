package com.example.unitybooks;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.unitybooks.API.User_API;
import com.example.unitybooks.Adapters.BookRecyclerAdapter;
import com.example.unitybooks.Models.Books;
import com.example.unitybooks.Models.LoginResponse;
import com.example.unitybooks.Models.LoginSignupResponse;
import com.example.unitybooks.Models.Users;
import com.example.unitybooks.Retrofit.RetrofitManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Url;

import static com.example.unitybooks.Retrofit.RetrofitManager.createinstance;
import static com.example.unitybooks.Retrofit.RetrofitManager.token;

public class Dashboards extends AppCompatActivity implements View.OnClickListener {
    RecyclerView rv;
    Button btnAdd;
    List<Books> Booklist = new ArrayList<>();
    SharedPreferences shared;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboards);
        rv = findViewById(R.id.recyclerView);
        shared=getSharedPreferences("APP",MODE_PRIVATE);
        editor=shared.edit();
        editor.putString("token", token);
        editor.commit();
        getAllItem();
        getSupportActionBar().hide();
        rv.setLayoutManager(new GridLayoutManager(this, 3));
        rv.setAdapter(new BookRecyclerAdapter(Booklist, this));
        btnAdd = findViewById(R.id.btnadd);
        btnAdd.setOnClickListener(this);

    }
    //Setting List oF items in Recycler View
    private void getAllItem() {
        Call<List<Books>> listCall = RetrofitManager.createinstance()
                .create(User_API.class)
                .getAllBook(token);
        listCall.enqueue(new Callback<List<Books>>() {
            @Override
            public void onResponse(Call<List<Books>> call, Response<List<Books>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(Dashboards.this, "Code:" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Books>itemList=response.body();
                BookRecyclerAdapter itemadapter=new BookRecyclerAdapter(itemList, Dashboards.this);
                rv.setAdapter(itemadapter);
                rv.setLayoutManager(new LinearLayoutManager(Dashboards.this));
            }

            @Override
            public void onFailure(Call<List<Books>> call, Throwable t) {
                Toast.makeText(Dashboards.this, "Code:" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnadd:
                Intent intent = new Intent(Dashboards.this, BookAdd.class);
                startActivity(intent);

                break;
        }
    }
}