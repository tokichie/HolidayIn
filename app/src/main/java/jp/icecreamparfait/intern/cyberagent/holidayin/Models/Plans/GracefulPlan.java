package jp.icecreamparfait.intern.cyberagent.holidayin.Models.Plans;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import br.com.condesales.models.Venue;
import jp.icecreamparfait.intern.cyberagent.holidayin.Models.Plan;
import jp.icecreamparfait.intern.cyberagent.holidayin.QueryStore;

/**
 * Created by tokitake on 2014/08/24.
 */
public class GracefulPlan extends BasePlan{

    public GracefulPlan() {
        this.belongingCategories =
                new HashMap<String, Integer>() {
                    {
                        put("4bf58dd8d48988d1e2931735", 45);  //ArtGallery
                        put("4bf58dd8d48988d127951735", 30);  //Arts&CraftsStore
                        //put("4bf58dd8d48988d114951735", 60);  //BookStore
                        put("4deefb944765f83613cdba6e", 60);  //HistorySite
                        put("4bf58dd8d48988d181941735", 90);  //Museum
                    }
                };

        this.attachments =
                new HashMap<String, Integer>() {
                    {
                        put("4bf58dd8d48988d16d941735", 60);  //Cafe
                        put("4bf58dd8d48988d128941735", 60);  //Cafeteria
                        put("4bf58dd8d48988d1e0931735", 60);  //CoffeeShop
                        put("4bf58dd8d48988d1dc931735", 60);  //Tearoom
                        put("4bf58dd8d48988d110941735", 45);  //Italian
                    }
                };
    }
}
