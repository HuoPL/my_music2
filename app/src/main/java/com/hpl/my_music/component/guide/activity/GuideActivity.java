package com.hpl.my_music.component.guide.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import androidx.annotation.NonNull;

import com.hpl.my_music.MainActivity;
import com.hpl.my_music.R;
import com.hpl.my_music.activity.BaseViewModelActivity;
import com.hpl.my_music.component.api.DefaultService;
import com.hpl.my_music.component.api.NetworkModule;
import com.hpl.my_music.component.guide.adapter.GuideAdapter;
import com.hpl.my_music.component.observer.ObserverAdapter;
import com.hpl.my_music.component.sheet.model.Sheet;
import com.hpl.my_music.component.sheet.model.SheetWrapper;
import com.hpl.my_music.config.Config;
import com.hpl.my_music.databinding.ActivityGuideBinding;
import com.hpl.my_music.model.response.DetailResponse;
import com.hpl.my_music.model.response.ListResponse;
import com.hpl.my_music.util.Constant;
import com.hpl.my_music.util.PreferenceUtil;
import com.hpl.superui.toast.SuperToast;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

/**
 * 左右滚动的引导界面
 * 如果想实现更复杂的效果，例如：滚动时文本缩放等效果，可以使用类似这样的框架：
 * https://github.com/bingoogolapple/BGABanner-Android
 */
public class GuideActivity extends BaseViewModelActivity<ActivityGuideBinding>implements View.OnClickListener {
    private static final String TAG = "GuideActivity";
    private GuideAdapter adapter;
    private DefaultService service;



    protected void initDatum() {
        super.initDatum();



        OkHttpClient okHttpClient = NetworkModule.provideOkHttpClient();
        Retrofit retrofit = NetworkModule.provideRetrofit(okHttpClient);
        service = retrofit.create(DefaultService.class);
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


//                    testGet();
                    testRetrofitGet();


                    break;


            }

        }




    private void testRetrofitGet() {

//        service.sheetDetail("ixuea", "1")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<DetailResponse<Sheet>>() {
//                    @Override
//                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull DetailResponse<Sheet> data) {
//                        Log.d(TAG, "onNext: " + data.getData().getTitle());
//                    }
//
//                    @Override
//                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
//                        Log.e(TAG, "onError: " + e.getLocalizedMessage());
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

//        service.sheetDetail("ixuea", "1")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new ObserverAdapter<DetailResponse<Sheet>>() {
//                    @Override
//                    public void onNext(DetailResponse<Sheet> sheetDetailResponse) {
//                        super.onNext(sheetDetailResponse);
//                    }
//                });
//        Toast.makeText(getHostActivity(), R.string.about_ta, Toast.LENGTH_SHORT).show();
        //这其实就是一个弹出的小提示
        SuperToast.error("加载中");
    }

    /**
     * okhttp get请求
     */
    private void testGet() {
        OkHttpClient client = new OkHttpClient();

        String url = Config.ENDPOINT + "v1/sheets";

        Request request = new Request.Builder()
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

