package app.doing.com.doing.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import app.doing.com.doing.R;


/**
 * Created by cherry on 18-6-9.
 */

public class SelectTabCustom extends ConstraintLayout {
    private TextView text;
    private View line;

    public SelectTabCustom(Context context, AttributeSet attrs) {

        super(context, attrs);
        initView(context);
        initType(context, attrs);
    }

    private void initView(Context context){
        LayoutInflater.from(context).inflate(R.layout.select_tab_custom,this);

        text = findViewById(R.id.select_tab_text);
        line = findViewById(R.id.select_tab_line);
    }

    private void initType(Context context,AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SelectTabCustom);
        int totalAttributes = typedArray.getIndexCount();

        for(int i=0;i<totalAttributes;i++){
            int index = typedArray.getIndex(i);
            switch(index){
                case R.styleable.SelectTabCustom_select_title:
                    text.setText(typedArray.getString(index));
                    break;
                case R.styleable.SelectTabCustom_select_selected:
                    setSelected(typedArray.getBoolean(index,false));
                    break;
            }
        }
        typedArray.recycle();
    }

    public void setSelected(boolean selected){
        if(selected){
            int color = getResources().getColor(R.color.textDarkPrimary);
            text.setTextColor(color);
            line.setBackgroundColor(color);
        }else{
            text.setTextColor(getResources().getColor(R.color.natural));
            line.setBackgroundColor(getResources().getColor(R.color.textDarkInvisible));
        }
    }
}
