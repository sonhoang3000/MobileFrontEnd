package com.example.mobilerestaurant.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobilerestaurant.R;
import com.example.mobilerestaurant.activities.LoginActivity;
import com.example.mobilerestaurant.activities.ProfileActivity;
import com.example.mobilerestaurant.api.RetrofitClient;
import com.example.mobilerestaurant.model.DefaultResponse;
import com.example.mobilerestaurant.model.LoginResponse;
import com.example.mobilerestaurant.model.User;
import com.example.mobilerestaurant.storage.SharedPrefManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsFragment extends Fragment implements View.OnClickListener{

    private EditText editTextName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.settings_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextName = view.findViewById(R.id.editTextName);

        view.findViewById(R.id.buttonSave).setOnClickListener(this);
        view.findViewById(R.id.buttonLogout).setOnClickListener(this);
        view.findViewById(R.id.buttonDelete).setOnClickListener(this);
    }

    private void editUser() {
        String name = editTextName.getText().toString().trim();

        if(name.isEmpty()) {
            editTextName.setError("Name is required ");
            editTextName.requestFocus();
            return;
        }

        User user = SharedPrefManager.getInstance(getActivity()).getUser();

        if (user != null && user.getId() != null) {
            Call<LoginResponse> call = RetrofitClient.getInstance().getApi().editUser(user.getId(),name);

            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                    Toast.makeText(getActivity(),response.body().getMessage(),Toast.LENGTH_LONG).show();

                    if(response.body().isErrCode() ==0) {
//                        SharedPrefManager.getInstance(getActivity()).saveUser(response.body().getUser());
//                        Toast.makeText(getActivity(),response.body().getMessage(),Toast.LENGTH_LONG).show();

                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            Toast.makeText(getActivity(), "No user data available", Toast.LENGTH_SHORT).show();
        }



    }

    private void logout() {
        SharedPrefManager.getInstance(getActivity()).clear();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void deleteUser() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Are you sure ?");
        builder.setMessage("This action is inreversible...");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                User user = SharedPrefManager.getInstance(getActivity()).getUser();
                Call<DefaultResponse> call = RetrofitClient.getInstance().getApi().deleteUser(user.getId());
                call.enqueue(new Callback<DefaultResponse>() {
                    @Override
                    public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                        Toast.makeText(getActivity(),"haha",Toast.LENGTH_LONG).show();

                        if(response.body().isErrCode() ==0) {
                            SharedPrefManager.getInstance(getActivity()).clear();
                            Intent intent = new Intent(getActivity(), LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }

                        Toast.makeText(getActivity(),response.body().getMessage(),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<DefaultResponse> call, Throwable t) {

                    }
                });



            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog ad = builder.create();
        ad.show();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSave:
                editUser();
                break;
            case R.id.buttonLogout:
                logout();
                break;
            case R.id.buttonDelete:
                deleteUser();
                break;
        }
    }
}