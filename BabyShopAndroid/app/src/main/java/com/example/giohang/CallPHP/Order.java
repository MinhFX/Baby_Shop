package com.example.giohang.CallPHP;

import android.os.AsyncTask;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.giohang.Adapter.AdapterOrder;
import com.example.giohang.Adapter.AdapterProduct;
import com.example.giohang.Chitietdonhang;
import com.example.giohang.Item.OrderItem;
import com.example.giohang.Link.MainLink;

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
import java.util.List;

public class Order extends AsyncTask<String, List<OrderItem>, List<OrderItem>>
{
    private int flag = 1;
    public Order(int flag)
    {
        this.flag = flag;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<OrderItem> doInBackground(String... strings) {
        if (flag == 1)
        {
            try
            {
                String username = strings[0];
                String link= MainLink.GlobalLink + "order.php";
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

                List<OrderItem> orderItems = new ArrayList<>();

                try
                {
                    JSONObject jsonObj = new JSONObject(json);
                    JSONArray orders = jsonObj.getJSONArray("orders");
                    for (int i = 0; i < orders.length(); i++)
                    {
                        JSONObject obj = (JSONObject) orders.get(i);
                        OrderItem pf = new OrderItem(obj.getInt("OrderID"), obj.optInt("StaffID", 0), obj.getString("Username"),
                                obj.getString("Fullname"), obj.getString("Address"), obj.optInt("Phone", 0), obj.getInt("Total"),
                                obj.getString("OrderDate"), obj.getString("TimeOrder"), obj.getInt("Status"));
                        orderItems.add(pf);
                    }
                    return orderItems;
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
    protected void onPostExecute(List<OrderItem> s)
    {
    }
}