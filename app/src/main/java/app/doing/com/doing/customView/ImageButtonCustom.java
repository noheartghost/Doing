package app.doing.com.doing.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.jar.Attributes;

import app.doing.com.doing.R;


/**
 * Created by cherry on 18-5-22.
 * 自定义ImageButton
 * 对应ui:
 * 1.精选页场馆、教练和课程按钮
 * 2.底部4个tab按钮
 */

public class ImageButtonCustom extends ConstraintLayout {
    private ImageView imageView;
    private TextView textView;

    public ImageButtonCustom(@NonNull Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.imagebutton_custom, this);
        this.setPadding(0,18,0,0);

        imageView = findViewById(R.id.image_id);
        textView = findViewById(R.id.text_id);

        //用于设置自定义组合控件的属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImageButtonCustom);
        setText(typedArray.getString(R.styleable.ImageButtonCustom_text));
        setImage(typedArray.getResourceId(R.styleable.ImageButtonCustom_image, -1));
        typedArray.recycle();//使用完后必须释放
    }

    public void setText(String text){
        textView.setText(text);
    }

    public void setImage(int sourceId){
        imageView.setBackgroundResource(sourceId);
    }

    /*
    按钮未被选中时字体颜色较浅
     */
    public void setTextUnSelected(){
        textView.setTextColor(getResources().getColor(R.color.textDarkGrey));
    }

    /*
    按钮被选中时字体颜色加深
     */
    public void setTextSelected(){
        textView.setTextColor(getResources().getColor(R.color.textGrey));
    }
}
