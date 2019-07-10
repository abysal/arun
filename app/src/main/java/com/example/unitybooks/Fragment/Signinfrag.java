package com.example.unitybooks.Fragment;

import android.app.Notification;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.unitybooks.API.User_API;
import com.example.unitybooks.Models.LoginResponse;
import com.example.unitybooks.Models.Users;
import com.example.unitybooks.Notification.Notificationchannel;
import com.example.unitybooks.R;
import com.example.unitybooks.Retrofit.RetrofitManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.SENSOR_SERVICE;

public class Signinfrag extends Fragment implements View.OnClickListener{
private NotificationManagerCompat notificationManagerCompat;
    EditText et_fname,et_lname,et_uname,et_pass,et_mail,et_address;
    Button btn_reg;
   User_API uapi;
   SensorManager sensorManager;


    public Signinfrag() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_register, container, false);

        notificationManagerCompat = NotificationManagerCompat.from(getActivity());
        Notificationchannel channel= new Notificationchannel(getActivity());
        channel.notification();
        proximity();
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
                    DisplayNotification();
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {

                }
            });

        }

    }
private void DisplayNotification(){
    Notification notificationchannel = new NotificationCompat.Builder(getActivity(), Notificationchannel.Channel1_1)
            .setSmallIcon(R.drawable.lee)
            .setContentTitle("Register Successful")
            .setContentText("Registration Successful")
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .build();
    notificationManagerCompat.notify(1, notificationchannel);

}

    public void proximity()
    {
        sensorManager=(SensorManager)getActivity().getSystemService(SENSOR_SERVICE);
        Sensor sensor=sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        if (sensor == null)
        {
            Toast.makeText(getActivity(), "No sensor detected", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getActivity(), "Sensor Kicking in .....", Toast.LENGTH_SHORT).show();
        }

        SensorEventListener proximityListener=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
                if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
                    if (event.values[0] == 0) {
                        params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                        params.screenBrightness = 0;
                        getActivity().getWindow().setAttributes(params);
                    } else {
                        params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                        params.screenBrightness = -1f;
                        getActivity().getWindow().setAttributes(params);
                    }
                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        sensorManager.registerListener(proximityListener,sensor,SensorManager.SENSOR_DELAY_FASTEST);
    }
}