package com.example.giohang.CallPHP;

import android.os.AsyncTask;
import android.widget.TextView;
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

public class CreateOrder extends AsyncTask<String, List<OrderItem>, String>
{
    private XacNhanGioHang context;
    private TextView orderID;
    private int flag = 1;
    public CreateOrder(XacNhanGioHang context, TextView orderID, int flag)
    {
        this.context = context;
        this.orderID = orderID;
        this.flag = flag;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        try
        {
            String username = strings[0];
            String total = strings[1];
            String fullname = strings[2];
            String address = strings[3];
            String phone = strings[4];
            String link= MainLink.GlobalLink + "createOrder.php";
            String data = "";
            if (flag == 1)
            {
                data  = URLEncoder.encode("Username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
                data += "&" + URLEncoder.encode("Total", "UTF-8") + "=" + URLEncoder.encode(total, "UTF-8");
                data += "&" + URLEncoder.encode("Fullname", "UTF-8") + "=" + URLEncoder.encode(fullname, "UTF-8");
                data += "&" + URLEncoder.encode("Address", "UTF-8") + "=" + URLEncoder.encode(address, "UTF-8");
                data += "&" + URLEncoder.encode("Phone", "UTF-8") + "=" + URLEncoder.encode(phone, "UTF-8");
            }
            else if (flag == 2)
            {
                data  = URLEncoder.encode("MaxID", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
            }

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

    @Override
    protected void onPostExecute(String s)
    {
        if (flag == 1)
        {
            if (s.equals("Lỗi"))
            {
                Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
            }
        }
        else if (flag == 2)
        {
            orderID.setText("Đơn hàng #"+s);
        }
    }
}