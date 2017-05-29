package com.example.wirelessmobile.menuq;

/**
 * Created by Claudia on 04/04/2017.
 */

public class Food {
    private String name;
    private String desc;
    private int iconID;
    private int price;
    private int quantity;


    public Food(String name, String desc, int iconID, int price, int quantity) {
        super();
        this.name = name;
        this.desc = desc;
        this.iconID = iconID;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public int getIconID() {
        return iconID;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }


}