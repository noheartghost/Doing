package app.doing.com.doing.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import app.doing.com.doing.R;

/**
 * Created by cherry on 18-5-30.
 * 自定义导航栏控件，可控制一些view的显示和隐藏
 */

public class NavigatorBarCustom extends ConstraintLayout{
    private ImageButton image_left;
    private ImageButton image_right;
    private TextView text_title;
    private ImageView image_center;

    public NavigatorBarCustom(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setPadding(20,10,20,0);
        initView(context);
        initType(context,attrs);
    }

    private void initView(Context context){
        LayoutInflater.from(context).inflate(R.layout.navigatorbar_custom, this);
        image_left = findViewById(R.id.nav_image_left);
        image_right = findViewById(R.id.nav_image_right);
        text_title = findViewById(R.id.nav_text);
        image_center = findViewById(R.id.nav_image_center);
    }

    /*
    用于设置自定义组合控件的属性
     */
    private void initType(Context context, AttributeSet attributeSet){
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.NavigatorBarCustom);
        int totalAttributes = typedArray.getIndexCount();

        for(int i=0;i<totalAttributes;i++){
            int index = typedArray.getIndex(i);
            switch (index){
                case R.styleable.NavigatorBarCustom_left_image:
                    image_left.setBackgroundResource(typedArray.getResourceId(index,-1));
                    break;
                case R.styleable.NavigatorBarCustom_right_image:
                    image_right.setBackgroundResource(typedArray.getResourceId(index,-1));
                    break;
                case R.styleable.NavigatorBarCustom_left_visible:
                    setVisible(typedArray,image_left,index);
                    break;
                case R.styleable.NavigatorBarCustom_right_visible:
                    setVisible(typedArray,image_right,index);
                    break;
                case R.styleable.NavigatorBarCustom_text_visible:
                    setVisible(typedArray,text_title,index);
                    break;
                case R.styleable.NavigatorBarCustom_text_title:
                    text_title.setText(typedArray.getString(index));
                    break;
                case R.styleable.NavigatorBarCustom_center_img_visile:
                    setVisible(typedArray,image_center,index);
                    break;
                case R.styleable.NavigatorBarCustom_center_image:
                    image_center.setBackgroundResource(typedArray.getResourceId(index,-1));
                    break;
            }
        }
        typedArray.recycle();//使用完后必须释放
    }

    /*
    设置view的显示和隐藏
     */
    private void setVisible(TypedArray typedArray, View view,int index){
        boolean visible = typedArray.getBoolean(index,true);
        if(visible){
            view.setVisibility(VISIBLE);
        }else{
            view.setVisibility(GONE);
        }
    }

    public void setLeftImageButtonListener(OnClickListener clickListener){
        if(clickListener!=null)image_left.setOnClickListener(clickListener);
    }

    public void setRightImageButtonListener(OnClickListener clickListener){
        if(clickListener!=null)image_right.setOnClickListener(clickListener);
    }

    public void setText_title(String text){
        text_title.setText(text);
    }

    public ImageButton getImage_left() {
        return image_left;
    }

    public ImageButton getImage_right() {
        return image_right;
    }

    public TextView getText_title() {
        return text_title;
    }

    public ImageView getImage_center() {
        return image_center;
    }
}
