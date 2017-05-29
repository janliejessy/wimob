package com.example.wirelessmobile.menuq.model;

import android.util.Log;

import com.example.wirelessmobile.menuq.R;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmObject;

/**
 * Created by jessyjanlie on 5/8/17.
 */

public class FoodMenu extends RealmObject{

    //declaration
    private int id;
    private String title;
    private String desc;
    /**
     * Category:
     * 0 all
     * 1 appetizer
     * 2 main course
     * 3 dessert
     * 4 drinks
     */
    private int cat;
    private int drawable;
    private double price;
    private boolean favourite = false;
    private boolean selectedToCart;
    private int qty;

    public FoodMenu(){
        id = 0;
        title = "";
        desc = "";
        cat = 0;
        price = 0;
        drawable = 0;
        qty = 0;
    }


    public FoodMenu(int c_id, String c_title, String c_desc, int c_cat, int c_draw, double c_price){
        id = c_id;
        title = c_title;
        desc = c_desc;
        cat = c_cat;
        drawable = c_draw;
        price = c_price;
        selectedToCart = false;
        qty = 0;
    }


    //SETTER & GETTER
    public boolean isFavourite() {
        return favourite;
    }
    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getCat() {
        return cat;
    }
    public void setCat(int cat) {
        this.cat = cat;
    }
    public int getDrawable() {
        return drawable;
    }
    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }
    public boolean isSelectedToCart() {
        return selectedToCart;
    }
    public void setSelectedToCart(boolean sel) {
        this.selectedToCart = sel;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getQty() {
        return qty;
    }
    public void setQty(int qty) {
        this.qty = qty;
    }
    public void updateQty(int qty) {
        this.qty += qty;
    }


    //database initialization
    public static ArrayList<FoodMenu> getInitializationFoodMenu() {
        ArrayList<FoodMenu> data =  new ArrayList();
        data.add(new FoodMenu(1, "Seasonal Fruit", "This season's fruit is apple", 1, R.drawable.apples, 10.60) );
        data.add(new FoodMenu(2, "Tofu Skewer", "Deep fried tofu served with dry seaweed", 1, R.drawable.cake, 5.99));
        data.add(new FoodMenu(3, "Singaporean Fishball", "Deep fried Chinese style dumplings", 1, R.drawable.cake, 8.65));
        data.add(new FoodMenu(4, "Wakame Salad", "Japanese style seaweed salad", 1, R.drawable.salad, 4.65));
        data.add(new FoodMenu(5, "Duck Breast", "Served with roasted pumpkin, celery puree and dried mustard", 2, R.drawable.skewers, 12.65));
        data.add(new FoodMenu(6, "Griller Perch", "Perch that is grilled", 2, R.drawable.rolls, 10.99));
        data.add(new FoodMenu(7, "Chocolate Cake", "Marvelous chocolate marble cake with rhum filling", 3, R.drawable.cake, 4.65));
        data.add(new FoodMenu(8, "Avocado Juice", "Served with chocolate syrup and honey", 4, R.drawable.drinks, 3.65));

        return data;
    }

}
