package com.shoudukejiguan.www.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.widget.TextView;

import com.shoudukejiguan.www.R;


/**
 * Created by dengmingzhi on 16/4/25.
 */
public class Color2Text extends TextView {
    private String oldText;
    private boolean isFirstAdd = true;
    private String nowText;
    private int size;
    private int color;

    public Color2Text(Context context) {
        this(context, null);
    }

    public Color2Text(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Color2Text(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs,R.styleable.Color2Text);
        size = typedArray.getDimensionPixelSize(R.styleable.Color2Text_Color2Text_size,R.dimen.sp11);
        color = typedArray.getColor(R.styleable.Color2Text_Color2Text_color,getResources().getColor(R.color.color333));
        typedArray.recycle();
    }


    public void setTextContent(String text) {
        setOldText();
        nowText = text;
        setColor();
    }

    public void setTextContent(int rid) {
        setOldText();
        nowText = getResources().getString(rid);
        setColor();
    }


    public String getTextContent() {
        return nowText;
    }


    private void setOldText(){
        if (isFirstAdd) {
            oldText = getText().toString();
            isFirstAdd = false;
        }
    }

    /**
     * 设置不同位置的文本颜色和字体大小
     */
    private void setColor(){
        String str =oldText+nowText;
        SpannableStringBuilder style = new SpannableStringBuilder(str);
//        style.setSpan(new BackgroundColorSpan(Color.RED), 2, 5, Spannable.SPAN_EXCLUSIVE_INCLUSIVE); //设置指定位置textview的背景颜色
//        style.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.yd_666)), 0, oldText.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE); //设置指定位置文字的颜色

        style.setSpan(new AbsoluteSizeSpan(size), oldText.length(),str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        style.setSpan(new ForegroundColorSpan(color),oldText.length(),str.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        setText(style);
    }


}

