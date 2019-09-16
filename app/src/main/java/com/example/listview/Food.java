package com.example.listview;

import java.io.Serializable;

public class Food implements Serializable {
    private int Image;
    private String Name;
    private String Price;

    public String getPrice() {
        return Price;
    }

    public void setImage(int image) {
        Image = image;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public void setPrice(String price) {
        Price = price;
    }

    private Boolean isSelected;

    public Food(int image, String name, String price ,Boolean isSelected) {
        Image = image;
        Name = name;
        Price = price;
        this.isSelected = isSelected;
    }

    public int getImage() {
        return Image;
    }

    public String getName() {
        return Name;
    }

    public Boolean getSelected() {
        return isSelected;
    }
}
