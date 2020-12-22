package com.example.jsonasker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity {
    Context context = this;
    TextView textView;
    CheckBox checkBox;
    private final String url = "http://date.jsontest.com/";



//    public interface JsontestApi {
////        @GET("/api/get")
////        Call<List<PostModel>> getData(@Query ("site") String siteName, @Query("name") String resourceName, @Query("num") int count);
//        @GET("/?service={choice}")
//        Call<List<PostModel>> getData(@Query("choice") String choice);     // "date" or "ip"
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.jtext);
        checkBox = findViewById(R.id.checkBox);

    }

    public void dateCall(View view) {
//        Response response
        if (checkBox.isChecked()) {
            System.out.println("NOPEEEEEEEEEEEEEEEEEE");

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            IpApi ipMessagesApi = retrofit.create(IpApi.class);
            Call<IpQuery> ipMessage = ipMessagesApi.ipMessage();

            ipMessage.enqueue(new Callback<IpQuery>() {
                @Override
                public void onResponse(Call<IpQuery> call, retrofit2.Response<IpQuery> response) {
                    if (response.isSuccessful()) {
                        Log.d("RESPONSE_SUCCESS", "response " + response.body());
                        textView.setText("IP: " + response.body().getIp());
                    } else {
                        Log.d("FAIL", "response code " + response.code());
                    }
                }


                @Override
                public void onFailure(Call<IpQuery> call, Throwable t) {
                    Log.d("FAIL", "Fail " + t);
                }
            });
        } else {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            DateApi dateMessageApi = retrofit.create(DateApi.class);
            Call<DateQuery> dateMessage = dateMessageApi.dateMessage();

            dateMessage.enqueue(new Callback<DateQuery>() {
                @Override
                public void onResponse(Call<DateQuery> call, retrofit2.Response<DateQuery> response) {
                    if (response.isSuccessful()) {
                        Log.d("SUCCESS", "Success " + response.body());
                        textView.setText("Date: " + response.body().getDate());
                    } else {
                        Log.d("FAIL", "response code " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<DateQuery> call, Throwable t) {
                    Log.d("FAIL", "Fail " + t);
                }
            });

        }
    }
}


