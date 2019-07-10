package com.example.unitybooks.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.unitybooks.API.User_API;
import com.example.unitybooks.Models.LoginResponse;
import com.example.unitybooks.Models.Users;
import com.example.unitybooks.R;
import com.example.unitybooks.Retrofit.RetrofitManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Signinfrag extends Fragment implements View.OnClickListener{

    EditText et_fname,et_lname,et_uname,et_pass,et_mail,et_address;
    Button btn_reg;
   User_API uapi;

    public Signinfrag() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_register, container, false);

        et_fname= view.findViewById(R.id.et_ufname);
        et_lname=view.findViewById(R.id.et_ulname);
        et_uname=view.findViewById(R.id.et_username);
        et_pass=view.findViewById(R.id.et_password);
        et_mail=view.findViewById(R.id.et_mail);
        et_address=view.findViewById(R.id.et_address);
        btn_reg = view.findViewById(R.id.btn_register2);

        btn_reg.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_register2) {
            //validation_Registration();
            Gson gson=new GsonBuilder()
                    .setLenient().create();
            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:3100/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
//
            uapi=retrofit.create(User_API.class);
            User_API user_api = RetrofitManager.createinstance().create(User_API.class);
            String uFname = et_fname.getText().toString();
            String uLname = et_lname.getText().toString();
            String uname = et_uname.getText().toString();
            String upass = et_pass.getText().toString();
            String umail = et_mail.getText().toString();
            String uaddress = et_address.getText().toString();


            Users userModel = new Users(uFname,uLname,uname, upass, umail, uaddress,"User");


            final Call<LoginResponse> register = user_api.register(userModel);
            register.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    Toast.makeText(getContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {

                }
            });

        }

    }}