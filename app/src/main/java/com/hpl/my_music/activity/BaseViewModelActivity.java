package com.hpl.my_music.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.hpl.my_music.component.guide.activity.GuideActivity;
import com.hpl.superui.reflect.ReflectUtil;

/**
 * 通用ViewModel Activity
 * 包括ViewBinding，主要是处理每次要setContentView
 * 以及自动创建ViewModel
 * 以及viewModel的通用观察处理
 */
//这里的vb指的是名字叫vb的泛型
public class BaseViewModelActivity<VB extends ViewBinding> extends BaseLogicActivity {
    protected VB binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //调用inflate方法，创建viewBinding
        binding = ReflectUtil.newViewBinding(getLayoutInflater(), this.getClass());
        setContentView(binding.getRoot());
    }
    protected void startActivityAfterFinishThis(Class<GuideActivity> clazz){
        //这是一个常用通用逻辑  启动新界面  然后关闭上一个界面
        Intent intent = new Intent(this, GuideActivity.class);
        startActivity(intent);
        //启动后不希望回到最开始的界面了  所以直接就调用finish
        finish();//在哪个界面调用finish  就关闭哪个界面了

    }
}
