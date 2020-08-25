package com.smmizan.nestedrecyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smmizan.nestedrecyclerview.R;
import com.smmizan.nestedrecyclerview.model.ItemCategory;
import com.smmizan.nestedrecyclerview.model.ItemSubCategory;

import java.util.ArrayList;

public class ParentAdapter extends RecyclerView.Adapter<ParentAdapter.ParentViewHolder> {

    Context context;
    ArrayList<ItemCategory> itemCategories;


    public ParentAdapter(Context context, ArrayList<ItemCategory> itemCategories) {
        this.context = context;
        this.itemCategories = itemCategories;
    }

    @NonNull
    @Override
    public ParentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,parent,false);

        return new ParentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParentViewHolder holder, int position) {

        final  ItemCategory itemCategory = itemCategories.get(position);

        holder.tCategoryName.setText(itemCategory.getCategoryName());


        final ArrayList<ItemSubCategory> itemSubCategories = itemCategory.getItemSubCategories();
        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));

        ChildAdapter childAdapter = new ChildAdapter(context,itemSubCategories);
        holder.recyclerView.setAdapter(childAdapter);


        holder.bCategoryAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, itemCategory.getCategoryName(), Toast.LENGTH_SHORT).show();
            }
        });




    }

    @Override
    public int getItemCount() {
        return itemCategories.size();
    }

    public class ParentViewHolder extends RecyclerView.ViewHolder{

        TextView tCategoryName;
        Button bCategoryAll;
        RecyclerView recyclerView;

        public ParentViewHolder(@NonNull View itemView) {
            super(itemView);

            tCategoryName = (TextView) itemView.findViewById(R.id.tv_item_category);
            bCategoryAll = (Button) itemView.findViewById(R.id.btn_item_category_all);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recyclerView);




        }
    }

}
