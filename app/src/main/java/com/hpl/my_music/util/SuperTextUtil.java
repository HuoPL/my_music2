package com.hpl.my_music.util;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.hpl.superui.text.SuperClickableSpan;

/**
 * 这是文本相关的一个工具类
 */
public class SuperTextUtil {
    public static SpannableStringBuilder setHtmlLinkClick(Spanned data,OnLinkClickListener listener) {
        //spanned是一种原始文本，可以这么去理解
        SpannableStringBuilder sb=new SpannableStringBuilder(data);
        URLSpan[] spans=sb.getSpans(0,sb.length(), URLSpan.class);
        //这里是去把初始文本中的url全部拿出来，然后存放在这哥urlspan中
        for (URLSpan span : spans) {
            int start = sb.getSpanStart(span);
            int end = sb.getSpanEnd(span);
            int flags = sb.getSpanFlags(span);
            //这里是给获取到的span设置新的样式或者是信息，设置为可点击的span
            sb.setSpan(new SuperClickableSpan() {
                @Override
                public void onClick(@NonNull View widget) {
                    listener.onLinkClick(span.getURL());
                }
            }, start, end, flags);
        }

        return sb;

    }

    /**
     * 这里是设置富文本 超链接的颜色
     * @param view
     * @param color
     */

    public static void setLinkColor(TextView view, int color) {
        //这样设置后才可以点击
        view.setMovementMethod(LinkMovementMethod.getInstance());
        view.setLinkTextColor(color);
    }

    /**
     * 链接点监听器
     */
    public interface OnLinkClickListener{
        void onLinkClick(String url);


    }
}
