package com.example.unitybooks.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Loginfrag extends Fragment implements View.OnClickListener{
    EditText etuname,etpass;
    Button btn_login;
    UserAPI uapi;

    public Loginfrag() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_loginfrag, container, false);

        etuname=view.findViewById(R.id.et_lusername);
        etpass=view.findViewById(R.id.et_lpassword);
        btn_login=view.findViewById(R.id.btn_lsignin);

        btn_login.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_lsignin)
        {
            Gson gson=new GsonBuilder()
                    .setLenient().create();
            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:2222/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            uapi=retrofit.create(UserAPI.class);

            String uname=etuname.getText().toString();
            String pass=etpass.getText().toString();


            Call<String> Logincall=uapi.login(uname,pass);

            Logincall.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getActivity(), Dashboard.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(getActivity(), "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
