package com.rocky.newringtones.vview.presenter.impl;

import com.rocky.newringtones.vview.model.impl.HomeAModelImpl;
import com.rocky.newringtones.vview.model.inter.IHomeAModel;
import com.rocky.newringtones.vview.presenter.inter.IHomeAPresenter;
import com.rocky.newringtones.vview.view.inter.IHomeAView;

public class HomeAPresenterImpl implements IHomeAPresenter {
    private IHomeAView mIHomeAView;
    private IHomeAModel mIHomeAModel;

    public HomeAPresenterImpl(IHomeAView aIHomeAView) {
        mIHomeAView = aIHomeAView;
        mIHomeAModel = new HomeAModelImpl();
    }
}
