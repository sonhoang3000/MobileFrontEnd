package com.example.mobilerestaurant.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mobilerestaurant.R;

import com.example.mobilerestaurant.activities.MainActivity;
import com.example.mobilerestaurant.adapter.UsersAdapter;
import com.example.mobilerestaurant.api.RetrofitClient;
import com.example.mobilerestaurant.model.User;
import com.example.mobilerestaurant.model.UserResponse;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersFragment  extends Fragment {

    private RecyclerView recyclerView;
    private UsersAdapter adapter;
    private List<User> userList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.users_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Call<UserResponse> call = RetrofitClient.getInstance()
                .getApi().getUsers();

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                    userList = response.body().getUsers();
                    adapter = new UsersAdapter(getActivity(),userList);
                    recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });

    }
}