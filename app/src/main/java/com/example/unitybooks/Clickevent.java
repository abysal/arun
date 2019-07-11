package com.example.unitybooks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Clickevent extends AppCompatActivity {
ImageView ivbookimage;
TextView txtbname,txtbprice,txtbauth;
Button btnres;
Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clickevent);

        bundle=getIntent().getExtras();
        Individualitem();
    }

    private void Individualitem() {
    ivbookimage=findViewById(R.id.ivbookimage);
    txtbname=findViewById(R.id.itbookname);
    txtbprice=findViewById(R.id.itbprice);
    txtbauth=findViewById(R.id.bookauth);
     btnres=findViewById(R.id.btnres);

        if(bundle != null) {
            txtbname.setText(bundle.getString("Book_Name"));
            txtbprice.setText(bundle.getString("Book_price"));
            txtbauth.setText(bundle.getString("Book_author"));
            String image = bundle.getString("Book_img");

            Picasso.with(Clickevent.this).load(image).into(ivbookimage);
        }
            btnres.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(Clickevent.this, Buy.class);
                    startActivity(intent);
                }
            });


        }

}
