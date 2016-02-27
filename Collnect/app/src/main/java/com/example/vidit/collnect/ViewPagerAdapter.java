package com.example.vidit.collnect;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.example.vidit.collnect.Tab2;

/**
 * Created by vidit on 02-10-2015.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created


    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(FragmentManager fm,CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if(position == 0) // if the position is 0 we are returning the First tab
        {
            Tab1 tab1 = new Tab1();
            return tab1;
        }
        else if(position == 1){
            //fragment = new Tab2()
            Tab2 tab2 = new Tab2();
            return tab2;
        }
        else if(position == 2){
            Tab3 tab3 = new Tab3();
            return tab3;
        }
        else if(position == 3){
            Tab4 tab4 = new Tab4();
            return tab4;
        }
        else if(position == 4){
            Tab5 tab5 = new Tab5();
            return tab5;
        }
        else if(position == 5){
            FacultySearch facultySearch = new FacultySearch();
            return facultySearch;
        }
        return fragment;

    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}