package com.rocky.newringtones.home.view;

import com.rocky.newringtones.base.basemvp.view.BaseViewInter;

public interface HomeViewInterface extends BaseViewInter {
    void resultSuccess(Object object);
    void resultFailed(String msg);
}
