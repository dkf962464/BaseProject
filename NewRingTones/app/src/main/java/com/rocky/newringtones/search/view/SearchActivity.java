package com.rocky.newringtones.search.view;

import android.os.Bundle;
import android.view.View;

import com.rocky.newringtones.R;
import com.rocky.newringtones.base.baseutil.BaseActivity;

import butterknife.OnClick;

public class SearchActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_;
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.but)
    public void onClick(View view) {

    }

    @Override
    protected void initView() {

    }

    @Override
    public boolean isImmersive() {
        return true;
    }
}
