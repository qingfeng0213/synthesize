package com.example.zk1lx.mvp.model;

import com.example.zk1lx.bean.DetailsBean;
import com.example.zk1lx.bean.SouBean;

public interface SeekModel {
        void gethome(String url,String keyword, CteanView cteanView);
        void getDetails(String url, CteanViews cteanViews);

        interface CteanView {
            void onSuccess(SouBean bean);
            void onFailure();
        }
    interface CteanViews {
        void onSuccess(DetailsBean bean);
        void onFailure();
    }
}
