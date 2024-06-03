package com.example.giohang;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.giohang.Link.MainLink;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class SubmitRatingTask extends AsyncTask<Object, Void, String> {
    private Context context;

    // Thêm constructor để truyền vào tham chiếu đến Context
    public SubmitRatingTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Object... params) {
        float rating = (Float) params[0];
        String feedback = (String) params[1];
        return performRatingSubmission(rating, feedback);
    }

    private String performRatingSubmission(float rating, String feedback) {
        String result = "";
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL(MainLink.GlobalLink + "submit_rating.php");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);

            // Gửi dữ liệu đánh giá và phản hồi
            OutputStream outputStream = urlConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String postData = "rating=" + rating + "&feedback=" + feedback;
            writer.write(postData);
            writer.flush();
            writer.close();
            outputStream.close();

            // Đọc kết quả từ server
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            result = stringBuilder.toString();

        } catch (IOException e) {
            result = "Lỗi khi gửi đánh giá và phản hồi";
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        // Xử lý kết quả, ví dụ hiển thị thông báo
        Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
    }
}
