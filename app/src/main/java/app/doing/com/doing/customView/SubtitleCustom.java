package app.doing.com.doing.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import app.doing.com.doing.R;

/**
 * Created by cherry on 18-5-29.
 */

public class SubtitleCustom extends FrameLayout {
    private TextView textView;
    private int index;
    private ImageButton imageButton;

    public SubtitleCustom(@NonNull Context context, AttributeSet attrs) {
        super(context,attrs);
        LayoutInflater.from(context).inflate(R.layout.subtitle_custom,this);
        textView = findViewById(R.id.subtitle_title);
        imageButton = findViewById(R.id.subtitle_right);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SubtitleCustom);

        setText(typedArray.getString(R.styleable.SubtitleCustom_title));
        index = typedArray.getInteger(R.styleable.SubtitleCustom_sub_index, 0);
    }

    public void setText(String text){
        textView.setText(text);
    }
}
