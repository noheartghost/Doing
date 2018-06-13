package app.doing.com.doing.utils.Decoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Space;

/**
 * Created by cherry on 18-6-13.
 * 用于为RecyclerView中的item提供间距
 */

public class SpacesItemDecoration extends RecyclerView.ItemDecoration{
    private int space;
    private int spacount;

    public SpacesItemDecoration(int space,int spacount){
        this.space = space;
        this.spacount = spacount;
    }

    public void getItemOffsets(Rect outRect, View view,RecyclerView parent,
                               RecyclerView.State state){
        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;

        if(parent.getChildLayoutPosition(view) < spacount ){
            outRect.top = 0;
        }else{
            outRect.top = space;
        }

    }
}
