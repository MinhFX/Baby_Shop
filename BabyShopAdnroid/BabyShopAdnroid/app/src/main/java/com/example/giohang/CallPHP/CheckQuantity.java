package com.example.giohang.CallPHP;

import android.os.AsyncTask;
import android.widget.Toast;

import com.example.giohang.Link.MainLink;
import com.example.giohang.XacNhanGioHang;
import com.example.giohang.Item.OrderItem;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

public class CheckQuantity extends AsyncTask<String, List<OrderItem>, String>
{
    private XacNhanGioHang context;
    private int flag = 1;
    public CheckQuantity(XacNhanGioHang context, int flag)
    {
        this.context = context;
        this.flag = flag;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        if (flag == 1)
        {
            try
            {
                String productID = strings[0];
                String quantity = strings[1];
                String link= MainLink.GlobalLink + "checkProductQuantity.php";
                String data  = URLEncoder.encode("ProductID", "UTF-8") + "=" + URLEncoder.encode(productID, "UTF-8");
                data += "&" + URLEncoder.encode("Quantity", "UTF-8") + "=" + URLEncoder.encode(quantity, "UTF-8");

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
                return sb.toString();
            }
            catch(Exception e)
            {
                return null;
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s)
    {
        if (s.equals("Lỗi"))
        {
            Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        }
    }
}