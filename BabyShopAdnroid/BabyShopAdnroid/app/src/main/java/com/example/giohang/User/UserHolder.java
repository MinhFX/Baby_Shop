package com.example.giohang.User;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class UserHolder {
    private static final String USER_ITEM = "UserItem";
    private static final String USER_Preferences = "UserPreferences";

    public static void saveUserItem(Context context, ArrayList<UserItem> cartItems) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_Preferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(cartItems);
        editor.putString(USER_ITEM, json);
        editor.apply();
    }

    public static ArrayList<UserItem> loadUserItem(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_Preferences, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(USER_ITEM, null);
        Type type = new TypeToken<ArrayList<UserItem>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public static void clearUser(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_Preferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
