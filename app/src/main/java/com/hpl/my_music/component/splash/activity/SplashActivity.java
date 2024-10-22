package com.hpl.my_music.component.splash.activity;

import android.Manifest;
import android.os.Build;
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
import com.hpl.my_music.databinding.ActivitySplashBinding;
import com.hpl.my_music.util.DefaultPreferenceUtil;
import com.hpl.my_music.util.SuperDateUtil;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.util.QMUIWindowInsetHelper;
import com.permissionx.guolindev.PermissionX;
import com.permissionx.guolindev.request.PermissionBuilder;
//import com.hpl.my_music.activity.BaseViewModelActivity;

public class SplashActivity extends BaseLogicActivity {

    private static final String TAG = "SplashActivity";
    private TextView copyrightView;
    private ActivitySplashBinding binding;

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
        binding=ActivitySplashBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());


    }

    @Override
    protected void initViews() {
        super.initViews();
        //这里吧实例变量变成类变量的快捷键是ctrl+alt+f

//        copyrightView = findViewById(R.id.copyright);
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
        binding.copyright.setText(getResources().getString(R.string.copyright,year));
        if(DefaultPreferenceUtil.getInstance(getHostActivity()).isAcceptTermsServiceAgreement()){
            //走到这里其实就是已经同意了
            checkPermission();

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
                checkPermission();

            }
        });
    }

    /**
     * 检查是否有需要的权限
     * <p>
     * <p>
     * 只有部分权限才需要动态授权
     * 例如：网络权限就不需要动态授权，但相机就需要动态授权
     * <p>
     * <p>
     * Google推荐在用到权限的时候才请求用户
     * 但真实项目中，如果在每个用到的位置请求权限可能比较麻烦
     * 例如：项目中有多个位置都用到了相机
     * <p>
     * <p>
     * 所以说大部分项目，像淘宝，京东等软件
     * 是在启动页请求项目所有需要的权限
     * <p>
     * <p>
     * 但如果大家的项目中有足够的时间
     * 肯定还是推荐在用到的时候再请求权限
     */

    private void checkPermission() {
        //让动态框架检查是否授权了

        //如果不使用框架就使用系统提供的API检查
        //它内部也是使用系统API检查
        //只是使用框架就更简单了
        PermissionBuilder r;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            r = PermissionX.init(this).permissions(
                    Manifest.permission.CAMERA,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.READ_MEDIA_AUDIO,
                    Manifest.permission.READ_MEDIA_IMAGES,
                    Manifest.permission.READ_MEDIA_VIDEO
            );
        } else {
            r = PermissionX.init(this).permissions(
                    Manifest.permission.CAMERA,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            );
        }

        r.request((allGranted, grantedList, deniedList) -> {
            if (allGranted) {

                binding.getRoot().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        prepareNext();
                    }
                }, 1000);
            } else {
                //可以在这里弹出提示告诉用户为什么需要权限
                finish();
            }
        });
    }

    private void prepareNext() {
        Log.d(TAG, "prepareNext: ");
    }
}