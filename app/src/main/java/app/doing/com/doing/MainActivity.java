package app.doing.com.doing;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import app.doing.com.doing.find.FindFragment;
import app.doing.com.doing.handpick.HandpickFragment;
import app.doing.com.doing.me.MeFragment;
import app.doing.com.doing.moment.MomentFragment;

public class MainActivity extends Activity implements View.OnClickListener{
    private HandpickFragment handpickFragment;
    private FindFragment findFragment;
    private MomentFragment momentFragment;
    private MeFragment meFragment;

    private View handpickText;
    private View findText;
    private View momentText;
    private View meText;

    /*对Fragment进行管理*/
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化布局元素
        initViews();
        fragmentManager = getFragmentManager();
        setTabSelection(0);
    }

    private void initViews(){

        handpickText = findViewById(R.id.handpick_text);
        findText = findViewById(R.id.find_text);
        momentText = findViewById(R.id.moment_text);
        meText = findViewById(R.id.me_text);
        handpickText.setOnClickListener(this);
        findText.setOnClickListener(this);
        momentText.setOnClickListener(this);
        meText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.handpick_text:
                setTabSelection(0);
                break;
            case R.id.find_text:
                setTabSelection(1);
                break;
            case R.id.moment_text:
                setTabSelection(2);
                break;
            case R.id.me_text:
                setTabSelection(3);
                break;
            default:
                break;
        }
    }

    /*根据传入的index设置显示的tab*/
    private void setTabSelection(int index){
        //每次切换fragment时，开启一个新的fragment事物
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        switch(index){
            case 0:
                if(handpickFragment == null){
                    handpickFragment = new HandpickFragment();

                    transaction.add(R.id.content,handpickFragment);
                }else{
                    transaction.show(handpickFragment);
                }
                break;
            case 1:
                if(findFragment == null){
                    findFragment = new FindFragment();
                    transaction.add(R.id.content,findFragment);
                }else{
                    transaction.show(findFragment);
                }
                break;
            case 2:
                if(momentFragment == null){
                    momentFragment = new MomentFragment();
                    transaction.add(R.id.content,momentFragment);
                }else{
                    transaction.show(momentFragment);
                }
                break;
            case 3:
            default:
                if(meFragment == null){
                    meFragment = new MeFragment();
                    transaction.add(R.id.content,meFragment);
                }else{
                    transaction.show(meFragment);
                }
                break;
        }
        transaction.commit();
    }

    /*用于将原有fragment隐藏起来*/
    private void hideFragments(FragmentTransaction transaction){
        if(handpickFragment != null){
            transaction.hide(handpickFragment);
        }
        if(findFragment != null){
            transaction.hide(findFragment);
        }
        if(momentFragment != null){
            transaction.hide(momentFragment);
        }
        if(meFragment != null){
            transaction.hide(meFragment);
        }
    }


}
