package com.example.jsonasker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity {
    Context context = this;
    TextView textView;
    private Retrofit retrofit;
    private static JsontestApi jsontestApi;



    public interface JsontestApi {
//        @GET("/api/get")
//        Call<List<PostModel>> getData(@Query ("site") String siteName, @Query("name") String resourceName, @Query("num") int count);
        @GET("?service={choice}")
        Call<List<PostModel>> groupList(@Path("choice") String choice);     // "date" or "ip"
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.jtext);
        System.out.println(textView);
        retrofit = new Retrofit.Builder().baseUrl("http://ip.jsontest.com").addConverterFactory(GsonConverterFactory.create()).build();
        jsontestApi = retrofit.create(JsontestApi.class);

        App.getApi().getData("bash", 50).enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                posts.addAll(response.body());
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void jsonText (View view) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://ip.jsontest.com";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response.substring(8, 23));
                Pattern pattern = Pattern.compile(".*[0-9].+.*");

                textView.setText(response.substring(8, 22));
                System.out.println("Done");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (textView != null) {
                    System.out.println("Your textView: " + textView);
                } else {
                    System.out.println("It is NULL");
                }
                textView.setText("Didn't get");
            }
        });

        queue.add(stringRequest);

    }

    public static JsontestApi getApi() {
        return jsontestApi;
    }

    public static void dateCall() {
        Response response
    }
}


