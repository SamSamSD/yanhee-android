package com.example.helloworld.yhhospital;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class SwipAdapter extends FragmentStatePagerAdapter{

    public SwipAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentOne();
            case 1:
                return new FragmentTwo();
            case 2:
                return new FragmentThree();

                case 3:
                return new FragementFour();

        }
        Fragment pageFragment = new FragmentPager();
        Bundle bundle = new Bundle();
        bundle.putInt("pageNumber", position + 1);
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
