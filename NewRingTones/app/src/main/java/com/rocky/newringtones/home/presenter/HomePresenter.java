package com.rocky.newringtones.home.presenter;
import android.content.Context;
import com.rocky.newringtones.base.basemvp.presenter.BasePresenter;
import com.rocky.newringtones.home.model.HomeCallBackInter;
import com.rocky.newringtones.home.model.HomeModelImpl;
import com.rocky.newringtones.home.view.HomeActivity;

public class HomePresenter extends BasePresenter<HomeActivity, HomeModelImpl> {

    @Override
    public HomeModelImpl getModel() {
        return new HomeModelImpl() ;
    }
    public void login(){
        model.doLogin(new HomeCallBackInter() {
            @Override
            public void resultSuccess(Object object) {
                getView().resultSuccess(object);
            }

            @Override
            public void resultFailed(String msg) {
                getView().resultFailed(msg);
            }
        });
    }
    public void requestExView(Context context){
        model.requestExView(context);
    }
}
