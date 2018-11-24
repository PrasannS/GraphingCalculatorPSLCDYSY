package graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.Models;

import android.util.Log;

import org.json.JSONObject;

import java.util.ArrayList;

public class Formula {
    public String equationString;
    public String info;
    public String name;
    public String rawString;

    public Formula(String raw){
        rawString = raw;
    }

    public Formula(){}




    public Formula(String e, String i, String n){
        equationString = e;
        info =i;
        name = n;
    }



}
