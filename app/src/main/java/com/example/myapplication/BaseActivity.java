package com.example.myapplication;

import android.content.Intent;

import androidx.fragment.app.FragmentActivity;

import com.example.myapplication.ui.login.LoginActivity;

public class BaseActivity extends FragmentActivity {

    protected void redirectLoginActivity() {
        final Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    protected void redirectSignupActivity() {
        final Intent intent = new Intent(this, SignupActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
