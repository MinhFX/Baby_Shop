package com.example.giohang.CallPHP;

import android.os.AsyncTask;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.giohang.DanhMuc;
import com.example.giohang.Adapter.AdapterProCategory;
import com.example.giohang.Item.ProCategoryItem;
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

public class Product_Category extends AsyncTask<String, List<ProCategoryItem>, List<ProCategoryItem>>
{
    private int flag = 0;
    public Product_Category(int flag)
    {
        this.flag = flag;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<ProCategoryItem> doInBackground(String... strings) {
        try
        {
            String link= MainLink.GlobalLink + "procategory.php";

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

            List<ProCategoryItem> proCategoryItems = new ArrayList<>();
            try
            {
                JSONObject jsonObj = new JSONObject(json);
                JSONArray category_products = jsonObj.getJSONArray("procategory");
                for (int i = 0; i < category_products.length(); i++)
                {
                    JSONObject obj = (JSONObject) category_products.get(i);
                    ProCategoryItem pf = new ProCategoryItem(obj.getInt("Category_ID"), obj.getString("Category_name"));
                    proCategoryItems.add(pf);
                }
                return proCategoryItems;
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
    protected void onPostExecute(List<ProCategoryItem> s)
    {

    }
}
