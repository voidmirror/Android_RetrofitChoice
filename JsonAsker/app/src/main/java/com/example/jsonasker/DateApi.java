package com.example.jsonasker;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DateApi {
    @GET("?service=date")
    Call<DateQuery> dateMessage();
}
