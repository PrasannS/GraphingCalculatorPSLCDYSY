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
import graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.Models.Expressions;

public class CalcActivity extends AppCompatActivity implements KeyBoardOneFragment.OnKeyboardOneReadListener, KeyBoardTwoFragment.OnKeyBoardTwoReadListener{


    private Button homebutton;
    private Button settingsbutton;
    private boolean isDecimal;
    private boolean firstform = false;
    private boolean onfunc = false;
    private TextView display;
    private String stringshown= "0";
    private int currentnum = 0;
    private int currentfunc = 0;
    Expressions e = new Expressions(new ArrayList<Expression>());
    private boolean betweenexpressions=false;
    private boolean formulamode = false;
    public ArrayList<Character>chars = new ArrayList<>();
    public ArrayList<Double>numbers = new ArrayList<>();
    public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        numbers.add(0.0);
        e.expressions.add(new Expression(chars,numbers));

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
        if(i<9 || i>-9 && !e.isDecimal(i)){
            if(betweenexpressions){
                e.expressions.add(new Expression(new ArrayList<Character>(), new ArrayList<Double>()));
                e.currentExpression++;
                currentfunc = 0;
                currentnum = 0;
                isDecimal = false;
                betweenexpressions = false;
            }

        /*if(e.expressions.get(e.expressions.size()-1).isSpecialMultiplier(e.expressions.get(e.expressions.size()-1).)){

        }*/

            if(onfunc){
                currentnum++;
                e.expressions.get(e.currentExpression).i.add(i);
                onfunc = false;
            }
            else if(e.expressions.get(e.currentExpression).i.size()<1){
                e.expressions.get(e.currentExpression).i.add(i);
            }
            else{
                if(firstform){
                    if(isDecimal){
                        firstform = false;
                        e.expressions.get(e.currentExpression).setvar(e.expressions.get(e.currentExpression).vars.get(0),i/Math.pow(10,e.getDecimalPlaces(e.expressions.get(e.currentExpression).i.get(currentnum))+1));
                    }
                    else {
                        firstform = false;
                        e.expressions.get(e.currentExpression).setvar(e.expressions.get(e.currentExpression).vars.get(0), i);
                    }
                }

                else if(isDecimal){
                    e.expressions.get(e.currentExpression).i.set(currentnum, e.expressions.get(e.currentExpression).i.get(currentnum)+(i/Math.pow(10,e.getDecimalPlaces(e.expressions.get(e.currentExpression).i.get(currentnum))+1)));
                }
                else
                    e.expressions.get(e.currentExpression).i.set(currentnum, e.expressions.get(e.currentExpression).i.get(currentnum)*10+i);

            }
            show(false);
        }

        else{
            e.expressions.get(e.currentExpression).i.add(i);
        }
    }

    public void enterfunc(char c){
        if(betweenexpressions){
            e.interFuncs.add(c);
            onfunc = true;
        }
        if(!onfunc){
            e.expressions.get(e.currentExpression).c.add(currentfunc,c);
            currentfunc++;
            onfunc = true;
        }
        else{
            e.expressions.get(e.currentExpression).c.set(currentfunc-1,c);
        }
        show(false);

    }
/*
    public void calculate(Expression e){
        e.expressions.get(e.currentExpression).i.add(e.getSolution());
    }
*/
    public void show(boolean a){
            stringshown = e.toString();
            display.setText(stringshown);
    }





    public void loadformula(String id){
        firstform = true;
        formulamode = true;
        Expression a = e.expressions.get(0).parseExpression("a+10*b", false);
        e.expressions.set(e.currentExpression,a);
        show(false);
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
                    isDecimal = true;
                    show(false);
                    break;
                case '(':
                    e.expressions.add(new Expression(new ArrayList<Character>(), new ArrayList<Double>(), '(',')'));
                    if(e.expressions.get(e.currentExpression).c.size()>e.expressions.get(e.currentExpression).i.size()){
                        e.interFuncs.add(e.expressions.get(e.currentExpression).c.get(e.expressions.get(e.currentExpression).c.size()-1));
                        e.expressions.get(e.currentExpression).c.remove(e.expressions.get(e.currentExpression).c.size()-1);
                    }
                    currentfunc = 0;
                    currentnum = 0;
                    isDecimal = false;
                    e.currentExpression++;
                    show(false);
                    break;
                case ')':
                    betweenexpressions=true;
                    show(false);
                    break;

                case'√':
                    e.expressions.add(new Expression(new ArrayList<Character>(), new ArrayList<Double>(), '√',')'));
                    if(e.expressions.get(e.currentExpression).c.size()>e.expressions.get(e.currentExpression).i.size()){
                        e.interFuncs.add(e.expressions.get(e.currentExpression).c.get(e.expressions.get(e.currentExpression).c.size()-1));
                        e.expressions.get(e.currentExpression).c.remove(e.expressions.get(e.currentExpression).c.size()-1);
                    }
                    currentfunc = 0;
                    currentnum = 0;
                    isDecimal = false;
                    e.currentExpression++;
                    break;

                default:
                    enterfunc(message.charAt(0));
                    break;

            }
        }
        else if(message.equals("enter")){
            if(formulamode&&e.expressions.get(e.currentExpression).vars.size()>0){
                firstform  = true;
                currentnum = e.expressions.get(e.currentExpression).i.indexOf(e.expressions.get(e.currentExpression).varcodes[0]);
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
        if(message.length()==1&&Character.isDigit(message.charAt(0))){
            enternum(Double.parseDouble(message));
        }
        else if(message.length()==1){
            enterfunc(message.charAt(0));
            switch (message.charAt(0)){
                case '.':
                    isDecimal = true;
                    show(false);
                    break;
                case '(':
                    e.expressions.add(new Expression(new ArrayList<Character>(), new ArrayList<Double>(), '(',')'));
                    if(e.expressions.get(e.currentExpression).c.size()>e.expressions.get(e.currentExpression).i.size()){
                        e.interFuncs.add(e.expressions.get(e.currentExpression).c.get(e.expressions.get(e.currentExpression).c.size()-1));
                        e.expressions.get(e.currentExpression).c.remove(e.expressions.get(e.currentExpression).c.size()-1);
                    }
                    currentfunc = 0;
                    currentnum = 0;
                    isDecimal = false;
                    e.currentExpression++;
                    show(false);
                    break;
                case ')':
                    betweenexpressions=true;
                    show(false);
                case '!':
                    double ans = e.expressions.get(e.expressions.size()-1).i.get(e.expressions.get(e.expressions.size()-1).i.size()-1);

                    if(e.isDecimal(ans))
                        display.setText("ERR");
                    else{
                        for(double i = ans-1; i >= 1; i--){
                            ans = ans * i;
                        }

                        enternum(ans);
                        currentnum++;
                    }
                    break;

            }
        }
        else if(message.equals("enter")){
            if(formulamode&&e.expressions.get(e.currentExpression).vars.size()>0){
                firstform  = true;
                currentnum = e.expressions.get(e.currentExpression).i.indexOf(e.expressions.get(e.currentExpression).varcodes[0]);
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

    public void solveExpressions(){
        e.solveAll();
        show(false);
        currentnum = 0;
        currentfunc = 0;
        onfunc = false;
    }
}
