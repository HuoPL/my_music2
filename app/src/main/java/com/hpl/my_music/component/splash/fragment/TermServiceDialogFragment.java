package com.hpl.my_music.component.splash.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentManager;

import com.hpl.my_music.R;
import com.hpl.my_music.fragment.BaseDialogFragment;

/**
 * 服务条款和隐私协议的对话框
 */
public class TermServiceDialogFragment extends BaseDialogFragment {
    /**
     * 显示对话框
     * @param fragmentManager
     * @param onAgreeClickListener 同意按钮点击对调
     */
    public static void show(FragmentManager fragmentManager, View.OnClickListener onAgreeClickListener) {
    }

    @Override
    protected View getLayoutView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dialog_term_service,container,false);
    }
}
