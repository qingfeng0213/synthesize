package com.example.zk1lx.mvp.model;

import com.example.zk1lx.Api.ApiService;
import com.example.zk1lx.Api.UserApiService;
import com.example.zk1lx.bean.DetailsBean;
import com.example.zk1lx.bean.SouBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SeekModelImpl implements SeekModel {

    Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ApiService.BASE_URL)
            .build();
    UserApiService userApiService = retrofit.create(UserApiService.class);
    @Override
    public void gethome(String url, String keyword, final CteanView cteanView) {
        Call<SouBean> seek = userApiService.seek(url,keyword);
        seek.enqueue(new Callback<SouBean>() {
            @Override
            public void onResponse(Call<SouBean> call, Response<SouBean> response) {
                cteanView.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<SouBean> call, Throwable t) {

            }
        });
    }

    @Override
    public void getDetails(String url, final CteanViews cteanViews) {
        Call<DetailsBean> details = userApiService.details(url);
        details.enqueue(new Callback<DetailsBean>() {
            @Override
            public void onResponse(Call<DetailsBean> call, Response<DetailsBean> response) {
                cteanViews.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<DetailsBean> call, Throwable t) {

            }
        });
    }
}
