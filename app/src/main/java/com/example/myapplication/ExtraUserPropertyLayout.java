package com.example.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * 추가로 받고자 하는 사용자 정보를 나타내는 layout
 * 이름, 나이, 성별을 입력할 수 있다.
 * @author MJ
 */
public class ExtraUserPropertyLayout extends FrameLayout {
    // property key
    private  static final String PHONE_KEY = "name";

    private EditText phone;
    public ExtraUserPropertyLayout(Context context) {
        super(context);
        initView();
    }
    public ExtraUserPropertyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }
    public ExtraUserPropertyLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }
    private void initView() {
        final View view = inflate(getContext(), R.layout.layout_usermgmt_extra_user_property, this);
        phone = view.findViewById(R.id.phone);
    }
    public HashMap<String, String> getProperties(){
        final String phoneValue = phone.getText().toString();

        HashMap<String, String> properties = new HashMap<>();
        properties.put(PHONE_KEY, phoneValue);
        return properties;
    }

    void showProperties(final Map<String, String> properties) {
        final String phoneValue = properties.get(PHONE_KEY);
        if (phoneValue != null)
            phone.setText(phoneValue);
    }
}