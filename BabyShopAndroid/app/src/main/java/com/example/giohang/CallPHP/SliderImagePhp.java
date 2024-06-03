package com.example.giohang.CallPHP;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ViewFlipper;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.giohang.Adapter.AdapterSliderImage;
import com.example.giohang.Home;
import com.example.giohang.Item.SliderBannerItem;
import com.example.giohang.Item.VoucherItem;
import com.example.giohang.Adapter.adapterVoucher;
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

public class SliderImagePhp extends AsyncTask<String, List<SliderBannerItem>, List<SliderBannerItem>>
{
    private Home context;
    private ViewFlipper vfSliderImage;
    private int flag = 0;
    public SliderImagePhp(Home context, ViewFlipper vfSliderImage, int flag)
    {
        this.context = context;
        this.vfSliderImage = vfSliderImage;
        this.flag = flag;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<SliderBannerItem> doInBackground(String... strings) {
        try
        {
            String link= MainLink.GlobalLink + "sliderimage.php";

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
            List<SliderBannerItem> sliderBannerItems = new ArrayList<>();
            try
            {
                JSONObject jsonObj = new JSONObject(json);
                JSONArray voucher = jsonObj.getJSONArray("sliderbanner");

                for (int i = 0; i < voucher.length(); i++)
                {
                    JSONObject obj = (JSONObject) voucher.get(i);
                    SliderBannerItem pf = new SliderBannerItem(obj.getInt("ID_banner"), obj.getString("Image"), obj.getInt("Status"));
                    sliderBannerItems.add(pf);
                }

                return sliderBannerItems;
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
    protected void onPostExecute(List<SliderBannerItem> s)
    {
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, LinearLayoutManager.HORIZONTAL);
//        recyclerView.setLayoutManager(manager);
//        recyclerView.setAdapter(new AdapterSliderImage(context, s));
//        vfSliderImage.addView(new AdapterSliderImage(context, s));
    }
}