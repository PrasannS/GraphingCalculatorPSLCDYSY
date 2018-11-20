package graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.Models.Expression;

public class CalcActivity extends AppCompatActivity implements KeyBoardOneFragment.OnKeyboardOneReadListener, KeyBoardTwoFragment.OnKeyBoardTwoReadListener{


    private Button homebutton;
    private Button settingsbutton;
    private boolean firstform = false;
    private TextView display;
    private String stringshown= "0";
    Expression e = new Expression();
    public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        e.expressions.add(new Expression(0));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
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
        e.enternumber(i,false);
        show();
    }

    public void enterfunc(char c){
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
        firstform = true;
        e.parseExpression("a+10*b");
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
                    e.addsepfunc(new Expression(new ArrayList<Character>(),new ArrayList<Double>(),'(',')'));
                    show();
                    break;
                case ')':
                    e.closeExpression();
                    show();
                    break;

                case'√':
                    e.addsepfunc(new Expression(new ArrayList<Character>(),new ArrayList<Double>(),'√',')'));
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
        double temp = e.getSolution();
        e = new Expression(new ArrayList<Character>(),new ArrayList<Double>());
        e.expressions.add(new Expression(temp));
        show();
        //currentnum = 0;
        // currentfunc = 0;
        e.onfunc = false;
    }
}