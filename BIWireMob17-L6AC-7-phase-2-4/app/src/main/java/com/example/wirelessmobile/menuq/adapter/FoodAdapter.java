package com.example.wirelessmobile.menuq.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wirelessmobile.menuq.R;
import com.example.wirelessmobile.menuq.model.FoodMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Claudia on 06/05/2017.
 */

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodHolder> {

    //declaration
    private List<FoodMenu> listData;
    private LayoutInflater inflater;

    private itemClickCallback itemClickCallback;

    public interface itemClickCallback {
        void onItemClick(int p);
    }

    public void setItemClickCallback(final itemClickCallback itemClickCallback) {
        this.itemClickCallback = itemClickCallback;
    }

    // Constructor
    public FoodAdapter(List<FoodMenu> listData, Context c) {
        this.inflater = LayoutInflater.from(c);
        this.listData = listData;
    }

    // Implement methods
    @Override
    public FoodHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_item, parent, false); // change here for card_item or list_item
        return new FoodHolder(view);
    }

    //this function is to set the list view corresponding to the datas retrieved
    @Override
    public void onBindViewHolder(FoodHolder holder, int position) {
        FoodMenu item = listData.get(position);
        holder.name.setText(item.getTitle());
        holder.desc.setText(item.getDesc());
        holder.price.setText("$" + item.getPrice());
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
    class FoodHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        //declaration
        private TextView name;
        private TextView desc;
        private TextView price;
        private ImageView thumbnail;
        private View container;
        private Button load;

        public FoodHolder(View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.lbl_item_name);
            desc = (TextView)itemView.findViewById(R.id.lbl_item_desc);
            price = (TextView)itemView.findViewById(R.id.lbl_item_price);
            thumbnail = (ImageView)itemView.findViewById(R.id.im_item_icon);
            load = (Button)itemView.findViewById(R.id.btn_card_load);
            load.setOnClickListener(this);
            container = itemView.findViewById(R.id.cont_item_root);

        }


        @Override
        public void onClick(View v) {
            // if (v.getId() == R.id.cont_item_root) {
            if (v.getId() == R.id.btn_card_load) {
                itemClickCallback.onItemClick(getAdapterPosition());
            }
        }
    }
}
