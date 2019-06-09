package com.example.natali.android1;

import android.os.AsyncTask;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpRequester {

    private OnResponseCompleted listener;

    public OkHttpRequester(OnResponseCompleted listener) {
        this.listener = listener;
    }

    public void load(String url) {
        Requester requester = new Requester(listener);
        requester.execute(url);
    }

    public interface OnResponseCompleted {
        void onCompleted (String content);
    }

    static class Requester extends AsyncTask<String, Void, String> {

        OnResponseCompleted listener;

        Requester(OnResponseCompleted listener) {
            this.listener = listener;
        }

        @Override
        protected String doInBackground(String... url) {
            return getContent(url[0]);
        }

        @Override
        protected void onPostExecute(String content) {
            listener.onCompleted(content);
        }

        private String getContent(String url) {
            OkHttpClient client = new OkHttpClient();
            Request.Builder builder = new Request.Builder();
            builder.url(url);
            Request request = builder.build();
            try (Response response = client.newCall(request).execute()) {
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
