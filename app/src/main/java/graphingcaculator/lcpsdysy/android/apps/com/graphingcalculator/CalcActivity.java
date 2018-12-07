package graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.Models.Expression;
import graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.Models.Formula;
import graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.Persistence.GraphingCalculatorDAO;

public class CalcActivity extends AppCompatActivity implements KeyBoardOneFragment.OnKeyboardOneReadListener, KeyBoardTwoFragment.OnKeyBoardTwoReadListener,CalcDisplayFragment.OnCalcDisplayReadListener{

    private Button addhistory;
    private Button homebutton;
    private Button settingsbutton;
    private TextView display;
    private String stringshown= "0";
    public Expression e;
    public double solution;
    public String last;
    private GraphingCalculatorDAO datasource=null;
    public static FragmentManager fragmentManager;
    public boolean first = true;

    public CalcActivity(){}



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
        datasource = new GraphingCalculatorDAO(this.getApplicationContext());
        datasource.open();

        display = (TextView) findViewById(R.id.display);
        display.setFocusable(false);
        display.setClickable(true);
        display.setText("0");
        /*display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomeActivity();
            }
        });*/

        addhistory = (Button)findViewById(R.id.addhistory);
        addhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(findViewById(R.id.fragmentcontainer)!=null){
                    if(savedInstanceState!=null){
                        return;
                    }
                    last = e.toString();
                    solution = e.getSolution();
                    CalcDisplayFragment calcDisplayFragment = CalcDisplayFragment.newInstance(last,solution);
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.history,calcDisplayFragment,null);
                    fragmentTransaction.commit();

                }
            }
        });



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
        if(first){
            first = false;
            e = new Expression();
            e.expressions.add(new Expression(0));
        }
        e.enternumber(i,false);
        show();
    }

    public void enterfunc(char c) {
        if (first){
            first = false;
        e = new Expression();
        e.expressions.add(new Expression(0));}
        e.enterfunction(c);
        show();
    }

    /*
        public void calculate(Expression e){
            e.Expression.get(e.currentExpression).i.add(e.getSolution());
        }
    */
    public void show(){
        stringshown = e.toString();
        display.setText(stringshown);
    }





    public void loadformula(String id){
        e= new Expression();
        first=false;
        e.parseExpression("-1-1-a");
        show();
    }


    @Override
    public void OnKeyboardOneRead(String message) {
        if(message.length()==1&&Character.isDigit(message.charAt(0))){
            enternum(Double.parseDouble(message));
        }
        else if(message.length()==1){
            switch (message.charAt(0)){
                case'π':
                    enternum(Math.PI);

                case '.':
                    e.isDecimal = true;
                    show();
                    break;
                case '(':
                    if(first) {
                        first = false;
                        e = new Expression(new ArrayList<Character>(),new ArrayList<Double>(),"(",")");
                        e.expressions.add(new Expression(0));
                    }
                    else{
                        e.addsepfunc(new Expression(new ArrayList<Character>(),new ArrayList<Double>(),"(",")"));
                    }
                    show();
                    break;
                case ')':
                    e.closeExpression();
                    show();
                    break;

                case'√':
                    if(first) {
                        first = false;
                        e = new Expression(new ArrayList<Character>(),new ArrayList<Double>(),"√",")");
                        e.expressions.add(new Expression(0));
                    }
                    else
                    e.addsepfunc(new Expression(new ArrayList<Character>(),new ArrayList<Double>(),"√",")"));

                    show();
                    break;

                case'!':
                    show();
                    break;

                default:
                    enterfunc(message.charAt(0));
                    break;

            }
        }
        else if(message.equals("degrad")){
            e.changeradians();
        }
        else if(message.equals("sin")){
            if(first) {
                first = false;
                e = new Expression(new ArrayList<Character>(),new ArrayList<Double>(),"sin",")");
                e.expressions.add(new Expression(0));
            }
            else
            e.addsepfunc(new Expression(new ArrayList<Character>(),new ArrayList<Double>(),"sin",")"));
            show();
        }
        else if(message.equals("cos")){
            if(first) {
                first = false;
                e = new Expression(new ArrayList<Character>(),new ArrayList<Double>(),"cos",")");
                e.expressions.add(new Expression(0));
            }
            else
            e.addsepfunc(new Expression(new ArrayList<Character>(),new ArrayList<Double>(),"cos",")"));
            show();
        }
        else if(message.equals("tan")){
            if(first) {
                first = false;
                e = new Expression(new ArrayList<Character>(),new ArrayList<Double>(),"tan",")");
                e.expressions.add(new Expression(0));
            }
            else
            e.addsepfunc(new Expression(new ArrayList<Character>(),new ArrayList<Double>(),"tan",")"));
            show();
        }
        else if(message.equals("cot")){
            if(first) {
                first = false;
                e = new Expression(new ArrayList<Character>(),new ArrayList<Double>(),"cot",")");
                e.expressions.add(new Expression(0));
            }
            else
            e.addsepfunc(new Expression(new ArrayList<Character>(),new ArrayList<Double>(),"cot",")"));
            show();
        }
        else if(message.equals("sec")){
            if(first) {
                first = false;
                e = new Expression(new ArrayList<Character>(),new ArrayList<Double>(),"sec",")");
                e.expressions.add(new Expression(0));
            }
            else
            e.addsepfunc(new Expression(new ArrayList<Character>(),new ArrayList<Double>(),"sec",")"));
            show();
        }
        else if(message.equals("csc")){
            if(first) {
                first = false;
                e = new Expression(new ArrayList<Character>(),new ArrayList<Double>(),"csc",")");
                e.expressions.add(new Expression(0));
            }
            else
            e.addsepfunc(new Expression(new ArrayList<Character>(),new ArrayList<Double>(),"csc",")"));
            show();
        }
        else if(message.equals("enter")){
            if(e.vars.size()>0&&e.vars.size()-1!=e.currentvar){
                    e.currentvar++;
            }
            else{
                try {
                    solveExpression();
                }
                catch(Exception e){
                    display.setText("ERR");
                    Log.d("There is an error","ERROR",e);
                }
                //currentfunc = 0;
                //currentnum = 0;
            }
        }
        else if(message.equals("quadratic")){
            loadformula("quadratic");
        }
        else if(message.equals("^2")){
            enterfunc('^');
            enternum(2);
        }
        else if(message.equals("clear")){
            openThisActivity();
        }
    }

    @Override
    public void OnKeyBoardTwoRead(String message) {
        OnKeyboardOneRead(message);
    }

    public void solveExpression(){
        addhistory.performClick();
        double temp = solution;
        e = new Expression(new ArrayList<Character>(),new ArrayList<Double>());
        e.expressions.add(new Expression(temp));
        show();
        //currentnum = 0;
        // currentfunc = 0;
        e.onfunc = false;
    }

    @Override
    public void OnCalcDisplayRead(double b) {
        e = new Expression(new ArrayList<Character>(),new ArrayList<Double>());
        e.expressions.add(new Expression(b));
        show();
        //currentnum = 0;
        // currentfunc = 0;
        e.onfunc = false;
    }


}