package com.example.mobilerestaurant.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilerestaurant.R;
import com.example.mobilerestaurant.activities.AddMenuActivity;
import com.example.mobilerestaurant.adapter.ProductsAdapter;
import com.example.mobilerestaurant.api.RetrofitClient;
import com.example.mobilerestaurant.model.Product;
import com.example.mobilerestaurant.model.ProductResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MenuFragment extends Fragment implements View.OnClickListener {

    private RecyclerView recyclerViewProduct;
    private ProductsAdapter adapterProduct;
    private List<Product> productList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.menu_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.addMenu).setOnClickListener(this);

        recyclerViewProduct = view.findViewById(R.id.recyclerViewProduct);
        recyclerViewProduct.setLayoutManager(new LinearLayoutManager(getActivity()));


        Call<ProductResponse> call = RetrofitClient.getInstance().getApi().getProducts();

        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {

                productList = response.body().getProducts();
                adapterProduct = new ProductsAdapter(getActivity(), productList);
                recyclerViewProduct.setAdapter(adapterProduct);
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {

            }
        });

    }

    private void addMenuFood() {
        Intent intent = new Intent(getActivity(), AddMenuActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addMenu:
                addMenuFood();
                break;

        }
    }


}
