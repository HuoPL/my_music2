package com.hpl.my_music.component.guide.fragment;

import android.os.Bundle;

import com.hpl.my_music.databinding.FragmentGuideBinding;
import com.hpl.my_music.fragment.BaseViewModelFragment;

/**
 * 引导界面Fragment
 */
public class GuideFragment extends BaseViewModelFragment<FragmentGuideBinding> {
    public static GuideFragment newInstance(Integer data) {

        Bundle args = new Bundle();
        args.putInt("ID", data);

        GuideFragment fragment = new GuideFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initDatum() {
        super.initDatum();
        int data = getArguments().getInt("ID");
        binding.icon.setImageResource(data);
    }
}
