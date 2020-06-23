package com.zxy.accountbook.utils;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;

/**
 * @author 温清洁
 * @package com.moyu.moyu.widget
 * @fileName AbastractDragFloatActionButton
 * @date on 2019/2/20 16:12
 * @describe TODO
 * @email wqjuser@gmail.com
 */
public abstract class AbastractDragFloatActionButton extends RelativeLayout {
    private static final String TAG = "DragFloatActionButton";
    private int parentHeight;//悬浮的父布局高度
    private int parentWidth;
    private int lastX;
    private int lastY;
    private int startX, startY;
    private boolean isDrag;

    public AbastractDragFloatActionButton(Context context) {
        this(context, null, 0);
    }

    public AbastractDragFloatActionButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }


    public AbastractDragFloatActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(getLayoutId(), this);
        renderView(view);
    }

    public abstract int getLayoutId();

    public abstract void renderView(View view);

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        View view = getChildAt(0);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "ACTION_DOWN: ");
                setPressed(true);//默认是点击事件
                isDrag = false;//默认是非拖动而是点击事件
                getParent().requestDisallowInterceptTouchEvent(true);//父布局不要拦截子布局的监听
                startX = lastX = rawX;
                startY = lastY = rawY;
                ViewGroup parent;
                if (getParent() != null) {
                    parent = (ViewGroup) getParent();
                    parentHeight = parent.getHeight();
                    parentWidth = parent.getWidth();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "ACTION_MOVE: ");
                isDrag = true;
                int dx = rawX - lastX;
                int dy = rawY - lastY;
                //这里修复一些华为手机无法触发点击事件
                int distance = (int) Math.sqrt(dx * dx + dy * dy);
                if (distance < 10) {
                    isDrag = false;
                    break;
                }
                float x = getX() + (rawX - lastX);
                float y = getY() + (rawY - lastY);
                //检测是否到达边缘 左上右下
                x = x < 0 ? 0 : x > parentWidth - getWidth() ? parentWidth - getWidth() : x;
                y = getY() < 0 ? 0 : getY() + getHeight() > parentHeight ? parentHeight - getHeight() : y;
                setX(x);
                setY(y);
                lastX = rawX;
                lastY = rawY;
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "ACTION_UP: ");
                setPressed(!isDrag);
                int dx1 = (int) event.getRawX() - startX;
                int dy1 = (int) event.getRawY() - startY;
                if (Math.abs(dx1) < 3 && Math.abs(dy1) < 3) {
                    performClick();//重点，确保setOnclickListener生效
                    break;
                }
                if (rawX >= parentWidth / 2) {
                    //靠右吸附
                    animate().setInterpolator(new DecelerateInterpolator())
                            .setDuration(500)
                            .xBy(parentWidth - getWidth() - getX())
                            .start();
                } else {
                    //靠左吸附
                    ObjectAnimator oa = ObjectAnimator.ofFloat(this, "x", getX(), 0);
                    oa.setInterpolator(new DecelerateInterpolator());
                    oa.setDuration(500);
                    oa.start();
                }
                break;

        }

        //如果不是拖拽，那么就不消费这个事件，以免影响点击事件的处理
        //拖拽事件要自己消费
        return !isDrag;
    }

}
