package com.example.wirelessmobile.menuq.model;

import io.realm.RealmObject;

/**
 * Created by jessyjanlie on 5/29/17.
 */

public class OrderedMenu extends RealmObject {
    private int foodID;
    private int foodQty;

    public int getFoodQty() {
        return foodQty;
    }

    public void setFoodQty(int foodQty) {
        this.foodQty = foodQty;
    }

    public int getFoodID() {
        return foodID;
    }

    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }
}
