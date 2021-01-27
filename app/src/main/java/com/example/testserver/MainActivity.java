package com.example.testserver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView edtBrand,edtModel,edtColor,edtPrice;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        edtBrand = findViewById(R.id.edt_brand);
        edtModel=findViewById(R.id.etd_model);
        edtColor=findViewById(R.id.edt_color);
        edtPrice= findViewById(R.id.edt_price);
        btnSave = findViewById(R.id.btm_save);
        btnSave.setOnClickListener(this);


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