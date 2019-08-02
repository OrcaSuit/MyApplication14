package com.example.myapplication.adapter;

import com.example.myapplication.GlobalApplication;
import com.kakao.auth.IApplicationConfig;
import com.kakao.auth.KakaoAdapter;

public class KakaoSDKAdapter extends KakaoAdapter {
    @Override
    public IApplicationConfig getApplicationConfig() {
        return GlobalApplication::getGlobalApplicationContext;
    }
}
