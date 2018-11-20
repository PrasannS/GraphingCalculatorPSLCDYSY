package graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.Models;

import android.util.Log;

import org.json.JSONObject;

import java.util.ArrayList;

public class Formula {
    public String equationString;
    public String info;
    public String name;

    public Formula(){}



    public Formula(String e, String i, String n){
        equationString = e;
        info =i;
        name = n;
    }

    public Formula(String json){
        try {
            /*JSONObject response = new JSONObject(json);
            this.name = response.optString("title");

            this.info = response.optString("description");

            JSONObject imgObject = response.optJSONObject("originalimage");
            this.imageURL = imgObject.optString("source");

            JSONObject locObject = response.optJSONObject("coordinates");
            this.lat = Double.parseDouble(locObject.optString("lat"));
            this.lng = Double.parseDouble(locObject.optString("lon"));

            this.detailedDescription = response.optString("extract");*/
        }
        catch (Exception ex){
            Log.d("Formula",ex.getMessage());
        }
    }


}
