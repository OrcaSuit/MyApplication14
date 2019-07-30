package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.kakao.auth.ISessionCallback;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.usermgmt.response.model.Gender;
import com.kakao.usermgmt.response.model.UserAccount;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.OptionalBoolean;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

import java.util.ArrayList;
import java.util.List;

public class SessionCallback implements ISessionCallback {

    private static final String TAG = "SessionCallback";

    @Override //세션 열림
    public void onSessionOpened() {
        Log.e(TAG, "세션 열림 ");
        requestMe();
    }

    @Override // 세션 연결 실패
    public void onSessionOpenFailed(KakaoException exception) {
        Log.e(TAG, "세션 열기 실패 ");

    }

    private void requestMe() {
        List<String> keys = new ArrayList<>();
        keys.add("properties.nickname");
        keys.add("properties.profile_image");

        UserManagement.getInstance().me(keys, new MeV2ResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                String message = "failed to get user info. msg=" + errorResult;
                Logger.d(message);
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                //redirectLoginActivity();
            }

            @Override
            public void onSuccess(MeV2Response response) {
                if (response.hasSignedUp() == OptionalBoolean.FALSE) {
                    showSignup();
                } else {
                    redirectMainActivity();
                }
            }

            @Override
            public void onNotSignedUp() {
                //showSignup();
            }
        });
    }
}

