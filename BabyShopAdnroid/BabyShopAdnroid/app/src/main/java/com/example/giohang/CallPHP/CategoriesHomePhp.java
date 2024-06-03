package com.example.giohang.CallPHP;

        import android.os.AsyncTask;
        import android.util.Log;

        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;
        import androidx.recyclerview.widget.StaggeredGridLayoutManager;

        import com.example.giohang.Adapter.AdapterProduct;
        import com.example.giohang.Item.CategoriesHomeItem;
        import com.example.giohang.Adapter.adapterCategoriesHome;

        import com.example.giohang.Home;
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

public class CategoriesHomePhp extends AsyncTask<String, List<CategoriesHomeItem>, List<CategoriesHomeItem>>
{
    private Home context;
    private RecyclerView recyclerView;
    private int flag = 0;
    public CategoriesHomePhp(Home context, RecyclerView recyclerView, int flag)
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
    protected List<CategoriesHomeItem> doInBackground(String... strings) {

        try
        {
            String link= MainLink.GlobalLink + "CategoriesHome.php";

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
            List<CategoriesHomeItem> categoriesHomeItems = new ArrayList<>();
            try
            {
                JSONObject jsonObj = new JSONObject(json);
                JSONArray icon = jsonObj.getJSONArray("categorieshome");

                for (int i = 0; i < icon.length(); i++)
                {
                    JSONObject obj = (JSONObject) icon.get(i);
                    CategoriesHomeItem pf = new CategoriesHomeItem(obj.getInt("CategoryID"), obj.getString("CategoryName"), obj.getString("Image"));
                    categoriesHomeItems.add(pf);

                }

                return categoriesHomeItems;

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
    protected void onPostExecute(List<CategoriesHomeItem> s)
    {

        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new adapterCategoriesHome(context, s));

    }
}