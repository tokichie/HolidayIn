package jp.icecreamparfait.intern.cyberagent.holidayin.models.plans;

import java.util.HashMap;

/**
 * Created by tokitake on 2014/08/24.
 */
public class FreePlan extends BasePlan{

    public FreePlan() {
        this.belongingCategories =
                new HashMap<String, Integer>() {
                    {
                        put("4bf58dd8d48988d1e2941735", 45);  //Beach
                        put("4bf58dd8d48988d1df941735", 15);  //Bridge
                        put("4bf58dd8d48988d1ae941735", 60);  //College
                        put("4bf58dd8d48988d15f941735", 45);  //Field
                        put("4bf58dd8d48988d163941735", 45);  //Park
                        put("4bf58dd8d48988d164941735", 45);  //Plaza
                        put("4bf58dd8d48988d165941735", 45);  //ScenicLookout
                        put("4bf58dd8d48988d17b941735", 90);  //Zoo
                    }
                };

        this.attachments =
                new HashMap<String, Integer>() {
                    {
                        put("4bf58dd8d48988d16d941735", 60);  //Cafe
                        put("4bf58dd8d48988d128941735", 60);  //Cafeteria
                        put("4bf58dd8d48988d1e0931735", 60);  //CoffeeShop
                        put("4bf58dd8d48988d1dc931735", 60);  //Tearoom
                    }
                };
    }
}
