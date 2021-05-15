package com.example.appbanhoa.activity.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ListAdapter;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.appbanhoa.R;
import com.example.appbanhoa.activity.adapter.Loaihadapter;
import com.example.appbanhoa.activity.model.Loaih;
import com.example.appbanhoa.activity.ultil.sever;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewmanhinhchinh;
    NavigationView navigationView;
    ListView listViewmanhinhchinh;
    DrawerLayout drawerLayout;
    Loaihadapter loaihadapter;
    private ListAdapter mAdapter;
    ArrayList<Loaih> mangloaih;
    int id = 0;
    String tenloaih = "";
    String hinhanhloaih = "";

    @RequiresApi(api = Build.VERSION_CODES.ECLAIR_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa(); // anhXa
        ActionBar();
        ActionViewFlipper();
        Getdulieuloaih();
    }

    private void Getdulieuloaih() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(sever.DuongdanLoaih, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    int totalData = response.length();

                    for (int i = 0; i < totalData; i++){
                       try {
                           JSONObject jsonObject = response.getJSONObject(i);
                           id = jsonObject.getInt("id");
                           tenloaih = jsonObject.getString("tenloaihoa");
                           hinhanhloaih = jsonObject.getString("hinhanhloaihoa");
                           mangloaih.add(new Loaih(id, tenloaih, hinhanhloaih));
                           loaihadapter.notifyDataSetChanged();
                       } catch (JSONException e) {
                           e.printStackTrace();
                       }
                    }
                    mangloaih.add(new Loaih(0,"Liên hệ","https://capnuocbenthanh.com/images/dtlienhe_1.jpg"));
                    mangloaih.add(new Loaih(0,"Thông tin","https://timviec365.vn/pictures/images/thong-tin-lien-lac-la-gi-1.jpg"));

                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonArrayRequest);
    }

    @RequiresApi(api = Build.VERSION_CODES.ECLAIR_MR1)
    private void ActionViewFlipper() {
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://cdn.brvn.vn/news/480px/2018/14735_VinFast.jpg");
        mangquangcao.add("https://cdn.tgdd.vn/Files/2019/05/14/1166730/durex_760x367.jpg");
        mangquangcao.add("https://didongviet.vn/blog/wp-content/uploads/2020/12/DSC02559.jpg");
        mangquangcao.add("https://duytranwatch.vn/wp-content/uploads/2019/06/dong-ho-thuy-sy-3.jpg");
        for (int i = 0; i < mangquangcao.size(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.get().load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }

        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);
    }

    private void ActionBar() {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            });
    }

    private void Anhxa() {
        toolbar = (Toolbar) findViewById(R.id.tbmanhinhchinh);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewlipper);
        recyclerViewmanhinhchinh = (RecyclerView) findViewById(R.id.re);

//        mAdapter = new ListAdapter(null, getApplicationContext());
        recyclerViewmanhinhchinh.setAdapter(mAdapter);

        navigationView = (NavigationView) findViewById(R.id.NavigationView);
        listViewmanhinhchinh = (ListView) findViewById(R.id.listviewmanhinhchinh);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);


        mangloaih = new ArrayList<>();
        mangloaih.add(0,new Loaih(0,"Trang Chính","https://vietadsgroup.vn/Uploads/files/trangchu-la-gi.png"));
        loaihadapter = new Loaihadapter(mangloaih, getApplicationContext());


        listViewmanhinhchinh.setAdapter(loaihadapter);
//        mangloaih = new ArrayList<>();
//        loaihadapter = new Loaihadapter(mangloaih,getApplicationContext());
//        listViewmanhinhchinh.setAdapter(loaihadapter);
    }
}