package com.example.myapplication;

import android.app.Application;

import com.example.myapplication.adapter.KakaoSDKAdapter;
import com.kakao.auth.KakaoSDK;

//앱 수준에서 카카오톡 회원 관리를 위한 클래스
public class GlobalApplication extends Application {
    private static volatile GlobalApplication instance = null;  //인스턴스를 캐시가 아닌 메인 메모리에 읽기/쓰기위하여 선언

    //싱글턴 객체 획득
    public static GlobalApplication getGlobalApplicationContext() {
        if(instance == null)
            throw new IllegalStateException("this application does not inherit com.kakao.GlobalApplication");
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this; //Context 전달
        KakaoSDK.init(new KakaoSDKAdapter()); //카카오 SDK 초기화 KakaoAdapter와 연결됨
    }

    //앱 종료시 singleton Application 객체 초기화
    @Override
    public void onTerminate() {
        super.onTerminate();
        instance = null;
    }
}
