package com.FInpro.OOP.GroupE.Service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpRequestBuilder;


public class BackendService {

    private static final String BASE_URL = "http://localhost:8080/api";

    // Callback interface to  handle the result later (Asynchronous)
    public interface RequestCallback {
        void onSuccess(String response);
        void onError(String error);
    }

    // 1. REGISTER PLAYER (POST)
    public void createPlayer(String username, final RequestCallback callback) {

        String json = "{\"username\":\"" + username + "\"}";

        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        Net.HttpRequest request = requestBuilder.newRequest()
            .method(Net.HttpMethods.POST)
            .url(BASE_URL + "/players")
            .header("Content-Type", "application/json")
            .content(json)
            .build();

        sendRequest(request, callback);
    }

    // 2. SUBMIT SCORE (POST)
    public void submitScore(String playerId, int score, int coins, int distance, final RequestCallback callback) {
        // Create JSON payload manually
        String json = "{" +
            "\"playerId\":\"" + playerId + "\"," +
            "\"score\":" + score + "," +
            "\"coins\":" + coins + "," +
            "\"distance\":" + distance +
            "}";

        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        Net.HttpRequest request = requestBuilder.newRequest()
            .method(Net.HttpMethods.POST)
            .url(BASE_URL + "/scores")
            .header("Content-Type", "application/json")
            .content(json)
            .build();

        sendRequest(request, callback);
    }

    // 3. GET LEADERBOARD (GET)
    public void getLeaderboard(final RequestCallback callback) {
        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        Net.HttpRequest request = requestBuilder.newRequest()
            .method(Net.HttpMethods.GET)
            .url(BASE_URL + "/leaderboard")
            .build();

        sendRequest(request, callback);
    }

    // HELPER: Handles the actual HTTP call and threading
    private void sendRequest(Net.HttpRequest request, final RequestCallback callback) {
        Gdx.net.sendHttpRequest(request, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                final int statusCode = httpResponse.getStatus().getStatusCode();
                final String result = httpResponse.getResultAsString();

                // Post to Main Thread (UI Thread) so we can safely update the screen
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        if (statusCode >= 200 && statusCode < 300) {
                            callback.onSuccess(result);
                        } else {
                            callback.onError("Server Error: " + statusCode);
                        }
                    }
                });
            }

            @Override
            public void failed(final Throwable t) {
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        callback.onError("Connection Failed: " + t.getMessage());
                    }
                });
            }

            @Override
            public void cancelled() {
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        callback.onError("Request Cancelled");
                    }
                });
            }
        });
    }
}
