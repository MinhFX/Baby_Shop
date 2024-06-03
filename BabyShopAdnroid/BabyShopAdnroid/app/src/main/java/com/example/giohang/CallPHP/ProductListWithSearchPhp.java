package com.example.giohang.CallPHP;

import android.os.AsyncTask;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.giohang.Adapter.AdapterProduct;
import com.example.giohang.DanhMuc;
import com.example.giohang.Item.ProductItem;
import com.example.giohang.Link.MainLink;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class ProductListWithSearchPhp extends AsyncTask<String, List<ProductItem>, List<ProductItem>>
{
    private String NameProduct;
    private DanhMuc context;
    private RecyclerView recyclerView;
    private int flag = 0;
    public ProductListWithSearchPhp(DanhMuc context, RecyclerView recyclerView, int flag, String txtSearch)
    {
        this.context = context;
        this.recyclerView = recyclerView;
        this.flag = flag;
        this.NameProduct = txtSearch;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<ProductItem> doInBackground(String... strings) {

        try
        {
            String link= MainLink.GlobalLink + "productwithsearch.php?Search="+ NameProduct;

            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);


            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = null;

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
        return null;
    }

    @Override
    protected void onPostExecute(List<ProductItem> s)
    {
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new AdapterProduct(context, s));
    }
}
