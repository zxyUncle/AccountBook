package com.zxy.accountbook.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.zxy.accountbook.R;


/**
 * @author 温清洁
 * @package com.moyu.moyu.widget
 * @fileName MyFloatButton
 * @date on 2019/2/20 16:15
 * @describe TODO
 * @email wqjuser@gmail.com
 */
public class MyFloatButton extends AbastractDragFloatActionButton {

    private Context mContext;
    private ImageView mSvgViewFoot;

    public MyFloatButton(Context context) {
        super(context);
        this.mContext=context;
    }

    public MyFloatButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext=context;
    }

    public MyFloatButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext=context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.invite_friends;
    }

    @Override
    public void renderView(View view) {

    }


}
