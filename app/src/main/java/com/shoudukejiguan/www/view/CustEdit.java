package com.shoudukejiguan.www.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.shoudukejiguan.www.R;


/**
 * Created by dengmingzhi on 16/6/15.
 */
public class CustEdit extends RelativeLayout {
    private ImageView iv_img;
    private EditText et_content;

    public CustEdit(Context context) {
        this(context, null);
    }

    public CustEdit(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustEdit(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        View.inflate(getContext(), R.layout.view_cust_edit, this);
        iv_img = (ImageView) findViewById(R.id.iv_img);
        et_content = (EditText) findViewById(R.id.et_content);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustEdit);
        int image = typedArray.getResourceId(R.styleable.CustEdit_CustEdit_image, 0);
        String hint = typedArray.getString(R.styleable.CustEdit_CustEdit_hint);
        if (image <= 0) {
            throw new IllegalArgumentException("图片引用不能为0或其他不是引用的值");
        } else {
            iv_img.setImageResource(image);
        }

        et_content.setHint(hint);
    }


    public String getContent() {
        return et_content.getText().toString();
    }

    public void setContent(String content) {
        et_content.setText(content);
    }
}
