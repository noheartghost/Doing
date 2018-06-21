package app.doing.com.doing.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.TextView;

import app.doing.com.doing.R;

/**
 * Created by cherry on 18-6-16.
 */

public class TextLineCustom extends ConstraintLayout{
    private TextView content;
    private TextView hint;
    private ImageButton more;

    public TextLineCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.text_line_custom,this);
        initView();
        initType(context,attrs);

    }

    private void initView(){
        content = findViewById(R.id.text_line_content);
        hint = findViewById(R.id.text_line_hint);
        more = findViewById(R.id.text_line_more);

    }

    private void initType(Context context,AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.TextLineCustom);
        int totalAttributes = typedArray.getIndexCount();

        for(int i=0;i<totalAttributes;i++){
            int index = typedArray.getIndex(i);
            switch (index){
                case R.styleable.TextLineCustom_textline_content:
                    content.setText(typedArray.getString(index));
                    break;
                case R.styleable.TextLineCustom_textline_hint:
                    hint.setText(typedArray.getString(index));
                    break;
            }
        }
        typedArray.recycle();
    }

    public void setContent(String text){
        content.setText(text);
    }

    public void setHint(String text){
        hint.setText(text);
    }

}
