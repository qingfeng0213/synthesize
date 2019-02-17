package com.example.zk1lx.Api;

import com.example.zk1lx.bean.DetailsBean;
import com.example.zk1lx.bean.SouBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryName;
import retrofit2.http.Url;

public interface UserApiService {

    @GET
    Call<SouBean> seek(@Url String url,@Query("keyword")String keyword);
    @GET
    Call<DetailsBean> details(@Url String durl);

}
