package com.example.giohang.helpers;

import android.os.AsyncTask;
import android.util.Log;

import com.example.giohang.interfaces.BotReply;
import com.google.cloud.dialogflow.v2.DetectIntentRequest;
import com.google.cloud.dialogflow.v2.DetectIntentResponse;
import com.google.cloud.dialogflow.v2.QueryInput;
import com.google.cloud.dialogflow.v2.SessionName;
import com.google.cloud.dialogflow.v2.SessionsClient;

public class SendMessageInBg extends AsyncTask<Void, Void, DetectIntentResponse> {
    private SessionName sessionName;
    private SessionsClient sessionsClient;
    private QueryInput queryInput;
    private String TAG = "async";
    private BotReply botReply;
    public SendMessageInBg(BotReply botReply,SessionName sessionName, SessionsClient sessionsClient, QueryInput queryInput)
    {
        this.botReply = botReply;
        this.sessionName = sessionName;
        this.sessionsClient = sessionsClient;
        this.queryInput = queryInput;
    }
    @Override
    protected DetectIntentResponse doInBackground(Void... voids) {
        try
        {
            DetectIntentRequest detectIntentRequest =
                    DetectIntentRequest.newBuilder()
                            .setSession(sessionName.toString())
                            .setQueryInput(queryInput)
                            .build();
            return sessionsClient.detectIntent(detectIntentRequest);
        } catch (Exception e)
        {
            Log.d(TAG, "doInBackground: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(DetectIntentResponse response)
    {
        botReply.callback(response);
    }
}