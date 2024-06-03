package com.example.giohang.CallPHP;

import android.os.AsyncTask;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.giohang.Link.MainLink;
import com.example.giohang.News;
import com.example.giohang.Adapter.AdapterNews;
import com.example.giohang.Item.NewsItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class tintuc extends AsyncTask<String, List<NewsItem>, List<NewsItem>>
{
    private News context;
    private RecyclerView recyclerView;
    private int flag = 0;
    public tintuc(News context, RecyclerView recyclerView, int flag)
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
    protected List<NewsItem> doInBackground(String... strings) {
        if (flag == 1)
        {
            try
            {
                String link= MainLink.GlobalLink + "news.php";

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

                List<NewsItem> newsItems = new ArrayList<>();
                try
                {
                    JSONObject jsonObj = new JSONObject(json);
                    JSONArray news = jsonObj.getJSONArray("news");
                    for (int i = 0; i < news.length(); i++)
                    {
                        JSONObject obj = (JSONObject) news.get(i);
                        NewsItem pf = new NewsItem(obj.getInt("NewsID"), obj.getInt("StaffID"), obj.getString("ImageNews"), obj.getString("Title"), obj.getString("Content")
                        , obj.getString("Date"), obj.getInt("Status"), obj.getString("Staffname"));
                        newsItems.add(pf);
                    }
                    return newsItems;
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
    protected void onPostExecute(List<NewsItem> s)
    {
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new AdapterNews(context, s));
    }
}
