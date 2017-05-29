package com.example.wirelessmobile.menuq.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import io.realm.RealmModel;
import io.realm.RealmObject;

/**
 * Created by jessyjanlie on 5/28/17.
 */

public class CartHelper extends RealmObject implements Cloneable{

    @Override
    protected Object clone() throws CloneNotSupportedException{
        return super.clone();
    }


    public static final String PRODUCT_INDEX = "PRODUCT_INDEX";


    public static void setCart(List<FoodMenu> cart) {
        CartHelper.cart = cart;
    }

    private static List<FoodMenu> cart;
    private int id;


    public static List<FoodMenu> getCart() {
        if(cart == null) {
            cart = new ArrayList<FoodMenu>();
        }

        return cart;
    }

    public static List<FoodMenu> deleteCart(){
        cart = new ArrayList<FoodMenu>();
        return cart;
    }

}
