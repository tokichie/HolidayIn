package jp.icecreamparfait.intern.cyberagent.holidayin;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;

public class MyTabListener implements ActionBar.TabListener {
    private Fragment mFragment;

    public MyTabListener(Fragment fragment) {
        mFragment = fragment;

    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        ft.add(R.id.container, mFragment, null);
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        // タブが切り替えられた時の処理
        // フラグメントを削除する
        ft.remove(mFragment);
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
        // 同じタブを再度タップされた時の処理
        // do nothing
    }
}
