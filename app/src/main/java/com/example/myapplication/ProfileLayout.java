package com.example.myapplication;



import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.usermgmt.response.model.UserProfile;


public class ProfileLayout extends FrameLayout {
    private MeV2ResponseCallback meV2ResponseCallback;

    private String nickname;
    private String userId;

    private TextView profileDescription;

    public ProfileLayout(Context context) {
        super(context);
        initView();
    }

    public ProfileLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ProfileLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    public void setUserProfile(final UserProfile userProfile) {
        setNickname(userProfile.getNickname());
    }
    public void setUserInfo(final MeV2Response response) {
        if (response.getProperties() != null) {
            setNickname(response.getProperties().get(MeV2Response.KEY_NICKNAME));
        }
        setUserId(String.valueOf(response.getId()));
        updateLayout();
    }

    /**
     * 사용자정보 요청 결과에 따른 callback을 설정한다.
     * @param callback 사용자정보 요청 결과에 따른 callback
     */
    public void setMeV2ResponseCallback(final MeV2ResponseCallback callback) {
        this.meV2ResponseCallback = callback;
    }

    /**
     * 별명 view를 update한다.
     * @param nickname 화면에 반영할 별명
     */
    public void setNickname(final String nickname) {
        this.nickname = nickname;
        updateLayout();
    }

    private void updateLayout() {
        StringBuilder builder = new StringBuilder();

        if (nickname != null && nickname.length() > 0) {
            builder.append(getResources().getString(R.string.com_kakao_profile_nickname)).append("\n").append(nickname).append("\n");
        }
        if (userId != null && userId.length() > 0) {
            builder.append(getResources().getString(R.string.com_kakao_profile_userId)).append("\n").append(userId).append("\n");
        }
    }

    public void setUserId(final String userId) {
        this.userId = userId;
        updateLayout();
    }

    private void initView() {
        View view = inflate(getContext(), R.layout.layout_common_kakao_profile, this);
        profileDescription = view.findViewById(R.id.profile_description);
    }
    //사용자 정보 요청
    public void requestMe() {
        UserManagement.getInstance().me(meV2ResponseCallback);
    }


}
