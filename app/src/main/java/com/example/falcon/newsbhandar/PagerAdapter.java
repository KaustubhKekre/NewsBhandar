package com.example.falcon.newsbhandar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int noOfTabs;

    public PagerAdapter(FragmentManager fm,int noOfTabs) {
        super(fm);
        this.noOfTabs=noOfTabs;
    }


    @Override
    public Fragment getItem(int i) {
      switch (i)
      {
          case 0:
              Headlines headlines=new Headlines();
              return headlines;
          case 1:
              General general=new General();
              return general;
          case 2:
              Science science=new Science();
              return science;
          case 3:
              Technology technology=new Technology();
              return technology;
          case 4:
              Entertainment entertainment=new Entertainment();
              return entertainment;
          case 5:
              Business business=new Business();
              return business;
          case 6:
              Health health=new Health();
              return health;
          case 7:
              Sports sports=new Sports();
              return sports;
          default:
              return null;
      }
    }

    @Override
    public int getCount() {
        return noOfTabs;
    }
}
