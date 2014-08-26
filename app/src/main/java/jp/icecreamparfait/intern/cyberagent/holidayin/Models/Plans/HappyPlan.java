package jp.icecreamparfait.intern.cyberagent.holidayin.models.plans;

import java.util.HashMap;

/**
 * Created by tokitake on 2014/08/24.
 */
public class HappyPlan extends BasePlan{

    public HappyPlan() {
        this.belongingCategories =
                new HashMap<String, Integer>() {
                    {
                        put("4bf58dd8d48988d1e1931735", 90);  //Arcade
                        put("52f2ab2ebcbc57f1066b8b18", 60);  //ComicShop
                        put("4bf58dd8d48988d1f1931735", 90);  //Entertainment
//                        put("4bf58dd8d48988d1e7941735", 60);  //PlayGround
                    }
                };

        this.attachments =
                new HashMap<String, Integer>() {
                    {
                        put("4bf58dd8d48988d16e941735", 30);  //FastFood
                        put("4bf58dd8d48988d1cb941735", 30);  //StreetFood
                        put("4d4ae6fc7a7b7dea34424761", 30);  //FriedChicken
                        put("4bf58dd8d48988d16c941735", 30);  //BurgerShop
                    }
                };
    }
}
