package com.example.wirelessmobile.menuq.model;

/**
 * Created by jessyjanlie on 5/28/17.
 */

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;

import com.example.wirelessmobile.menuq.R;

/**
 * Created by Claudia on 06/05/2017.
 */

public class DerpData {

    private static final String[] subtitles = {"Kimchi", "Bottle", "Coke", "Book", "Red Hat", "Potato Chips", "Blue Sofa"};

    public static int icon = R.drawable.ic_tag_faces_black_36dp;

    public static List<ListItem> getListData() {
        List<ListItem> data = new ArrayList<>();

        // Repeat process 4 times, so that we have enough data to demonstratea scrollable RV
        for (int x = 0; x < 4; x++) {
            // Create ListItem with dummy data, then add them to our List
            for (int i = 0; i < subtitles.length; i++) {
                ListItem item = new ListItem();
                item.setSubtitle(subtitles[i]);
                data.add(item);
            }
        }

        return data;
    }
}