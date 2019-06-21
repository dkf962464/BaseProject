package com.rocky.newringtones.home.model;

import android.content.Context;

import com.rocky.newringtones.base.basemvp.model.BaseModelInter;

public interface HomeModelInterface extends BaseModelInter {
   void doLogin(HomeCallBackInter inter);
   void requestExView(Context context);
}
