package com.rocky.newringtones.base.basemvp.presenter;

import com.rocky.newringtones.base.basemvp.model.BaseModelInter;
import com.rocky.newringtones.base.basemvp.view.BaseViewInter;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<V extends BaseViewInter,M extends BaseModelInter> {
    private WeakReference<V> mWeakReference;
}
