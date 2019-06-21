package com.rocky.newringtones.home.presenter;

import com.rocky.newringtones.base.basemvp.presenter.BasePresenter;
import com.rocky.newringtones.home.model.HomeModel;
import com.rocky.newringtones.home.view.HomeActivity;

public class HomePresenter extends BasePresenter<HomeActivity, HomeModel> {
    @Override
    public HomeModel getModel() {

        return new HomeModel();
    }
}
