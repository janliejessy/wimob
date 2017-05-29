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


public class BillAdapter extends RecyclerView.Adapter<BillAdapter.DerpHolder> {

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
    public BillAdapter (List<FoodMenu> listData, Context c) {
        this.inflater = LayoutInflater.from(c);
        this.listData = listData;
    }

    // Implement methods
    @Override
    public DerpHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.bill_item_view, parent, false); // change here for card_item or list_item
        return new DerpHolder(view);
    }

    @Override
    public void onBindViewHolder(DerpHolder holder, int position) {
        FoodMenu item = listData.get(position);
        holder.subtitle.setText(item.getTitle());
        holder.quantity.setText(""+item.getQty());
        holder.price.setText("$"+Double.toString(item.getPrice()));
        holder.ttlPrice.setText("$"+item.getPrice()*item.getQty());


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

        private TextView subtitle, quantity, price, ttlPrice;
        private View container;

        public DerpHolder(View itemView) {
            super(itemView);

            subtitle = (TextView) itemView.findViewById(R.id.item_txtName);
            quantity = (TextView) itemView.findViewById(R.id.item_txtQuantity);
            price = (TextView) itemView.findViewById(R.id.item_txtPrice);
            ttlPrice = (TextView) itemView.findViewById(R.id.item_txt_ttl_price);
            container = itemView.findViewById(R.id.cont_item_root);
        }

    }

}