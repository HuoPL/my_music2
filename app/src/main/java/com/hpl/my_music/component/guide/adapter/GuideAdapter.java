package com.hpl.my_music.component.guide.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.hpl.my_music.adapter.BaseFragmentStatePagerAdapter;
import com.hpl.my_music.component.guide.fragment.GuideFragment;


import java.util.ArrayList;
import java.util.List;

/**
 * 引导界面适配器
 */
public class GuideAdapter extends BaseFragmentStatePagerAdapter<Integer> {

    /***
     *  @param context 上下文
     * @param fm Fragment管理器
     */
    public GuideAdapter(Context context, @NonNull FragmentManager fm) {
        super(context, fm);
    }

    /**
     * 返回当前位置Fragment
     *
     * @param position
     * @return
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return GuideFragment.newInstance(getData(position));
    }
}

