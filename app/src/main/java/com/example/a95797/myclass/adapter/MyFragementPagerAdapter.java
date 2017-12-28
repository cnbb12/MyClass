package com.example.a95797.myclass.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;


public class MyFragementPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragmentsList;
    private ArrayList<String> titleList = new ArrayList();

    public MyFragementPagerAdapter(FragmentManager paramFragmentManager, ArrayList<String> paramArrayList, ArrayList<Fragment> paramArrayList1)
    {
        super(paramFragmentManager);
        this.fragmentsList = paramArrayList1;
        this.titleList = paramArrayList;
    }

    public int getCount()
    {
        return this.fragmentsList.size();
    }

    public Fragment getItem(int paramInt)
    {
        return (Fragment)this.fragmentsList.get(paramInt);
    }

    public CharSequence getPageTitle(int paramInt)
    {
        return (CharSequence)this.titleList.get(paramInt);
    }
}
