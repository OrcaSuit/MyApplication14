package com.example.myapplication;

import android.app.Application;
import android.os.StrictMode;

import com.kakao.auth.KakaoSDK;

public class GlobalApplication extends Application {
    private static GlobalApplication instance;
    private UserVO userinfo;

    public static GlobalApplication getGlobalApplicationContext() {
        if (instance == null) {
            throw new IllegalStateException("This Application does not inherit com.kakao.GlobalApplication");
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        // Kakao Sdk 초기화
        KakaoSDK.init(new KakaoSDKAdapter());
        //StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        //StrictMode.setVmPolicy(builder.build());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        instance = null;
    }

    public UserVO getUserinfo() {
        if (userinfo == null) userinfo = new UserVO();
        return userinfo;
    }

    public void setUserinfo(UserVO item) {
        this.userinfo = item;
    }

}