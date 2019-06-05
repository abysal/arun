package com.example.unitybooks.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signinfrag extends Fragment implements View.OnClickListener{

    EditText et_lname,et_fname,et_uname,et_pass;
    Button btn_reg;
    UserAPI uapi;

    public SignupFrag() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_signup, container, false);

        et_fname= view.findViewById(R.id.et_ufname);
        et_lname=view.findViewById(R.id.et_ulname);
        et_uname=view.findViewById(R.id.et_username);
        et_pass=view.findViewById(R.id.et_password);
        btn_reg=view.findViewById(R.id.btn_signup);

        btn_reg.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_signup)
        {
            //validation_Registration();
            Gson gson=new GsonBuilder()
                    .setLenient().create();
            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:2222/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            uapi=retrofit.create(UserAPI.class);
            String uFname=et_fname.getText().toString();
            String uLname=et_lname.getText().toString();
            String uname=et_uname.getText().toString();
            String pass=et_pass.getText().toString();

            UserModel userModel= new UserModel(uFname,uLname,uname,pass);


            Call<Void> Usercall=uapi.useradd(userModel);
            Usercall.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(getActivity(), "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }

    }