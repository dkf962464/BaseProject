package com.rocky.newringtones.base.basemvp.model;

import com.rocky.newringtones.base.baseutil.BaseActivity;

public interface BaseModelInter {
    default boolean isJson(String json){
        return BaseActivity.isJson(json);
    }

}

