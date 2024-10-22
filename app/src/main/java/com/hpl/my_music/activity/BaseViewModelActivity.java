package com.hpl.my_music.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

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
}
