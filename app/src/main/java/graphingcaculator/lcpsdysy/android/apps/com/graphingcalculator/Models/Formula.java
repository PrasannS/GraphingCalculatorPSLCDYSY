package graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.Models;

import android.util.Log;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Scanner;

import graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.FormulaListGenerator;

public class Formula {
    public String equationString = "";
    public String info="";
    public String name;
    public ArrayList<String>categories;
    public ArrayList<String>content;
    public boolean empty = false;
    public int type;
    public String alternate;
    public static String[] types = {"Math","Chemistry","Physics"};

    public Formula(String raw, int t, String s){
        name = s;
        try{
        if(raw.equals("")){
            empty = true;
            type =t;
            info = types[type]+"\n"+"Sorry we don't have any data on this. Press the below button to report if data is innacurate, incomplete, or nonexistent";
        }

        else {
            /**TODO-we will have to figure out how to extract equations from info*/
            type = t;
            info = types[type]+"\n"+raw;
            /*
            String temp = "";
            String next;
            String cur;
            Scanner scan = new Scanner(raw);
            scan.nextLine();
            alternate = scan.nextLine();
            cur = alternate;
            while(scan.hasNextLine()){
                next = scan.nextLine();
                if(next.equals("!@#$%")){
                    info+=cur+"---\n";
                    if((cur.equals("Equation")||cur.equals("Basic dimensions")||cur.equals("Result"))){
                        if(cur.equals("Result")){
                            cur = scan.nextLine();
                            if(cur.indexOf('+')!=-1||cur.indexOf('=')!=-1){
                                equationString+=cur+",,,";
                            }
                        }
                        else{
                        cur = scan.nextLine();
                        equationString+=cur+",,,";}
                    }
                    cur = scan.nextLine();
                }
                else if(!next.equals("")){
                    if(!cur.equals("!@#$%")){
                        temp+=cur;
                        info+=cur+"\n";
                    }

                    cur = next;
                }
            }
            scan.close();*/

        }}
        catch (Exception e){
            Log.d("Formula scan error","",e);
        }

    }

    public Formula(){}

    public Formula(String s){
        name = s;
    }




    public Formula(String e, String i, String n){
        equationString = e;
        info =i;
        name = n;
    }

    public String toString (){
        String ret = "";
        ret+=name+"\n";
        ret+=types[type]+"\n";
        ret+=info+"\n";
        return ret;
    }



}
