package com.example.jsonasker;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IpApi {
    @GET("?service=ip")
    Call<IpQuery> ipMessage();
}