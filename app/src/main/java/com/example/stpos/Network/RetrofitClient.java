package com.example.stpos.Network;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;
    private static Retrofit retrofitWithDifBase = null;

    private static String BaseUrl=" https://softwaresale.xyz/stinventory/";
    private static Retrofit retrofitWithNoIntercepter = null;
    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private RetrofitClient() {
    }

    public static Retrofit get(Context context) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        if (retrofit == null) {OkHttpClient.Builder httpClient = new OkHttpClient.Builder().
                addInterceptor(new QueryParameterInterceptor(context));

            OkHttpClient client = httpClient.build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
        }
        return retrofit;
    }

    public static Retrofit noInterceptor() {
        if (retrofitWithNoIntercepter == null) {
            retrofitWithNoIntercepter = new Retrofit.Builder()
                    .baseUrl(BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitWithNoIntercepter;
    }
    public static Retrofit difBaseUrle() {
        if (retrofitWithDifBase == null) {
            retrofitWithDifBase = new Retrofit.Builder()
                    .baseUrl(" https://softwaresale.xyz/stinventory/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitWithDifBase;
    }
}
