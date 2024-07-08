package com.example.mobilerestaurant.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.mobilerestaurant.R;
import com.example.mobilerestaurant.api.RetrofitClient;
import com.example.mobilerestaurant.model.DefaultResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMenuActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText nameProduct, priceProduct, unitProduct ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);

        nameProduct = findViewById(R.id.nameProduct);
        priceProduct = findViewById(R.id.priceProduct);
        unitProduct = findViewById(R.id.unitProduct);

//        findViewById(R.id.icBack).setOnClickListener(this);
        findViewById(R.id.btnAddMenuProduct).setOnClickListener(this);
    }

    private void addMenuProductFc() {
        String name = nameProduct.getText().toString().trim();
        String price =  priceProduct.getText().toString().trim();
        String unit =  unitProduct.getText().toString().trim();

        if(name.isEmpty()) {
            nameProduct.setError("Name is required ");
            nameProduct.requestFocus();
            return;
        }

        if(price.isEmpty()) {
            priceProduct.setError("price is required ");
            priceProduct.requestFocus();
            return;
        }

        if(unit.isEmpty()) {
            unitProduct.setError("unit is required ");
            unitProduct.requestFocus();
            return;
        }

        Call<DefaultResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .createNewProduct(name,price,unit);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                DefaultResponse defaultResponse = response.body();

                Toast.makeText(AddMenuActivity.this,"Tao Okie",Toast.LENGTH_LONG).show();


                if(defaultResponse.isErrCode() == 0) {
                    Toast.makeText(AddMenuActivity.this,defaultResponse.getMessage(),Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(AddMenuActivity.this,defaultResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Toast.makeText(AddMenuActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }

    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.btnAddMenuProduct:
                addMenuProductFc();
                break;
        }
    }

}
