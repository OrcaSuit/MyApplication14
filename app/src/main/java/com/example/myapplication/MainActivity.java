package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

import java.security.MessageDigest;
import java.util.Map;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static androidx.appcompat.widget.AppCompatDrawableManager.get;

public class MainActivity extends AppCompatActivity {
    UserVO userinfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userinfo = ((GlobalApplication) getApplication()).getUserinfo();


        //getHashKey(this);
    }


}
    /*// 프로젝트의 해시키를 반환

    @Nullable
    public static String getHashKey(Context context) {
        final String TAG = "KeyHash";
        String keyHash = null;
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                keyHash = new String(Base64.encode(md.digest(), 0));
                Log.d(TAG, keyHash);
            }

        } catch (Exception e) {
            Log.e("name not found", e.toString());
        }


        if (keyHash != null) {
            return keyHash;
        } else {
            return null;
        }
    }*/


