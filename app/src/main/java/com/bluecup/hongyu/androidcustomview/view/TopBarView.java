package com.bluecup.hongyu.androidcustomview.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bluecup.hongyu.androidcustomview.R;

/**
 * Des:
 * Created by hongyu
 * Date:16/4/17_上午9:39
 */
public class TopBarView extends RelativeLayout implements View.OnClickListener {


    private int leftTextColor;
    private String leftTextInfo;
    private Drawable leftBackground;
    private int rightTextColor;
    private Drawable rightBackground;
    private String rightTextInfo;
    private String title;
    private float titleSize;
    private int titleColor;
    private Button leftBtn;
    private TextView titleTxt;
    private Button rightBtn;
    private RelativeLayout.LayoutParams leftBtnLayoutParams;
    private RelativeLayout.LayoutParams rightBtnLayoutParams;
    private LayoutParams titleLayoutParams;

    private Context mContext;
    private ITopBarListener listener;

    public interface ITopBarListener {
        void leftClick();
        void rightClick();
    }

    /**
     * 设置对外的接口
     * 设计View的时候当内部消费了一个接口的时候需要定义一个新的给外部使用
     * @param listener
     */
    public void setClickListener(ITopBarListener listener) {
        this.listener = listener;
    }

    public TopBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void initView(Context context, AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTopbar);

        leftTextColor = typedArray.getColor(R.styleable.CustomTopbar_leftBtnColor, 0);
        leftTextInfo =  typedArray.getString(R.styleable.CustomTopbar_leftBtnInfo);
        leftBackground = typedArray.getDrawable(R.styleable.CustomTopbar_leftBackground);

        rightTextColor = typedArray.getColor(R.styleable.CustomTopbar_rightBtnColor, 0);
        rightTextInfo =  typedArray.getString(R.styleable.CustomTopbar_rightBtnInfo);
        rightBackground = typedArray.getDrawable(R.styleable.CustomTopbar_rightBackground);

        title = typedArray.getString(R.styleable.CustomTopbar_customTitle);
        titleSize = typedArray.getDimension(R.styleable.CustomTopbar_customTitleSize, 18);
        titleColor = typedArray.getColor(R.styleable.CustomTopbar_customeTitleColor, 0);

        typedArray.recycle();

        leftBtn = new Button(context);
        titleTxt = new TextView(context);
        rightBtn = new Button(context);

        leftBtn.setOnClickListener(this);
        rightBtn.setOnClickListener(this);

        leftBtn.setTextColor(leftTextColor);
        leftBtn.setText(leftTextInfo);
        leftBtn.setGravity(Gravity.CENTER);
        leftBtn.setBackground(leftBackground);
        
        rightBtn.setTextColor(rightTextColor);
        rightBtn.setText(rightTextInfo);
        rightBtn.setGravity(Gravity.CENTER);
        rightBtn.setBackground(rightBackground);

        titleTxt.setTextColor(titleColor);
        titleTxt.setText(title);
        titleTxt.setTextSize(titleSize);

        leftBtnLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        leftBtnLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        leftBtn.setLayoutParams(leftBtnLayoutParams);
        addView(leftBtn);

        rightBtnLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rightBtnLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        rightBtn.setLayoutParams(rightBtnLayoutParams);
        addView(rightBtn);

        titleLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        titleLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        titleTxt.setLayoutParams(titleLayoutParams);
        addView(titleTxt);

        setBackgroundColor(0xFFFF9563);
    }

    @Override
    public void onClick(View v) {
        if (v == leftBtn) {
            onResponse("left");
            if (listener != null) {
                listener.leftClick();
            }
        } else if (v == rightBtn){
            onResponse("right");
            if (listener != null) {
                listener.rightClick();
            }
        }
    }

    private void onResponse(String info) {
//        Toast.makeText(TopBarView.this.getContext(), info, Toast.LENGTH_SHORT).show();
    }
}
