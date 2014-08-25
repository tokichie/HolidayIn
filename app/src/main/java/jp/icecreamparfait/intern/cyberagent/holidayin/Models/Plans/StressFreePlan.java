package jp.icecreamparfait.intern.cyberagent.holidayin.Models.Plans;

import java.util.HashMap;

/**
 * Created by tokitake on 2014/08/24.
 */
public class StressFreePlan extends BasePlan{

    public StressFreePlan() {
        this.belongingCategories =
                new HashMap<String, Integer>() {
                    {
                        put("4bf58dd8d48988d103951735", 60);  //Apparel
                        put("4bf58dd8d48988d1f6941735", 90);  //DepartmentStore
                        put("52dea92d3cf9994f4e043dbb", 90);  //DiscountStore
                        put("4bf58dd8d48988d1fb941735", 60);  //HobbyShop
                        put("4bf58dd8d48988d17f941735", 150); //Theater
                        put("4bf58dd8d48988d117951735", 30);  //CandyStore
                        put("52f2ab2ebcbc57f1066b8b42", 90);  //BigBoxStore
                    }
                };

        this.attachments =
                new HashMap<String, Integer>() {
                    {
                        put("4bf58dd8d48988d11c941735", 90);  //SakeBar
                        put("4bf58dd8d48988d186941735", 15);  //LiquorStore
                        put("5370f356bcbc57f1066c94c2", 60);  //BeerGarden
                        put("4bf58dd8d48988d119951735", 30);  //WineShop
                    }
                };
    }
}
