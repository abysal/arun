package com.example.unitybooks.Users;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.unitybooks.Adapters.Loginregisteradapter;
import com.example.unitybooks.Fragment.Loginfrag;
import com.example.unitybooks.Fragment.Signinfrag;
import com.example.unitybooks.R;

public class Loginregister extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tablayout1);

        Loginregisteradapter adapter = new Loginregisteradapter(getSupportFragmentManager());
        adapter.addFragment(new Loginfrag(), "Login");
        adapter.addFragment(new Signinfrag(), "Register");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }


}

