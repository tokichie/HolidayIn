package jp.icecreamparfait.intern.cyberagent.holidayin;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;

import jp.icecreamparfait.intern.cyberagent.holidayin.stores.FragmentStore;

public class MyTabListener implements ActionBar.TabListener {

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        Fragment fragment = (tab.getTag() == "tab1") ? FragmentStore.getTab1Fragment() : FragmentStore.getTab2Fragment();
        ft.replace(R.id.container, fragment);
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        Fragment fragment = (tab.getTag() == "tab1") ? FragmentStore.getTab1Fragment() : FragmentStore.getTab2Fragment();
        ft.remove(fragment);
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
    }
}
