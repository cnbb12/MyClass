package com.example.a95797.myclass.utils;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class NoScrollViewPager
        extends ViewPager
{
    private boolean noScroll = false;

    public NoScrollViewPager(Context paramContext)
    {
        super(paramContext);
    }

    public NoScrollViewPager(Context paramContext, AttributeSet paramAttributeSet)
    {
        super(paramContext, paramAttributeSet);
    }

    public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
    {
        if (this.noScroll) {
            return false;
        }
        return super.onInterceptTouchEvent(paramMotionEvent);
    }

    public boolean onTouchEvent(MotionEvent paramMotionEvent)
    {
        if (this.noScroll) {
            return false;
        }
        return super.onTouchEvent(paramMotionEvent);
    }

    public void setNoScroll(boolean paramBoolean)
    {
        this.noScroll = paramBoolean;
    }
}
