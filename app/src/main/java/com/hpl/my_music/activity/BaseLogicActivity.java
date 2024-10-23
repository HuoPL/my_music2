package com.hpl.my_music.activity;
import com.hpl.my_music.util.PreferenceUtil;
/**
 * 项目中特有的逻辑，比如 显示迷你控制器播放栏状态
 */
public class BaseLogicActivity extends BaseCommonActivity{
    protected PreferenceUtil sp;

    @Override
    protected void initDatum() {
        super.initDatum();
        sp = PreferenceUtil.getInstance(getHostActivity());
    }



    /**
     * 获取界面方法
     *
     * @return
     */
    protected BaseLogicActivity getHostActivity() {
        return this;
    }

}
