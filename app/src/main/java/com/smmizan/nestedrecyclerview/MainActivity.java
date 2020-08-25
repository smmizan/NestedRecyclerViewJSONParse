package com.smmizan.nestedrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.smmizan.nestedrecyclerview.adapter.ParentAdapter;
import com.smmizan.nestedrecyclerview.model.ItemCategory;
import com.smmizan.nestedrecyclerview.model.ItemSubCategory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    ArrayList<ItemCategory> itemCategorys;


    //private static final String URL = "https://smmizan.com/appWorld/EcomApp/product_list.php";
    private static final String URL = "https://smmizan.com/appWorld/json/nestedjson.txt";


    private ArrayList<ItemCategory> productsList;
    private ArrayList<ItemSubCategory> itemSubCategoryArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setHasFixedSize(true);


        itemCategorys = new ArrayList<>();
        ArrayList<ItemSubCategory> itemSubCategories = new ArrayList<>();
        ArrayList<ItemSubCategory> itemSubCategories2 = new ArrayList<>();
        ArrayList<ItemSubCategory> itemSubCategories3 = new ArrayList<>();



        itemSubCategories.add(new ItemSubCategory("shart","200","10"));
        itemSubCategories.add(new ItemSubCategory("pant","600","6"));
        itemSubCategories.add(new ItemSubCategory("Panjabi","1500","9"));
        itemSubCategories.add(new ItemSubCategory("Lungi","400","1"));
        itemSubCategories.add(new ItemSubCategory("Gamca","150","30"));



        itemSubCategories2.add(new ItemSubCategory("jama","2500","10"));
        itemSubCategories2.add(new ItemSubCategory("tops","400","5"));
        itemSubCategories2.add(new ItemSubCategory("bra","450","12"));
        itemSubCategories2.add(new ItemSubCategory("panty","300","6"));
        itemSubCategories2.add(new ItemSubCategory("burka","1500","10"));


        itemSubCategories3.add(new ItemSubCategory("Mom","140","50"));
        itemSubCategories3.add(new ItemSubCategory("Golap","150","10"));
        itemSubCategories3.add(new ItemSubCategory("Gift","500","30"));

        itemCategorys.add(new ItemCategory("Men",itemSubCategories));
        itemCategorys.add(new ItemCategory("Women",itemSubCategories2));
        itemCategorys.add(new ItemCategory("Honymoon",itemSubCategories3));


//        adapter = new ParentAdapter(this,itemCategorys);
//        recyclerView.setAdapter(adapter);


        productsList = new ArrayList<>();
        itemSubCategoryArrayList = new ArrayList<>();


        loadingProducts();

    }



    private void loadingProducts(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            //JSONObject jsonObject2 = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("result");


                            //Log.e("mizan",jsonArray.toString());
                            JSONArray jsonArray2 = jsonObject.getJSONArray("category_item");
                            //Log.e("mizan2",jsonArray2.toString());




                            for(int i = 0; i < jsonArray.length(); i++){

                                JSONObject jsnObject = jsonArray.getJSONObject(i);



                                for(int j = 0; j < jsonArray2.length(); j++){

                                    JSONObject jsonObject1 = jsonArray2.getJSONObject(j);


                                    ItemSubCategory itemSubCategory = new ItemSubCategory(
                                            jsonObject1.getString("name"),
                                            jsonObject1.getString("price"),
                                            jsonObject1.getString("code")
                                    );

                                    itemSubCategoryArrayList.add(itemSubCategory);

                                }


                                ItemCategory itemCategorys = new ItemCategory(
                                        jsnObject.getString("category_name"),
                                        itemSubCategoryArrayList
                                );

                                productsList.add(itemCategorys);
                            }

                            adapter = new ParentAdapter(getApplicationContext(),itemCategorys);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();

                            //Log.e("mizan", jsonArray2.toString());


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}