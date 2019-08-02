package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.ui.login.LoginActivity;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.util.exception.KakaoException;

//앱 실행시 스플래쉬 화면 세션이 존재한다면 메인화면으로 전환, 없다면 로그인 화면으로 전환
public class SplashActivity extends BaseActivity {
    private ISessionCallback callback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash);

        callback = new ISessionCallback() {
            @Override
            public void onSessionOpened() {
                goToMainActivity();
            }

            @Override
            public void onSessionOpenFailed(KakaoException exception) {
                redirectToLoginActivity();
            }
        };

        Session.getCurrentSession().addCallback(callback);
        findViewById(R.id.splash).postDelayed(() -> {
            if (!Session.getCurrentSession().checkAndImplicitOpen()) {
                redirectToLoginActivity();
            }
        }, 500);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(callback);
    }

    private void goToMainActivity() {
        Intent intent = new Intent(SplashActivity.this, ServiceListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void redirectToLoginActivity() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
