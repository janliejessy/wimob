package com.example.wirelessmobile.menuq.model;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by jessyjanlie on 5/8/17.
 */

public class DatabaseLogic {

    //realm declaration
    private static Realm realm = Realm.getDefaultInstance();

    //this function is to ensure that there are data in database.
    public static void openTheDatabase() throws IOException {
        Log.i("OPENING THE DATABASE >>", "ok");
        RealmResults<FoodMenu> result = realm.where(FoodMenu.class).findAllAsync();
        result.load();
        //if there is no data in the database, initialize one.
        if( result.isEmpty()){
            Log.i("NO DATA IN DATABASE >>", "initializing database");
            initialize_databases();
        }
    }

    private static void initialize_databases() throws IOException {
        ArrayList<FoodMenu> list = FoodMenu.getInitializationFoodMenu();
        for (int i = 0; i < list.size(); i++) {
            final FoodMenu data = list.get(i);
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    FoodMenu item = realm.createObject(FoodMenu.class);
                    item.setId(data.getId());
                    Log.i("GET ID >>", ""+ data.getId());
                    item.setTitle(data.getTitle());
                    item.setDesc(data.getDesc());
                    item.setCat(data.getCat());
                    item.setDrawable(data.getDrawable());
                    item.setPrice(data.getPrice());
                }
            });
        }
    }

    //this function retrieves all data from db according to the food's category
    public static FoodMenu get_data_from_database_from_id(int id) {
        //db query according to the id
        FoodMenu result = realm.where(FoodMenu.class).equalTo("id", id).findFirst();
        result.load();
        Log.i("GETTING IT FROM ID >>", "ok");
        return result;
    }

    //this function retrieves all data from db according to the food's category
    public static FoodMenu get_data_from_database_from_name(String id) {
        //db query according to the id
        FoodMenu result = realm.where(FoodMenu.class).equalTo("title", id).findFirst();
        result.load();
        Log.i("GETTING IT FROM ID >>", "ok");
        return result;
    }


    //this function retrieves all data from db according to the food's category
    public static List<FoodMenu> get_data_from_database(int cat) {
        //list declaration to pass the list back to recyclerView
        List<FoodMenu> data = new ArrayList<>();
        //db query according to the cat
        RealmResults<FoodMenu> result = realm.where(FoodMenu.class).equalTo("cat", cat).findAll();
        result.load();

        Log.i("LOADED WITH SIZE >>", String.valueOf(result.size()));
        //add all items in database to a new list to be display in the recyclerView
        for (FoodMenu itemInDatabase : result) {
            FoodMenu item = new FoodMenu();
            item.setTitle(itemInDatabase.getTitle());
            item.setDesc(itemInDatabase.getDesc());
            item.setCat(itemInDatabase.getCat());
            item.setPrice(itemInDatabase.getPrice());
            item.setDrawable(itemInDatabase.getDrawable());
            Log.i("DRAWABLE >>", String.valueOf(item.getDrawable()));
            data.add(item);
        }
        return data;
    }

    public static void add_cart_to_order_list(final List<FoodMenu> currentShoppingCart) {
        for (int i = 0; i < currentShoppingCart.size(); i++) {
            final FoodMenu currFoodInCart = currentShoppingCart.get(i);
            final OrderedMenu result = realm.where(OrderedMenu.class).equalTo("foodID", currFoodInCart.getId()).findFirst();
            //result.load();
            if (result == null) {
                Log.i("NO ITEM IN DB >>", "init");
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        OrderedMenu item = realm.createObject(OrderedMenu.class);
                        item.setFoodID(currFoodInCart.getId());
                        item.setFoodQty(currFoodInCart.getQty());
                        Log.i("item init >>", "" + item.getFoodID());

                    }
                });
            } else {
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                        Log.i("ITEM IS IN DB >>", "updating only qty");
                        result.setFoodQty(result.getFoodQty() + currFoodInCart.getQty());
                    }
                });
            }
            return_all_order();
        }
    }

    public static List<FoodMenu> get_final_order(){
        final List<FoodMenu> orderedList = new ArrayList<FoodMenu>();
        final RealmResults<OrderedMenu> result = realm.where(OrderedMenu.class).findAll();
        result.load();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {

                    for (OrderedMenu itemInDatabase : result) {
                    FoodMenu currItem = DatabaseLogic.get_data_from_database_from_id(itemInDatabase.getFoodID());
                    currItem.setQty(itemInDatabase.getFoodQty());
                        orderedList.add(currItem);

                }
                }
            });

        return orderedList;
    }

    public static void delete_order_from_database(){

        realm.beginTransaction();
                realm.delete(OrderedMenu.class);

        realm.commitTransaction();
    }

    //debugging purposes
    public static void return_all_order(){
        RealmResults<OrderedMenu> result = realm.where(OrderedMenu.class).findAll();
        result.load();
        Log.i("after set >>", ":");
        for (OrderedMenu itemInDatabase : result) {
            Log.i("ITEM is>>", " "+itemInDatabase.getFoodID());
            Log.i("QTY is>>", " "+itemInDatabase.getFoodQty());
            Log.i("--------", "-------");
        }
    }


}
