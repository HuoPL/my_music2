package com.hpl.my_music.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

/**
 * 所有的dialogfragment对话框的父类
 */
public abstract class BaseDialogFragment extends DialogFragment {
    /**
     * 找控件用的
     */
    protected  void initViews(){

    }
    /**
     * 设置数据
     */
    protected void initDatum(){

    }
    /**
     * 设置监听器
     */
    protected void initListeners(){


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //获取view
        View view= getLayoutView(inflater,container,savedInstanceState);
        //返回view
        return view;
    }

    /**
     * 获取view
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    protected abstract View getLayoutView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);


    /**
     * view创建了
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        initDatum();
        initListeners();
    }

    /**
     * 这里是因为fragment不能直接调用findvviewbuid，所以给他封装一个方法来用。
     * 这里使用泛型就是为了适应 返回任何样式的view，泛型继承view 也是对T进行了一些限制
     * @param id
     * @return
     * @param <T>
     */
    public <T extends View> T findViewById(@IdRes int id){
        return getView().findViewById(id);
    }
}
