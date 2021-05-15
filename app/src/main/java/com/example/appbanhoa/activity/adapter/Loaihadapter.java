package com.example.appbanhoa.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appbanhoa.R;
import com.example.appbanhoa.activity.model.Loaih;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Loaihadapter extends BaseAdapter {
    ArrayList<Loaih> arrayListloaih;
    Context context;

    public Loaihadapter(ArrayList<Loaih> arrayListloaih, Context context) {
        this.arrayListloaih = arrayListloaih;
        this.context = context;
    }


    @Override
    public int getCount() {

        return arrayListloaih.size();
    }

    @Override
    public Object getItem(int i) {

        return arrayListloaih.get(i);
    }

    @Override
    public long getItemId(int i) {

        return i;
    }
    public class ViewHolder{
    TextView txttenloaih;
    ImageView imgloaih;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_lisview_loaih,null);
            viewHolder.txttenloaih = (TextView) view.findViewById(R.id.textviewloaih);
            viewHolder.imgloaih = (ImageView) view.findViewById(R.id.imageviewloaih);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
            Loaih loaih = (Loaih) getItem(i);
            viewHolder.txttenloaih.setText(loaih.getTenloaih());
            Picasso.get().load(loaih.getHinhanhloaih())
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.error)
                    .into(viewHolder.imgloaih);
        }
        return view;
    }
}
