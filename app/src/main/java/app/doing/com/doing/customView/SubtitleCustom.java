package app.doing.com.doing.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import app.doing.com.doing.R;

/**
 * Created by cherry on 18-5-29.
 */

public class SubtitleCustom extends ConstraintLayout {
    private TextView textView;
    private LinearLayoutCompat button;

    public SubtitleCustom(@NonNull Context context, AttributeSet attrs) {
        super(context,attrs);
        LayoutInflater.from(context).inflate(R.layout.subtitle_custom,this);
        this.setMinHeight(96);
        textView = findViewById(R.id.subtitle_title);

        button = findViewById(R.id.subtitle_button);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SubtitleCustom);

        setText(typedArray.getString(R.styleable.SubtitleCustom_title));
        typedArray.recycle();
    }

    public void setText(String text){
        textView.setText(text);
    }

    public LinearLayoutCompat getButton() {
        return button;
    }
}
