package com.example.testserver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView edtBrand,edtModel,edtColor,edtPrice;
    Button btnSave,btnF;
    String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnF=findViewById(R.id.btnF);
        edtBrand = findViewById(R.id.edt_brand);
        edtModel=findViewById(R.id.etd_model);
        edtColor=findViewById(R.id.edt_color);
        edtPrice= findViewById(R.id.edt_price);
        btnSave = findViewById(R.id.btm_save);
        btnSave.setOnClickListener(this);
        btnF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("Car");
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null){
                               if (objects.size()>0){
                                   userName="";
                                   for (ParseObject car : objects){
                                       userName= userName+car.get("brand")+"\n";
                                   }
                                   Toast.makeText(MainActivity.this,userName,Toast.LENGTH_LONG).show();
                               }
                               else {
                                   Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_LONG).show();

                               }
                        }else {
                            Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_LONG).show();

                        }
                    }
                });

            }
        });


    }

    @Override
    public void onClick(View v) {
        ParseObject car = new ParseObject("Car");
        car.put("brand",edtBrand.getText().toString());
        car.put("model",edtModel.getText().toString());
        car.put("color",edtColor.getText().toString());
        car.put("price",edtPrice.getText().toString());
        car.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){
                    Toast.makeText(MainActivity.this,"your car is saved : ",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(MainActivity.this,"somting wrrong",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}