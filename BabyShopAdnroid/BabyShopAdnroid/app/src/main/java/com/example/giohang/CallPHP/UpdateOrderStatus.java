package com.example.giohang.CallPHP;

import android.os.AsyncTask;
import android.widget.Toast;

import com.example.giohang.Chitietdonhang;
import com.example.giohang.Item.OrderItem;
import com.example.giohang.Link.MainLink;
import com.example.giohang.User.UserHolder;
import com.example.giohang.User.UserItem;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class UpdateOrderStatus extends AsyncTask<String, List<OrderItem>, String>
{
    private Chitietdonhang context;
    ArrayList<UserItem> userItems;
    private int flag = 1;
    public UpdateOrderStatus(Chitietdonhang context, int flag)
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
                String orderID = strings[0];
                String status = strings[1];
                String link= MainLink.GlobalLink + "updateOrderStatus.php";
                String data  = URLEncoder.encode("OrderID", "UTF-8") + "=" + URLEncoder.encode(orderID, "UTF-8");
                data += "&" + URLEncoder.encode("Status", "UTF-8") + "=" + URLEncoder.encode(status, "UTF-8");

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
        else if (s.equals("success"))
        {
            userItems = UserHolder.loadUserItem(context);
            Toast.makeText(context, "Hủy đơn thành công", Toast.LENGTH_SHORT).show();
            context.finish();
        }
    }
}