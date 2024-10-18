package com.hpl.my_music.component.splash.fragment;

import android.os.Bundle;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;

import com.google.android.material.button.MaterialButton;
import com.hpl.my_music.R;
import com.hpl.my_music.fragment.BaseDialogFragment;
import com.hpl.my_music.util.SuperTextUtil;

/**
 * 服务条款和隐私协议的对话框
 */
public class TermServiceDialogFragment extends BaseDialogFragment {
    private static final String TAG="TermServiceDialogFragment";
    private TextView contentView;
    private MaterialButton primaryView;
    private Button disagreeView;


    @Override
    protected void initViews() {
        super.initViews();
        setCancelable(false);//这行代码 的作用是点击弹窗外面不能关闭

        contentView=findViewById(R.id.content);
        primaryView=findViewById(R.id.primary);
        disagreeView=findViewById(R.id.disagree);
        SuperTextUtil.setLinkColor(contentView,getActivity().getColor(R.color.link));
    }

    @Override
    protected void initDatum() {
        super.initDatum();
        Spanned content =Html.fromHtml(getString(R.string.term_service_privacy_content));
        //这里是对获取到到的html进行一个简单的处理
        SpannableStringBuilder result=SuperTextUtil.setHtmlLinkClick(content, new SuperTextUtil.OnLinkClickListener() {
            @Override
            public void onLinkClick(String data) {
                Log.d(TAG, "onLinkClick: "+data);

            }
        });
        contentView.setText(result);

    }

    public static TermServiceDialogFragment newInstance() {

        Bundle args = new Bundle();

        TermServiceDialogFragment fragment = new TermServiceDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * 显示对话框
     * @param fragmentManager
     * @param onAgreeClickListener 同意按钮点击对调
     */
    public static void show(FragmentManager fragmentManager, View.OnClickListener onAgreeClickListener) {
        TermServiceDialogFragment fragment= newInstance();
        fragment.show(fragmentManager,"TermServiceDialogFragment");
    }

    @Override
    protected View getLayoutView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dialog_term_service,container,false);
    }
}
