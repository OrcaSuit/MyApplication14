package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.kakao.auth.AccessTokenCallback;
import com.kakao.auth.ApiResponseCallback;
import com.kakao.auth.AuthService;
import com.kakao.auth.Session;
import com.kakao.auth.authorization.accesstoken.AccessToken;
import com.kakao.auth.network.response.AccessTokenInfoResponse;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.helper.log.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//가입된 사용자가 보게되는 메인 페이지 사용자 정보 불러오기
public class ProfileActivity extends BaseActivity {
    private MeV2Response response;
    private ProfileLayout profileLayout;

    //로그인 또는 가입창에서 넘긴 유저 정보가 있다면 저장
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeView();
        profileLayout.requestMe();
    }

    private void initializeView() {
        setContentView(R.layout.layout_profile_main);

        initializeButtons();
        initializeProfileView();
    }

    private void initializeButtons() {
        final Button buttonMe = findViewById(R.id.buttonMe);
        buttonMe.setOnClickListener(view -> updateScopes());

        final Button logoutButton = findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(view -> onClickLogout());

        final Button tokenInfoButton = findViewById(R.id.token_info_button);
        tokenInfoButton.setOnClickListener(view -> onClickAccessTokenInfo());
    }

    private void onClickLogout() {
        UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
            @Override
            public void onCompleteLogout() {
                redirectLoginActivity();
            }
        });
    }


    private void onClickAccessTokenInfo() {
        AuthService.getInstance().requestAccessTokenInfo(new ApiResponseCallback<AccessTokenInfoResponse>() {
            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                redirectLoginActivity();
            }

            @Override
            public void onNotSignedUp() {
                // not happened
            }

            @Override
            public void onFailure(ErrorResult errorResult) {
                String message = "failed to get access token info. msg=" + errorResult;
                Logger.e(message);
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(AccessTokenInfoResponse accessTokenInfoResponse) {
                long userId = accessTokenInfoResponse.getUserId();
                Logger.d("this access token is for userId=" + userId);

                long expiresInMilis = accessTokenInfoResponse.getExpiresInMillis();
                Logger.d("this access token expires after " + expiresInMilis + " milliseconds.");

                Toast.makeText(getApplicationContext(), "this access token for user(id="+ userId+") expires after " + expiresInMilis + " milliseconds.", Toast.LENGTH_LONG).show();
            }
        });
    }



    private void initializeProfileView() {
        profileLayout = findViewById(R.id.com_kakao_user_profile);
        profileLayout.setMeV2ResponseCallback(new MeV2ResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                String message = "failed to get user info. msg=" + errorResult;
                Logger.e(message);
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                redirectLoginActivity();
            }

            @Override
            public void onSuccess(MeV2Response result) {
                response = result;
                updateLayouts(result);
            }
        });
    }

    private void updateLayouts(MeV2Response result) {
        profileLayout.setUserInfo(result);
        if (result.getProperties() != null) {
        }
    }

    private void updateScopes() {
        List<String> scopes = new ArrayList<>();

        if (response.getKakaoAccount().needsScopeAccountEmail()) {
            scopes.add("account_email");
        }
        if (response.getKakaoAccount().needsScopePhoneNumber()) {
            scopes.add("phone_number");
        }

        if (scopes.isEmpty()) {
            return;
        }

        Session.getCurrentSession().updateScopes(ProfileActivity.this, scopes, new AccessTokenCallback() {
            @Override
            public void onAccessTokenReceived(AccessToken accessToken) {
                profileLayout.requestMe();
            }

            @Override
            public void onAccessTokenFailure(ErrorResult errorResult) {
                Toast.makeText(getApplicationContext(), "Failed to update scopes", Toast.LENGTH_LONG).show();

            }
        });
    }
}
