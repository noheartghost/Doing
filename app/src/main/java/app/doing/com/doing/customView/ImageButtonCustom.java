package app.doing.com.doing.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
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
 */

public class ImageButtonCustom extends FrameLayout {
    private ImageView imageView;
    private TextView textView;
    private int index;

    public ImageButtonCustom(@NonNull Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.imagebutton_custom, this);
        imageView = findViewById(R.id.image_id);
        textView = findViewById(R.id.text_id);
        //imageView.setOnClickListener(this);
        //textView.setOnClickListener(this);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImageButtonCustom);
        setText(typedArray.getString(R.styleable.ImageButtonCustom_text));
        setImage(typedArray.getResourceId(R.styleable.ImageButtonCustom_image, -1));
        index = typedArray.getInteger(R.styleable.ImageButtonCustom_index, 0);

    }

    public void setText(String text){
        textView.setText(text);
    }

    public void setImage(int sourceId){
        imageView.setBackgroundResource(sourceId);
    }


    public void onClick(View v) {
        Log.i("ImageButton","clicked");
    }
}
