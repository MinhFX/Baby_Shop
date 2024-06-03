package com.example.giohang.Cart;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CartHolder {
    private static final String CART_PREFERENCES = "CartPreferences";
    private static final String CART_ITEMS_KEY = "CartItems";

    public static void saveCartItems(Context context, ArrayList<ProductCartItem> cartItems) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(CART_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(cartItems);
        editor.putString(CART_ITEMS_KEY, json);
        editor.apply();
    }

    public static ArrayList<ProductCartItem> loadCartItems(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(CART_PREFERENCES, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(CART_ITEMS_KEY, null);
        Type type = new TypeToken<ArrayList<ProductCartItem>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public static void clearCart(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(CART_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
