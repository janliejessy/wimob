package com.example.wirelessmobile.menuq.model;

/**
 * Created by jessyjanlie on 5/28/17.
 */

public class ListItem {
    private String title;
    private String subtitle;
    private int imageResId;
    private boolean favourite = false;

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

}
