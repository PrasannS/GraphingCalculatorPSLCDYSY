package graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.io.Console;
import java.lang.reflect.Array;
import java.security.spec.ECField;
import java.util.ArrayList;

import graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.Models.Expression;

public class CalcActivity extends AppCompatActivity implements KeyBoardOneFragment.OnKeyboardOneReadListener, KeyBoardTwoFragment.OnKeyBoardTwoReadListener{


    private Button homebutton;
    private Button settingsbutton;

    private boolean isDecimal = false;
    private boolean firstform = false;
    private int currentnum = 0;
    private int currentfunc = 0;
    private boolean onfunc = false;
    private int currentExpression=0;
    private TextView display;
    private String stringshown= "0";
    private ArrayList<Expression> expressions = new ArrayList<>();
    private ArrayList<Character> interFuncs = new ArrayList<>();
    private boolean betweenExpressions=false;
    private boolean formulamode = false;
    private String formulaID = "0";
    private int currentvar;
    public ArrayList<Character>chars = new ArrayList<>();
    public ArrayList<Double>numbers = new ArrayList<>();
    public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        numbers.add(0.0);
        expressions.add(new Expression(chars,numbers));

        fragmentManager = getSupportFragmentManager();
        if(findViewById(R.id.fragmentcontainer)!=null){
            if(savedInstanceState!=null){
                return;
            }
            KeyBoardOneFragment keyBoardOneFragment = new KeyBoardOneFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragmentcontainer,keyBoardOneFragment,null);
            fragmentTransaction.commit();

        }


        display = (TextView) findViewById(R.id.display);
        display.setText("0");
        /*display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomeActivity();
            }
        });*/


        homebutton = (Button)findViewById(R.id.homebutton);
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomeActivity();
            }
        });

        settingsbutton = (Button)findViewById(R.id.homebutton);
        settingsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSettingsActivity();
            }
        });

        display = (TextView) findViewById(R.id.display);
        display.setText("0");
        /*display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomeActivity();
            }
        });*/



    }

    public void openHomeActivity(){
        Intent intent1 = new Intent(this,HomeActivity.class);
        startActivity(intent1);
    }

    public void openThisActivity(){
        Intent intent1 = new Intent(this,CalcActivity.class);
        startActivity(intent1);
    }

    public void openSettingsActivity(){
        Intent intent1 = new Intent(this,HomeActivity.class);
        startActivity(intent1);
    }

    public void enternum(double i){

        if(betweenExpressions){
            expressions.add(new Expression(new ArrayList<Character>(), new ArrayList<Double>()));
            currentExpression++;
            currentfunc = 0;
            currentnum = 0;
            isDecimal = false;
            betweenExpressions = false;
        }

        if(onfunc){
            currentnum++;
            expressions.get(currentExpression).i.add(i);
            onfunc = false;
        }
        else if(expressions.get(currentExpression).i.size()<1){
            expressions.get(currentExpression).i.add(i);
        }
        else{
                if(firstform){
                if(isDecimal){
                    firstform = false;
                    expressions.get(currentExpression).setvar(expressions.get(currentExpression).vars.get(0),i/Math.pow(10,getDecimalPlaces(expressions.get(currentExpression).i.get(currentnum))+1));
                }
                else {
                    firstform = false;
                    expressions.get(currentExpression).setvar(expressions.get(currentExpression).vars.get(0), i);
                }
                }

                else if(isDecimal){
                    expressions.get(currentExpression).i.set(currentnum, expressions.get(currentExpression).i.get(currentnum)+(i/Math.pow(10,getDecimalPlaces(expressions.get(currentExpression).i.get(currentnum))+1)));
                }
                else
                    expressions.get(currentExpression).i.set(currentnum, expressions.get(currentExpression).i.get(currentnum)*10+i);

        }
        show();

    }

    public void enterfunc(char c){
        if(betweenExpressions){
            interFuncs.add(c);
            onfunc = true;
        }
        if(!onfunc){
            expressions.get(currentExpression).c.add(currentfunc,c);
            currentfunc++;
            onfunc = true;
        }
        else{
            expressions.get(currentExpression).c.set(currentfunc-1,c);
        }
        show();

    }
