package jp.icecreamparfait.intern.cyberagent.holidayin;

import java.util.HashMap;

/**
 * Created by tokitake on 2014/08/26.
 */
public class RadioButtonIdHash {
    public static HashMap<Integer, Integer> hashForRequiredTime =
            new HashMap<Integer, Integer>() {
                {
                    put(R.id.radioButton1, 0);
                    put(R.id.radioButton2, 1);
                    put(R.id.radioButton3, 2);
                }
            };

    public static HashMap<Integer, Integer> hashForMovingTime =
            new HashMap<Integer, Integer>() {
                {
                    put(R.id.radioButton4, 0);
                    put(R.id.radioButton5, 1);
                    put(R.id.radioButton6, 2);
                }
            };

    public static HashMap<Integer, Integer> hashForMood =
            new HashMap<Integer, Integer>() {
                {
                    put(R.id.radioButton7, 0);
                    put(R.id.radioButton8, 1);
                    put(R.id.radioButton9, 2);
                    put(R.id.radioButton10, 3);
                }
            };
}
