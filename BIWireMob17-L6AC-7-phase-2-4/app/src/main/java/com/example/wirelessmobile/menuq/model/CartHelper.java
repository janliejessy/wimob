package com.example.wirelessmobile.menuq.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import io.realm.RealmModel;

/**
 * Created by jessyjanlie on 5/28/17.
 */

public class CartHelper implements RealmModel {

    public static final String PRODUCT_INDEX = "PRODUCT_INDEX";

    private static List<FoodMenu> cart;

    public void callCartHelper(List<FoodMenu> cartParam){
        for(FoodMenu item : cartParam){
            this.cart.add(item);
        }
    }

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

    public static boolean isThisMenuOrdered(FoodMenu food){
        if(cart.contains(food)){ return true;}
        return false;
    }

    public static int getMenuPos(FoodMenu food){
        int i = 0;
        for(i = 0; i < cart.size(); i++){
            if(cart.get(i).getId() == food.getId()){
                return i ;
            }
        }
        return i;
    }

}
