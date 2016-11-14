package com.gupengcheng.checksoftinput;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by gupengcheng on 16/11/11.
 */

public class CheckSoftInputLayout extends FrameLayout {

    private OnResizeListener mOnResizeListener;

    public CheckSoftInputLayout(Context context) {
        super(context);
    }

    public CheckSoftInputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CheckSoftInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public CheckSoftInputLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mOnResizeListener != null) {
            mOnResizeListener.onResize(w, h, oldw, oldh);
        }
    }

    public void setOnResizeListener(OnResizeListener listener) {
        this.mOnResizeListener = listener;
    }

    public interface OnResizeListener {
        void onResize(int w, int h, int oldw, int oldh);
    }
}
