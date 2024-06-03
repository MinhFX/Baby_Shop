package com.example.giohang.CallPHP;

import android.os.AsyncTask;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.giohang.DonHang;
import com.example.giohang.Adapter.AdapterOrderProduct;
import com.example.giohang.Cart.ProductCartItem;
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

public class OrderIDProduct extends AsyncTask<String, List<ProductCartItem>, List<ProductCartItem>>
{
    DonHang donHang;
    RecyclerView recyclerView;
    private int flag = 1;
    public OrderIDProduct(DonHang donHang, RecyclerView recyclerView, int flag)
    {
        this.donHang = donHang;
        this.recyclerView = recyclerView;
        this.flag = flag;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<ProductCartItem> doInBackground(String... strings) {
        if (flag == 1)
        {
            try
            {
                String orderID = strings[0];
                String link= MainLink.GlobalLink + "proorder.php";
                String data  = URLEncoder.encode("OrderID", "UTF-8") + "=" + URLEncoder.encode(orderID, "UTF-8");

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

                List<ProductCartItem> productCartItems = new ArrayList<>();

                try
                {
                    JSONObject jsonObj = new JSONObject(json);
                    JSONArray proCart = jsonObj.getJSONArray("ordersdetail");
                    for (int i = 0; i < proCart.length(); i++)
                    {
                        JSONObject obj = (JSONObject) proCart.get(i);
                        ProductCartItem pf = new ProductCartItem(obj.getInt("ProductID"), obj.getInt("Quantity"), obj.getInt("Price"), obj.getString("Productname"), obj.getString("ImageProduct"));
                        productCartItems.add(pf);
                    }
                    return productCartItems;
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
    protected void onPostExecute(List<ProductCartItem> s)
    {
        if (s != null)
        {
            StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(new AdapterOrderProduct(donHang, s));
        }
    }
}