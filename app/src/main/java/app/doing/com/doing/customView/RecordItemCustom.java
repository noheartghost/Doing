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
 * Created by cherry on 18-6-10.
 */

public class RecordItemCustom extends ConstraintLayout{
    private TextView name;
    private TextView content;
    private ImageButton more;

    public RecordItemCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.record_item_custom,this);
        initView();
        initType(context,attrs);
    }

    private void initView(){
        name = findViewById(R.id.record_item_name);
        content = findViewById(R.id.record_item_content);
        more = findViewById(R.id.record_item_more);
    }

    private void initType(Context context,AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.RecordItemCustom);
        int totalAttributes = typedArray.getIndexCount();

        for(int i=0;i<totalAttributes;i++){
            int index = typedArray.getIndex(i);
            switch (index){
                case R.styleable.RecordItemCustom_record_name:
                    name.setText(typedArray.getString(index));
                    break;
                case R.styleable.RecordItemCustom_record_content:
                    content.setText(typedArray.getString(index));
                    break;
            }
        }
        typedArray.recycle();
    }

    public void setName(String text){
        name.setText(text);
    }

    public void setContent(String text){
        content.setText(text);
    }



}
