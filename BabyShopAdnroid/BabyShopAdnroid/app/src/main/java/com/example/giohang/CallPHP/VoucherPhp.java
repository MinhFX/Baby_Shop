package com.example.giohang.CallPHP;

import android.os.AsyncTask;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.giohang.Home;
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

public class VoucherPhp extends AsyncTask<String, List<VoucherItem>, List<VoucherItem>>
{
    private Home context;
    private RecyclerView recyclerView;
    private int flag = 0;
    public VoucherPhp(Home context, RecyclerView recyclerView, int flag)
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
    protected List<VoucherItem> doInBackground(String... strings) {
        try
        {
            String link= MainLink.GlobalLink + "voucher.php";

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
            List<VoucherItem> voucherItems = new ArrayList<>();
            try
            {
                JSONObject jsonObj = new JSONObject(json);
                JSONArray voucher = jsonObj.getJSONArray("Vouchers");

                for (int i = 0; i < voucher.length(); i++)
                {
                    JSONObject obj = (JSONObject) voucher.get(i);
                    VoucherItem pf = new VoucherItem(obj.getInt("VoucherID"), obj.getString("VoucherName"), obj.getInt("Points"),
                            obj.getInt("DiscountPrice"), obj.getInt("Category_ID"), obj.getInt("Quantity"),obj.getInt("Status"));
                    voucherItems.add(pf);
                }

                return voucherItems;
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
    protected void onPostExecute(List<VoucherItem> s)
    {
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new adapterVoucher(context, s));
    }
}