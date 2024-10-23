package com.hpl.my_music.component.guide.activity;

import android.view.View;


import com.hpl.my_music.MainActivity;
import com.hpl.my_music.R;
import com.hpl.my_music.activity.BaseViewModelActivity;
import com.hpl.my_music.databinding.ActivityGuideBinding;
import com.hpl.my_music.util.PreferenceUtil;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

/**
 * 左右滚动的引导界面
 * 如果想实现更复杂的效果，例如：滚动时文本缩放等效果，可以使用类似这样的框架：
 * https://github.com/bingoogolapple/BGABanner-Android
 */
public class GuideActivity extends BaseViewModelActivity<ActivityGuideBinding>implements View.OnClickListener  {
//    protected void initViews() {
//        super.initViews();
//        //这里吧实例变量变成类变量的快捷键是ctrl+alt+f
//
////        copyrightView = findViewById(R.id.copyright);
//        //设置出沉浸式状态栏
//
//        QMUIStatusBarHelper.translucent(this);
////        QMUIWindowInsetHelper.setStatuBarLightMode
//        QMUIStatusBarHelper.setStatusBarLightMode(this);
//
//    }

    @Override


    protected void initListeners() {
        super.initListeners();
        binding.loginOrRegister.setOnClickListener(this);
        binding.experienceNow.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_or_register:
                setShowGuide();
                break;

            case R.id.experience_now:
                startActivityAfterFinishThis(MainActivity.class);
                setShowGuide();


                break;


        }

    }
    private void setShowGuide(){
        sp.setShowGuide(false);

    }
}
