package com.example.wirelessmobile.menuq.adapter;

/**
 * Created by jessyjanlie on 5/28/17.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wirelessmobile.menuq.model.CartHelper;
import com.example.wirelessmobile.menuq.model.FoodMenu;
import com.example.wirelessmobile.menuq.R;

import java.util.ArrayList;
import java.util.List;

public class DerpAdapter extends RecyclerView.Adapter<DerpAdapter.DerpHolder> {

    private List<FoodMenu> listData;
    private LayoutInflater inflater;

    private itemClickCallback itemClickCallback;

    public interface itemClickCallback {
        void onItemClick(int p);
        void onSecondaryIconClick(int p);
    }

    public void setItemClickCallback(final itemClickCallback itemClickCallback) {
        this.itemClickCallback = itemClickCallback;
    }
    // Constructor
    public DerpAdapter (List<FoodMenu> listData, Context c) {
        this.inflater = LayoutInflater.from(c);
        this.listData = listData;
    }

    // Implement methods
    @Override
    public DerpHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cart_card_item, parent, false); // change here for card_item or list_item
        return new DerpHolder(view);
    }

    @Override
    public void onBindViewHolder(DerpHolder holder, int position) {
        FoodMenu item = listData.get(position);
        holder.subtitle.setText(item.getTitle());
        holder.quantity.setText("qty: " + item.getQty());
        holder.thumbnail.setImageResource(item.getDrawable());

    }

    // Setter
    public void setListData(ArrayList<FoodMenu> exerciseList) {
        this.listData.clear();
        this.listData.addAll(exerciseList);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    // ViewHolder
    class DerpHolder extends RecyclerView.ViewHolder {

        private TextView subtitle, quantity;
        private ImageView thumbnail;
        private View container;
        private ImageButton remove;

        public DerpHolder(View itemView) {
            super(itemView);

            subtitle = (TextView) itemView.findViewById(R.id.lbl_item_subtitle);
            quantity = (TextView) itemView.findViewById(R.id.qty);
            thumbnail = (ImageView) itemView.findViewById(R.id.im_item_icon);
            container = itemView.findViewById(R.id.cont_item_root);

        }
    }
}