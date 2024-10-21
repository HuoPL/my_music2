package com.hpl.my_music.component.splash.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hpl.my_music.R;
import com.hpl.my_music.activity.BaseLogicActivity;
import com.hpl.my_music.component.splash.fragment.TermServiceDialogFragment;
import com.hpl.my_music.util.DefaultPreferenceUtil;
import com.hpl.my_music.util.SuperDateUtil;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.util.QMUIWindowInsetHelper;

public class SplashActivity extends BaseLogicActivity {

    private static final String TAG = "cweshi";
    private TextView copyrightView;

    /**
     * 这里启动界面
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);


    }

    @Override
    protected void initViews() {
        super.initViews();
        //这里吧实例变量变成类变量的快捷键是ctrl+alt+f

        copyrightView = findViewById(R.id.copyright);
        //设置出沉浸式状态栏

        QMUIStatusBarHelper.translucent(this);
//        QMUIWindowInsetHelper.setStatuBarLightMode
        QMUIStatusBarHelper.setStatusBarLightMode(this);

    }

    @Override
    protected void initDatum() {
        super.initDatum();
        //设置版本的年份
        //获取年份
        int year= SuperDateUtil.currentYear();
        copyrightView.setText(getResources().getString(R.string.copyright,year));
        if(DefaultPreferenceUtil.getInstance(getHostActivity()).isAcceptTermsServiceAgreement()){
            //走到这里其实就是已经同意了
            prepareNext();

        }else {
            showTermsServiceAgreementDialog();
        }
//        showTermsServiceAgreementDialog();
        //这里最开始忘记注释掉了  就导致 点击了统一还是显示了出来

    }
    private void showTermsServiceAgreementDialog(){
        TermServiceDialogFragment.show(getSupportFragmentManager(),new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DefaultPreferenceUtil.getInstance(getHostActivity()).setAcceptTermsServiceAgreement();;
                prepareNext();

            }
        });
    }

    private void prepareNext() {
        Log.d(TAG, "prepareNext: ");
    }
}