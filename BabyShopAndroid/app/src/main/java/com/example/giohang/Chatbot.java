package com.example.giohang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.giohang.Adapter.MessageAdapter;
import com.example.giohang.Item.MessageItem;
import com.example.giohang.helpers.SendMessageInBg;
import com.example.giohang.interfaces.BotReply;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.dialogflow.v2.DetectIntentResponse;
import com.google.cloud.dialogflow.v2.QueryInput;
import com.google.cloud.dialogflow.v2.SessionName;
import com.google.cloud.dialogflow.v2.SessionsClient;
import com.google.cloud.dialogflow.v2.SessionsSettings;
import com.google.cloud.dialogflow.v2.TextInput;
import com.google.common.collect.Lists;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Chatbot extends AppCompatActivity implements BotReply {
    private EditText textText1;
    private ImageView btnBack, btn_sendtext;
    private RecyclerView recycleView;
    private MessageAdapter messageAdapter;
    private List<MessageItem> messageItemList;
    private SessionName sessionName;
    private SessionsClient sessionsClient;
    private String message;
    private String uuid = UUID.randomUUID().toString();
    private String TAG = "ChatbotBabyShop";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatbox);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        textText1 = findViewById(R.id.textText1);
        btn_sendtext = findViewById(R.id.btn_sendtext);
        recycleView = findViewById(R.id.recycleView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycleView.setLayoutManager(linearLayoutManager);

        messageItemList = new ArrayList<>();
        messageAdapter = new MessageAdapter(messageItemList, this);
        messageAdapter.setData(messageItemList);
        recycleView.setAdapter(messageAdapter);

        btn_sendtext.setOnClickListener((view) -> {
            message = textText1.getText().toString();
            if (!message.isEmpty()) {
                messageItemList.add(new MessageItem(message, false));
                textText1.setText("");
                sendMessageToBot(message);
                Objects.requireNonNull(recycleView.getAdapter()).notifyDataSetChanged();
                Objects.requireNonNull(recycleView.getLayoutManager())
                        .scrollToPosition(messageItemList.size() - 1);
            }
            else {
                Toast.makeText(Chatbot.this, "Please enter text!", Toast.LENGTH_SHORT).show();
            }
        });

        setUpBot();
    }

    private void setUpBot() {
        try {
            InputStream stream = this.getResources().openRawResource(R.raw.babyshop);
            GoogleCredentials credentials = GoogleCredentials.fromStream(stream)
                    .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
            String projectId = ((ServiceAccountCredentials) credentials).getProjectId();

            SessionsSettings.Builder settingsBuilder = SessionsSettings.newBuilder();
            SessionsSettings sessionsSettings = settingsBuilder.setCredentialsProvider(
                    FixedCredentialsProvider.create(credentials)).build();
            sessionsClient = SessionsClient.create(sessionsSettings);
            sessionName = SessionName.of(projectId, uuid);

            Log.d(TAG, "projectId: " + projectId);
        } catch (Exception e) {
            Log.d(TAG, "setUpBot: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void sendMessageToBot(String message) {
        QueryInput input = QueryInput.newBuilder().setText(TextInput.newBuilder().setText(message).setLanguageCode("vi-VN")).build();

        new SendMessageInBg(this, sessionName, sessionsClient, input).execute();
    }

    @Override
    public void callback(DetectIntentResponse returnResponse) {
        if (returnResponse != null) {
            String botReply = returnResponse.getQueryResult().getFulfillmentText();
            Log.d("ChatbotBabyShop", botReply);

            // Kiểm tra nếu câu trả lời chứa từ "không phải" hoặc "không đúng"
            if (message.toLowerCase().contains("không phải") || message.toLowerCase().contains("không đúng")) {
                botReply = "Bạn cần hỗ trợ trực tiếp với tư vấn viên? Hãy gọi 0933695144.";
            }

            if (!botReply.isEmpty()) {
                messageItemList.add(new MessageItem(botReply, true));
                messageAdapter.notifyDataSetChanged();
                recycleView.getLayoutManager().scrollToPosition(messageItemList.size() - 1);
            }
        } else  {
            Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show();
        }
    }
}

