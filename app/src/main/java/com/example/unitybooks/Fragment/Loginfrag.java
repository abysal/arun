package com.example.unitybooks.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.unitybooks.API.User_API;
import com.example.unitybooks.Dashboards;
import com.example.unitybooks.Models.LoginSignupResponse;
import com.example.unitybooks.R;
import com.example.unitybooks.Retrofit.RetrofitManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Loginfrag extends Fragment implements View.OnClickListener{
    EditText etuname,etpass;
    Button btn_login;


    public Loginfrag() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_login, container, false);


        etuname=view.findViewById(R.id.et_Username);
        etpass=view.findViewById(R.id.et_lpassword);
        btn_login=view.findViewById(R.id.btn_lsignin);
        btn_login.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
    if(validate()){

    checkUser();

}


    }

    private boolean validate() {
        boolean checkvalidate=true;
        if(TextUtils.isEmpty(etuname.getText().toString())){
            etuname.setError("Username is required");
            etuname.requestFocus();
            checkvalidate=false;
        }
        if(TextUtils.isEmpty(etpass.getText().toString())){
            etpass.setError("Password is required");
            etpass.requestFocus();
            checkvalidate=false;
        }
        return  checkvalidate;

    }

    private void checkUser() {
         User_API user_api= RetrofitManager.createinstance().create(User_API.class);

        String username= etuname.getText().toString().trim();
        String password= etpass.getText().toString().trim();

        Call<LoginSignupResponse> usersCall = user_api.checkUser(username,password);
        usersCall.enqueue(new Callback<LoginSignupResponse>() {
            @Override
            public void onResponse(Call<LoginSignupResponse> call, Response<LoginSignupResponse> response) {
                if (response.isSuccessful()){
                    RetrofitManager.token = response.body().getToken();
                    Toast.makeText(getActivity(), "Success ", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getActivity(), Dashboards.class);
                    startActivity(intent);
                }
                else {
                    Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(4000);
                }
                }
                @Override
            public void onFailure(Call<LoginSignupResponse> call, Throwable t){
                Toast.makeText(getActivity(),"Error"+t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

        }
        });

}}
