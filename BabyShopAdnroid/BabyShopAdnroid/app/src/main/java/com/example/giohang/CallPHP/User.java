package com.example.giohang.CallPHP;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.giohang.Home;
import com.example.giohang.Link.MainLink;
import com.example.giohang.Login;
import com.example.giohang.User.UserHolder;
import com.example.giohang.User.UserItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

public class User extends AsyncTask<String, ArrayList<UserItem>, ArrayList<UserItem>>
{
    private Context context;
    private int flag = 1;
    public User(Context context, int flag)
    {
        this.context = context;
        this.flag = flag;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<UserItem> doInBackground(String... strings) {
        if (flag == 1)
        {
            try
            {
                String username = strings[0];
                String password = strings[1];

                String link= MainLink.GlobalLink + "user.php";
                String data  = URLEncoder.encode("Username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");

                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                wr.write( data );
                wr.flush();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = "";

                // Read Server Response
                while((line = reader.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                String json = sb.toString();

                ArrayList<UserItem> userItems = new ArrayList<>();
                try
                {
                    JSONObject jsonObj = new JSONObject(json);
                    JSONArray users = jsonObj.getJSONArray("users");
                    for (int i = 0; i < users.length(); i++)
                    {
                        JSONObject obj = (JSONObject) users.get(i);
                        UserItem pf = new UserItem(obj.getString("Username"), obj.getString("Password"),
                                obj.isNull("Fullname") ? "" : obj.optString("Fullname"),
                                obj.isNull("Email")? "" : obj.getString("Email"),
                                obj.isNull("Birthday")? "" : obj.getString("Birthday"),
                                obj.optInt("Gender", 0), obj.optInt("Phone", 0),
                                obj.isNull("Address") ? "" : obj.getString("Address"),
                                obj.isNull("ImageUser") ? "" : obj.getString("ImageUser"), obj.optInt("Points", 0));
                        pf.setPassword(password);
                        userItems.add(pf);
                    }

                    return userItems;
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            catch(Exception e)
            {
                return null;
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<UserItem> s)
    {
        if (s != null)
        {
            UserHolder.saveUserItem(context, s);
        }
    }
}