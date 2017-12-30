package com.example.a95797.myclass.module.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.a95797.myclass.R;
import com.example.a95797.myclass.module.fragment.HomeworkFragment;
import com.example.a95797.myclass.module.fragment.OneselfFragment;
import com.example.a95797.myclass.module.fragment.PreFragment;
import com.example.a95797.myclass.utils.NoScrollViewPager;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private PreFragment pre;
    private HomeworkFragment homework;
    private OneselfFragment oneself;
    Button prebt;
    Button homeworkbt;
    Button oneselfbt;
    private ArrayList<String> titleList = new ArrayList();
    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    NoScrollViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initbt();
        initViewpager();
        this.prebt.setOnClickListener(this);
        this.homeworkbt.setOnClickListener(this);
        this.oneselfbt.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.pre:
                Log.d("Button", "onClick1: ");
                this.prebt.setSelected(true);
                prebt.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.mipmap.pre), null, null);
                this.homeworkbt.setSelected(false);
                homeworkbt.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.mipmap.re_homework), null, null);
                this.oneselfbt.setSelected(false);
                homeworkbt.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.mipmap.re_oneself), null, null);
//                this.viewPager.setCurrentItem(0);
                break;
            case R.id.homework:
                Log.d("Button", "onClick2: ");
                this.prebt.setSelected(false);
                prebt.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.mipmap.re_pre), null, null);
                this.homeworkbt.setSelected(true);
                homeworkbt.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.mipmap.homework), null, null);
                this.oneselfbt.setSelected(false);
                oneselfbt.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.mipmap.re_oneself), null, null);
//                this.viewPager.setCurrentItem(1);
                break;
            case R.id.oneself:
                Log.d("Button", "onClick3: ");
                this.prebt.setSelected(false);
                prebt.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.mipmap.re_pre), null, null);
                this.homeworkbt.setSelected(false);
                homeworkbt.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.mipmap.re_homework), null, null);
                this.oneselfbt.setSelected(true);
                oneselfbt.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.mipmap.oneself), null, null);
//                this.viewPager.setCurrentItem(2);
                break;
            default:
                break;
        }
    }

    private void initbt() {
        this.prebt = (Button)findViewById(R.id.pre);
        this.homeworkbt = (Button)findViewById(R.id.homework);
        this.oneselfbt = (Button)findViewById(R.id.oneself);
        this.prebt.setSelected(true);
    }

    private void initViewpager()
    {

        this.pre = new PreFragment();
        this.homework = new HomeworkFragment();
        this.oneself = new OneselfFragment();
        this.fragmentList.add(this.pre);
        this.fragmentList.add(this.homework);
        this.fragmentList.add(this.oneself);
        this.titleList.add("课前预习");
        this.titleList.add("课后习题");
        this.titleList.add("自学拓展");
//        PagerAdapter localObject = new MyFragementPagerAdapter(getSupportFragmentManager(), this.titleList, this.fragmentList);
//        this.viewPager.setAdapter(localObject);
//        this.viewPager.setOffscreenPageLimit(2);
//        this.viewPager.setNoScroll(true);

        this.prebt.setSelected(true);
        this.homeworkbt.setSelected(false);
        this.oneselfbt.setSelected(false);
        //this.viewPager.setCurrentItem(0);


    }



}
