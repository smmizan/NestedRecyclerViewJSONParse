package com.smmizan.nestedrecyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smmizan.nestedrecyclerview.R;
import com.smmizan.nestedrecyclerview.model.ItemSubCategory;

import java.util.ArrayList;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.SubCategoriesViewHolder> {

    Context context;
    ArrayList<ItemSubCategory> itemSubCategories;


    public ChildAdapter(Context context, ArrayList<ItemSubCategory> itemSubCategories) {
        this.context = context;
        this.itemSubCategories = itemSubCategories;
    }

    @NonNull
    @Override
    public SubCategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sub_category,parent,false);


        return new SubCategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubCategoriesViewHolder holder, int position) {
        final ItemSubCategory itemSubCategory = itemSubCategories.get(position);

        holder.tName.setText(itemSubCategory.getName());
        holder.tPrice.setText(itemSubCategory.getPrice());
        holder.tDiscount.setText(itemSubCategory.getDiscount());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, itemSubCategory.getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemSubCategories.size();
    }

    public class SubCategoriesViewHolder extends RecyclerView.ViewHolder {

        TextView tName;
        TextView tPrice;
        TextView tDiscount;


        public SubCategoriesViewHolder(@NonNull View itemView) {
            super(itemView);

            tName = (TextView) itemView.findViewById(R.id.products_name);
            tPrice = (TextView) itemView.findViewById(R.id.products_price);
            tDiscount = (TextView) itemView.findViewById(R.id.products_discount);


        }
    }
}