/*
    public void calculate(Expression e){
        expressions.get(currentExpression).i.add(e.getSolution());
    }
*/
    public void show(){
            stringshown = "";
            int ind;
            int funcind = 0;
            for(Expression e: expressions){
                if(e.hasSeparator){
                    stringshown+=e.separator;
                }
                ind = 0;
                for(double c:e.i){
                    if(e.getvar(c)!='0'){
                        stringshown+=e.getvar(c);
                    }
                    else if(getDecimalPlaces(c)==0){
                        stringshown+=(int)c;
                        if(isDecimal)
                            stringshown+=".0";}
                    else{
                        stringshown+=c+" ";}
                    if(ind<e.c.size()){
                        stringshown+=e.c.get(ind)+" ";
                        ind++;
                    }
                }
                if(e.hasSeparator){
                    stringshown+=e.close;
                }
                if(funcind<interFuncs.size()){
                    stringshown+=interFuncs.get(funcind);
                }
                funcind++;

            }

        display.setText(stringshown);

    }

    public void solveExpressions(){
        //boolean done = false;
        //int ind = funcs.indexOf('(');
            /*while(!done&&ind<funcs.size()){
                if(ind==-1)
                    break;
                tempnums.add(nums.remove(ind));
                tempfuncs.add(funcs.remove(ind));
                if(funcs.get(ind)==')'){
                    expressions.add(new Expression(tempfuncs, tempnums));
                    ind = funcs.indexOf('(');
                    tempnums.add(nums.remove(ind));
                    funcs.remove(ind);
                }

            }*/
            if(expressions.size()>1) {
                Expression solved = new Expression(interFuncs, new ArrayList<Double>());
                for (Expression e : expressions) {
                    if(e.c.size()>=e.i.size()){
                        interFuncs.add(e.c.get(e.c.size()-1));
                        e.c.remove(e.c.size()-1);
                    }
                    solved.i.add(e.getSolution());
                }
                expressions = new ArrayList<>();
                double solution = solved.getSolution();
                solved.c = new ArrayList<>();
                solved.i = new ArrayList<>();
                solved.i.add(solution);
                expressions.add(solved);
            }
            else{
                double solution = expressions.get(currentExpression).getSolution();
                expressions.get(currentExpression).c = new ArrayList<>();
                expressions.get(currentExpression).i = new ArrayList<>();
                expressions.get(currentExpression).i.add(solution);
            }
            currentnum = 0;
            currentfunc = 0;
            onfunc = false;
            currentExpression = 0;
            show();

        /*while(funcs.indexOf('(')!=-1){
            expressions.add(new Expression(new ArrayList<Character>(funcs.subList(funcs.indexOf('(')+1,funcs.indexOf(')'))),new ArrayList<Double>(nums.subList(funcs.indexOf('('),funcs.indexOf(')')+1))));
            ArrayList<Character> tempf2= new ArrayList<>(funcs.subList(0,funcs.indexOf('(')));
            funcs= new ArrayList<>(funcs.subList(funcs.indexOf(')')+1,funcs.size()));
            funcs.addAll(tempf2);
            ArrayList<Double> tempd2= new ArrayList<>(nums.subList(0,funcs.indexOf('(')+1));
            nums= new ArrayList<>(nums.subList(funcs.indexOf(')'),nums.size()));
            nums.addAll(tempd2);
        }
        if(nums.size()-funcs.size()<1) {
            nums = new ArrayList<>();
        }
        Expression answer = new Expression(funcs,nums);
        expressions.add(answer);
        for(Expression e: expressions){
            calculate(e);
        }
        nums = new ArrayList<>();
        funcs = new ArrayList<>();
        nums.add(answer.getSolution());
        show();
        */
    }

    public int getDecimalPlaces(double d){
        String s = Double.toString(d);
        String[] result = s.split("\\.");
        if(Double.parseDouble(result[1])!=0)
            return result[1].length();
        else
        return 0;
    }

    public void loadformula(String id){
        firstform = true;
        formulamode = true;
        Expression a = expressions.get(0).parseExpression("a+10*b", false);
        expressions.set(currentExpression,a);
        show();
    }


    @Override
    public void OnKeyboardOneRead(String message) {
        if(message.length()==1&&Character.isDigit(message.charAt(0))){
            enternum(Double.parseDouble(message));
        }
        else if(message.length()==1){
            enterfunc(message.charAt(0));
            switch (message.charAt(0)){
                case '.':
                    isDecimal = true;
                    show();
                    break;
                case '(':
                    expressions.add(new Expression(new ArrayList<Character>(), new ArrayList<Double>(), '(',')'));
                    if(expressions.get(currentExpression).c.size()>expressions.get(currentExpression).i.size()){
                        interFuncs.add(expressions.get(currentExpression).c.get(expressions.get(currentExpression).c.size()-1));
                        expressions.get(currentExpression).c.remove(expressions.get(currentExpression).c.size()-1);
                    }
                    currentfunc = 0;
                    currentnum = 0;
                    isDecimal = false;
                    currentExpression++;
                    show();
                    break;
                case ')':
                    betweenExpressions=true;
                    show();

            }
        }
        else if(message.equals("enter")){
            if(formulamode&&expressions.get(currentExpression).vars.size()>0){
                firstform  = true;
                currentnum = expressions.get(currentExpression).i.indexOf(expressions.get(currentExpression).varcodes[0]);
            }
            else{
                try {
                    solveExpressions();
                }
                catch(Exception e){
                    display.setText("ERR");
                    Log.d("There is an error","ERROR",e);
                }
                currentfunc = 0;
                currentnum = 0;
            }
        }
        else if(message.equals("quadratic")){
            loadformula("quadratic");
        }
        else if(message.equals("clear")){
            openThisActivity();
        }
    }

    @Override
    public void OnKeyBoardTwoRead(String message) {
        if(message.length()==1&&Character.isDigit(message.charAt(0))){
            enternum(Double.parseDouble(message));
        }
        else if(message.length()==1){
            enterfunc(message.charAt(0));
            switch (message.charAt(0)){
                case '.':
                    isDecimal = true;
                    show();
                    break;
                case '(':
                    expressions.add(new Expression(new ArrayList<Character>(), new ArrayList<Double>(), '(',')'));
                    if(expressions.get(currentExpression).c.size()>expressions.get(currentExpression).i.size()){
                        interFuncs.add(expressions.get(currentExpression).c.get(expressions.get(currentExpression).c.size()-1));
                        expressions.get(currentExpression).c.remove(expressions.get(currentExpression).c.size()-1);
                    }
                    currentfunc = 0;
                    currentnum = 0;
                    isDecimal = false;
                    currentExpression++;
                    show();
                    break;
                case ')':
                    betweenExpressions=true;
                    show();

            }
        }
        else if(message.equals("enter")){
            if(formulamode&&expressions.get(currentExpression).vars.size()>0){
                firstform  = true;
                currentnum = expressions.get(currentExpression).i.indexOf(expressions.get(currentExpression).varcodes[0]);
            }
            else{
                try {
                    solveExpressions();
                }
                catch(Exception e){
                    display.setText("ERR");
                    Log.d("There is an error","ERROR",e);
                }
                currentfunc = 0;
                currentnum = 0;
            }
        }
        else if(message.equals("quadratic")){
            loadformula("quadratic");
        }
        else if(message.equals("clear")){
            openThisActivity();
        }
    }
}
