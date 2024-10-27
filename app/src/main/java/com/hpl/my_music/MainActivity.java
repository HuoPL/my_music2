package com.hpl.my_music;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hpl.my_music.activity.BaseViewModelActivity;
import com.hpl.my_music.component.login.activity.LoginHomeActivity;
import com.hpl.my_music.databinding.ActivityMainBinding;
import com.hpl.my_music.util.Constant;

public class MainActivity extends BaseViewModelActivity <ActivityMainBinding> {

    @Override
    protected void initDatum() {
        super.initDatum();
        String action = getIntent().getAction();
        if (Constant.ACTION_LOGIN.equals(action)) {
            //跳转到启动界面
            startActivity(LoginHomeActivity.class);
        }
    }
//这是测
    //折尺测试
    //demo2


}