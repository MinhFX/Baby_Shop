package com.example.giohang.CallPHP;

import android.os.AsyncTask;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.giohang.DanhMuc;
import com.example.giohang.Adapter.AdapterProduct;
import com.example.giohang.DanhMuc;
import com.example.giohang.Item.ProductItem;
import com.example.giohang.Link.MainLink;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class Product extends AsyncTask<String, List<ProductItem>, List<ProductItem>>
{
    private DanhMuc context;
    private RecyclerView recyclerView;
    private int flag = 0;
    public Product(DanhMuc context, RecyclerView recyclerView, int flag)
    {
        this.context = context;
        this.recyclerView = recyclerView;
        this.flag = flag;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<ProductItem> doInBackground(String... strings) {
        if (flag == 0)
        {
            try {
                String procategoryID = (String) strings[0];
                String link = MainLink.GlobalLink + "product.php?proid=" + procategoryID;
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(link));

                HttpResponse response = client.execute(request);
                BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                StringBuilder sb = new StringBuilder();
                String line = "";

                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    break;
                }

                String json = sb.toString();

                List<ProductItem> productItems = new ArrayList<>();
                try
                {
                    JSONObject jsonObj = new JSONObject(json);
                    JSONArray products = jsonObj.getJSONArray("products");
                    for (int i = 0; i < products.length(); i++)
                    {
                        JSONObject obj = (JSONObject) products.get(i);
                        ProductItem pf = new ProductItem(obj.getInt("ProductID"), obj.getString("Productname"), obj.getString("ImageProduct"),
                                obj.getString("Describes"), obj.getInt("Price"), obj.getInt("Quantity"),obj.getInt("Status"), obj.getInt("Category_ID"));
                        productItems.add(pf);
                    }
                    return productItems;
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
        else if (flag == 1)
        {
            try
            {
                String link=MainLink.GlobalLink + "product.php";

                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = "";

                // Read Server Response
                while((line = reader.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                String json = sb.toString();

                List<ProductItem> productItems = new ArrayList<>();
                try
                {
                    JSONObject jsonObj = new JSONObject(json);
                    JSONArray products = jsonObj.getJSONArray("products");
                    for (int i = 0; i < products.length(); i++)
                    {
                        JSONObject obj = (JSONObject) products.get(i);
                        ProductItem pf = new ProductItem(obj.getInt("ProductID"), obj.getString("Productname"), obj.getString("ImageProduct"),
                                obj.getString("Describes"), obj.getInt("Price"), obj.getInt("Quantity"),obj.getInt("Status"), obj.getInt("Category_ID"));
                        productItems.add(pf);
                    }
                    return productItems;
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
    protected void onPostExecute(List<ProductItem> s)
    {
        if (s != null)
        {
            StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(new AdapterProduct(context, s));
        }
    }
}
