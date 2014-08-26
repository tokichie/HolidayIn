package jp.icecreamparfait.intern.cyberagent.holidayin.stores;

import android.app.Fragment;

/**
 * Created by tokitake on 2014/08/23.
 */
public class FragmentStore {
    private static FragmentStore instance = new FragmentStore();

    private static Fragment fragment_tab1 = null;
    private static Fragment fragment_tab2 = null;

    public static void setTab1Fragment(Fragment fragment) { fragment_tab1 = fragment; }

    public static void setTab2Fragment(Fragment fragment) { fragment_tab2 = fragment; }

    public static Fragment getTab1Fragment() { return fragment_tab1; }

    public static Fragment getTab2Fragment() { return fragment_tab2; }

}
