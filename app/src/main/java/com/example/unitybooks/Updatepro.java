package com.example.unitybooks;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.unitybooks.API.User_API;
import com.example.unitybooks.Models.Users;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Updatepro extends AppCompatActivity implements View.OnClickListener {

        EditText et_fname,et_lname,et_mail,et_uname,et_pass,et_address;
        Button btn_up;
        Boolean isLoggedIn=false;

        private static final String BASE_URL ="http://10.0.2.2:3100/";


        User_API user_api;
        Retrofit retrofit;
        SharedPreferences preferences;
        SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatepro);
        LoadUserDetail();

        et_fname =findViewById(R.id.et_uf);
        et_lname =findViewById(R.id.et_ul);
        et_mail =findViewById(R.id.et_m);
        et_uname =findViewById(R.id.et_un);
        et_pass =findViewById(R.id.et_p);
        et_address =findViewById(R.id.et_a);

        btn_up = findViewById(R.id.btn_update);
        btn_up.setOnClickListener(this);



            }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_update){
            updateProfile();
        }
    }
    private void createInstance(){
//        Gson gson = GsonBuilder()
//                .setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        user_api= retrofit.create(User_API.class);
    }



    private void LoadUserDetail() {
        createInstance();
        preferences = getSharedPreferences("APP", 0);
        final String userId = preferences.getString("uid", null);

        Toast.makeText(this, "User ID +" + userId, Toast.LENGTH_SHORT).show();
        Call<Users> usersCall = user_api.loadprofile(userId);
        usersCall.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                Users users = response.body();
                et_fname.setText(users.getFname());
                et_lname.setText(users.getLname());
                et_uname.setText(users.getUsername());
                et_pass.setText(users.getPassword());
                et_mail.setText(users.getEmail());
                et_address.setText(users.getAddress());


            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
            Toast.makeText(Updatepro.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    public void updateProfile(){

        createInstance();
        String newfn,newln,newun,newp,newm,newa;

         newfn= et_fname.getText().toString();
         newln=et_lname.getText().toString();
        newun=et_uname.getText().toString();
        newp=et_pass.getText().toString();
        newm=et_mail.getText().toString();
        newa=et_address.getText().toString();

        SharedPreferences sharedPreferences = (Updatepro.this).getSharedPreferences("APP",0);
        String userID = sharedPreferences.getString("uid", null);

        Toast.makeText(this, "User id +", Toast.LENGTH_LONG).show();
        Call<String> updateProfileData= user_api.updateProfile(userID,newfn,newln,newun,newp,newm,newa);
        updateProfileData.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(Updatepro.this, "Profile Updated", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Updatepro.this, Dashboards.class);
                startActivity(intent);
            }
                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(Updatepro.this, "Error"+t.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });

    }
    }
