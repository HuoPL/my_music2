package com.hpl.my_music.component.guide.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;


import androidx.annotation.NonNull;

import com.hpl.my_music.MainActivity;
import com.hpl.my_music.R;
import com.hpl.my_music.activity.BaseViewModelActivity;
import com.hpl.my_music.component.guide.adapter.GuideAdapter;
import com.hpl.my_music.config.Config;
import com.hpl.my_music.databinding.ActivityGuideBinding;
import com.hpl.my_music.util.Constant;
import com.hpl.my_music.util.PreferenceUtil;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 左右滚动的引导界面
 * 如果想实现更复杂的效果，例如：滚动时文本缩放等效果，可以使用类似这样的框架：
 * https://github.com/bingoogolapple/BGABanner-Android
 */
public class GuideActivity extends BaseViewModelActivity<ActivityGuideBinding>implements View.OnClickListener {
    private static final String TAG = "GuideActivity";
    private GuideAdapter adapter;
    protected void initDatum() {
        super.initDatum();
        //创建适配器
        adapter = new GuideAdapter(getHostActivity(), getSupportFragmentManager());

        //设置适配器到控件
        binding.list.setAdapter(adapter);

        //让指示器根据列表控件配合工作
        binding.indicator.setViewPager(binding.list);

        //适配器注册数据源观察者
        adapter.registerDataSetObserver(binding.indicator.getDataSetObserver());
        //准备数据
        List<Integer> datum = new ArrayList<>();
        datum.add(R.drawable.guide1);
        datum.add(R.drawable.guide2);
        datum.add(R.drawable.guide3);
        datum.add(R.drawable.guide4);
        datum.add(R.drawable.guide5);

        //设置数据到适配器
        adapter.setDatum(datum);
    }
    protected void initViews() {
        super.initViews();
        //这里吧实例变量变成类变量的快捷键是ctrl+alt+f
//
//        copyrightView = findViewById(R.id.copyright);
////        设置出沉浸式状态栏

        QMUIStatusBarHelper.translucent(this);
//        QMUIWindowInsetHelper.setStatuBarLightMode
        QMUIStatusBarHelper.setStatusBarLightMode(this);

    }

        @Override


        protected void initListeners () {
            super.initListeners();
            binding.loginOrRegister.setOnClickListener(this);
            binding.experienceNow.setOnClickListener(this);

        }

        @Override
        public void onClick (View v){
            switch (v.getId()) {
                case R.id.login_or_register:
                    Intent intent=new Intent(getHostActivity(), MainActivity.class);
                    intent.setAction(Constant.ACTION_LOGIN);
                    startActivity(intent);

                    setShowGuide();
                    finish();
                    break;

                case R.id.experience_now:
//                    startActivityAfterFinishThis(MainActivity.class);
//                    setShowGuide();


                    testGet();


                    break;


            }

        }

    private void testGet() {
        OkHttpClient client = new OkHttpClient();

        String url= Config.ENDPOINT+"v1/sheets";
        Request request =new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e(TAG, "get error: " + e.getLocalizedMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.d(TAG, "get success: " + response.body().string());

            }
        });
    }

    private void setShowGuide () {
//            sp.setShowGuide(false);

        }
    }

