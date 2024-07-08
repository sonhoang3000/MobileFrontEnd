package com.example.mobilerestaurant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobilerestaurant.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilerestaurant.model.Product;
import com.example.mobilerestaurant.model.User;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> {

    private Context mCtx;
    private List<Product> productList;

    public ProductsAdapter(Context mCtx, List<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(mCtx).inflate(R.layout.recycleview_products,parent,false);
        return new ProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.textViewNameProduct.setText(product.getNameProduct());
        holder.textViewPriceProduct.setText(product.getPrice());
        holder.textViewUnitProduct.setText(product.getUnit());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductsViewHolder extends RecyclerView.ViewHolder{

        TextView textViewNameProduct, textViewPriceProduct, textViewUnitProduct;

        public ProductsViewHolder(View itemView) {
            super(itemView);

            textViewNameProduct = itemView.findViewById(R.id.textViewNameProduct);
            textViewPriceProduct = itemView.findViewById(R.id.textViewPriceProduct);
            textViewUnitProduct = itemView.findViewById(R.id.textViewUnitProduct);

        }
    }

}
