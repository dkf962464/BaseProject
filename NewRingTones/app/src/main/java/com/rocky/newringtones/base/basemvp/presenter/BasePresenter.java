package com.rocky.newringtones.base.basemvp.presenter;

import com.rocky.newringtones.base.basemvp.model.BaseModelInter;
import com.rocky.newringtones.base.basemvp.view.BaseViewInter;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<V extends BaseViewInter, M extends BaseModelInter> {
    private WeakReference<V> mWeakReference;
    public M model;

    public void attach(V v) {
        mWeakReference = new WeakReference<>(v);
        model = getModel();
    }

    public void deAttach() {
        if (null != mWeakReference) {
            mWeakReference.clear();
            mWeakReference = null;
        }
    }

    protected boolean isAttach() {
        return mWeakReference != null && mWeakReference.get() != null;
    }

    public V getView() {
        if (mWeakReference != null) {
            return mWeakReference.get();
        }
        return null;
    }

    public abstract M getModel();
}
