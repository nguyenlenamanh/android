package com.example.listview;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class FoodAdapter extends ArrayAdapter<Food> {

    Context context;
    int resource;
    ArrayList<Food> foods;

    public FoodAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Food> foods) {
        super(context, resource, foods);
        this.context = context;
        this.resource = resource;
        this.foods = foods;
    }

    @Override
    public int getCount() {
        return foods.size();
    }

    @Override
    public Food getItem(int i) {
        return foods.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;

        if(view == null) {
            view = LayoutInflater.from(context).inflate(resource,null);

            ImageView img = view.findViewById(R.id.imgFood);
            TextView name = view.findViewById(R.id.tvName);
            TextView price = view.findViewById(R.id.tvPrice);
            CheckBox selected = view.findViewById(R.id.cbSelected);

            viewHolder = new ViewHolder(img,name,price,selected);

            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder)view.getTag();
        }

        Food food = (Food)getItem(i);

        viewHolder.getImageView().setImageResource(food.getImage());
        viewHolder.getName().setText(food.getName());
        viewHolder.getPrice().setText(food.getPrice());
        viewHolder.getSelected().setChecked(food.getSelected());

        return view;
    }

    public class ViewHolder {
        ImageView imageView;
        TextView name,price;
        CheckBox selected;

        public ImageView getImageView() {
            return imageView;
        }

        public TextView getName() {
            return name;
        }

        public TextView getPrice() {
            return price;
        }

        public CheckBox getSelected() {
            return selected;
        }

        public ViewHolder(ImageView imageView, TextView name, TextView price, CheckBox selected) {
            this.imageView = imageView;
            this.name = name;
            this.price = price;
            this.selected = selected;
        }
    }
}
