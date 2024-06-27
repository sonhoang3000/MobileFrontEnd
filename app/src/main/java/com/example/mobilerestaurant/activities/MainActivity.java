package com.example.mobilerestaurant.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobilerestaurant.R;
import com.example.mobilerestaurant.api.RetrofitClient;
import com.example.mobilerestaurant.model.DefaultResponse;
import com.example.mobilerestaurant.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextEmail, editTextPassword, editTextName ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextName = findViewById(R.id.editTextName);

        findViewById(R.id.buttonSignUp).setOnClickListener(this);
        findViewById(R.id.textViewLogin).setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();

        if(SharedPrefManager.getInstance(this).isLoggedin()) {
            Intent intent = new Intent(this,ProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    private void userSignUp() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String name = editTextName.getText().toString().trim();

        if(email.isEmpty()) {
            editTextEmail.setError("email is required");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()) {
            editTextEmail.setError("Password is required ");
            editTextEmail.requestFocus();
            return;
        }

        if(password.length() < 6) {
            editTextEmail.setError("Password should be alleast 6 characters long ");
            editTextEmail.requestFocus();
            return;
        }

        if(name.isEmpty()) {
            editTextEmail.setError("Name is required ");
            editTextEmail.requestFocus();
            return;
        }

        Call<DefaultResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .createNewUser(email,name,password);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                DefaultResponse defaultResponse = response.body();

                if(defaultResponse.isErrCode() == 0) {
                    Toast.makeText(MainActivity.this,defaultResponse.getMessage(),Toast.LENGTH_LONG).show();

                } else if(defaultResponse.isErrCode() == 1) {
                    Toast.makeText(MainActivity.this,defaultResponse.getMessage(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSignUp:
                userSignUp();
                break;
            case R.id.textViewLogin:
                startActivity(new Intent(this,LoginActivity.class));
                break;

        }
    }
}