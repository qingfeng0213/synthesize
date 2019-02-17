package com.example.zk1lx.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zk1lx.DetailsActivity;
import com.example.zk1lx.R;
import com.example.zk1lx.adapter.HomeAdapter;
import com.example.zk1lx.bean.DetailsBean;
import com.example.zk1lx.bean.SouBean;
import com.example.zk1lx.mvp.presenter.SeekPresenterImpl;
import com.example.zk1lx.mvp.view.MyView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class HomeFragment extends Fragment implements MyView {
    @BindView(R.id.left)
    ImageView left;
    @BindView(R.id.shuru)
    EditText shuru;
    @BindView(R.id.sousuo)
    TextView sousuo;
    @BindView(R.id.xrecyview)
    XRecyclerView xrecyview;
    Unbinder unbinder;
    private SeekPresenterImpl seekPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //沉浸式
        setAndroidNativeLightStatusBar(this, true);

        View view = inflater.inflate(R.layout.homefrag, container, false);
        unbinder = ButterKnife.bind(this, view);

        xrecyview.setLayoutManager(new GridLayoutManager(getActivity(),2));
        seekPresenter = new SeekPresenterImpl(this);
        return view;
    }
    //    状态栏文字颜色的修改  false为白色  true为黑色
    private static void setAndroidNativeLightStatusBar(HomeFragment homeFragment, boolean dark) {
        View decor = homeFragment.getActivity().getWindow().getDecorView();
        if (dark) {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.left, R.id.shuru, R.id.sousuo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left:
                break;
            case R.id.shuru:
                
                break;
            case R.id.sousuo:
                String yang = shuru.getText().toString();
                seekPresenter.getseekdata(yang);
                break;
        }
    }

    @Override
    public void getPreData(SouBean myBean) {
        List<SouBean.ResultBean> list = myBean.getResult();
        HomeAdapter homeAdapter = new HomeAdapter(getActivity(),list);
        xrecyview.setAdapter(homeAdapter);
        homeAdapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String  i) {
                seekPresenter.getDetailsdata(i);
                startActivity(new Intent(getActivity(),DetailsActivity.class));
            }
        });
    }

    @Override
    public void getDetailsPreData(DetailsBean deBean) {
        EventBus.getDefault().postSticky(deBean.getResult().getDetails());
    }
}
