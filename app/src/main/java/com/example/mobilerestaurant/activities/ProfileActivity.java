package com.example.mobilerestaurant.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobilerestaurant.R;
import com.example.mobilerestaurant.model.User;
import com.example.mobilerestaurant.storage.SharedPrefManager;

public class ProfileActivity extends AppCompatActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        textView = findViewById(R.id.textView);

        User user = SharedPrefManager.getInstance(this).getUser();

        textView.setText("Welcome back" + user.getName());

    }
    @Override
    protected void onStart() {
        super.onStart();

        if(!SharedPrefManager.getInstance(this).isLoggedin()) {
            Intent intent = new Intent(  this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}