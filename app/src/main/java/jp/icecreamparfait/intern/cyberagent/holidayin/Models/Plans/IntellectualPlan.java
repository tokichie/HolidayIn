package jp.icecreamparfait.intern.cyberagent.holidayin.Models.Plans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.condesales.EasyFoursquareAsync;
import br.com.condesales.criterias.VenuesCriteria;
import br.com.condesales.listeners.FoursquareVenuesRequestListener;
import br.com.condesales.models.Venue;

/**
 * Created by tokitake on 2014/08/24.
 */
public class IntellectualPlan{
    public static HashMap<String, Integer> belongingCategories =
            new HashMap<String, Integer>() {
                {
                    put("4bf58dd8d48988d1e2931735", 45);  //ArtGallery
                    put("4bf58dd8d48988d127951735", 30);  //Arts&CraftsStore
                    put("4bf58dd8d48988d114951735", 60);  //BookStore
                    put("4deefb944765f83613cdba6e", 60);  //HistorySite
                    put("4bf58dd8d48988d181941735", 90);  //Museum
                }
            };

    public static HashMap<String, Integer> cafes =
            new HashMap<String, Integer>() {
                {
                    put("4bf58dd8d48988d16d941735", 60);  //Cafe
                    put("4bf58dd8d48988d128941735", 60);  //Cafeteria
                    put("4bf58dd8d48988d1e0931735", 60);  //CoffeeShop
                    put("4bf58dd8d48988d1dc931735", 60);  //Tearoom
                }
            };

    public static void makePlan(ArrayList<Venue> venues, int amountTime) {

    }

}
