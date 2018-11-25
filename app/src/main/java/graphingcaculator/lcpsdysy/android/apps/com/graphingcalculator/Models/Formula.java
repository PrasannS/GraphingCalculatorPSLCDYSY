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
        if(raw.equals("")){
            empty = true;
        }
        else {
            type = t;
            String temp = "";
            String next;
            String cur;
            Scanner scan = new Scanner(raw);
            alternate = scan.nextLine();
            cur = alternate;
            while(scan.hasNextLine()){
                next = scan.nextLine();
                if(next.equals("!@#$")){
                    info+=cur+"\n";
                    content.add(temp);
                    categories.add(cur);
                    if((cur.equals("Equation")||cur.equals("Basic dimensions")||(cur.equals("Result")&&cur.indexOf('+')!=0))){
                        scan.nextLine();
                        cur = scan.nextLine();
                        if(!equationString.equals(""))
                        equationString = cur;
                    }
                    cur = scan.nextLine();
                }
                else if(!next.equals("")){
                    if(!cur.equals("!@#$")){
                        temp+=cur;
                        info+=cur+"\n";
                    }

                    cur = next;
                }
            }
        }

    }

    public Formula(){}




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
