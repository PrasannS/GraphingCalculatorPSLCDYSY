package graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.io.Console;
import java.lang.reflect.Array;
import java.security.spec.ECField;
import java.util.ArrayList;

import graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.Models.Expression;

public class CalcActivity extends AppCompatActivity {

    private Button openbracket;
    private Button closedbracket;
    private Button homebutton;
    private Button settingsbutton;
    private Button clearbutton;
    private Button decimal;
    private Button zero;
    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button six;
    private Button seven;
    private Button eight;
    private Button nine;
    private Button enter;
    private Button add;
    private Button subtract;
    private Button divide;
    private Button multiply;
    private Button exponent;
    private Button E;
    private Button mod;
    private Button sqrt;
    private Button squared;
    private boolean isDecimal = false;
    private int currentnum = 0;
    private int currentfunc = 0;
    private boolean onfunc = false;
    private int currentExpression=0;
    private TextView display;
    private String stringshown= "0";
    private ArrayList<Expression> expressions = new ArrayList<>();
    private ArrayList<Character> interFuncs = new ArrayList<>();
    private boolean betweenExpressions=false;
    public ArrayList<Character>chars = new ArrayList<>();
    public ArrayList<Double>numbers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        numbers.add(0.0);
        expressions.add(new Expression(chars,numbers));


        display = (TextView) findViewById(R.id.display);
        display.setText("0");
        /*display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomeActivity();
            }
        });*/

        clearbutton = (Button)findViewById(R.id.clear);
        clearbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openThisActivity();
            }
        });

        homebutton = (Button)findViewById(R.id.homebutton);
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomeActivity();
            }
        });

        enter = (Button)findViewById(R.id.enter);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

        add = (Button)findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterfunc('+');
                show();
            }
        });

        subtract = (Button)findViewById(R.id.subtract);
        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterfunc('-');
                show();
            }
        });

        divide = (Button)findViewById(R.id.divide);
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterfunc('/');
                show();
            }
        });

        multiply = (Button)findViewById(R.id.multiply);
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterfunc('*');
                show();
            }
        });

        exponent = (Button)findViewById(R.id.exponent);
        exponent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterfunc('^');
                show();
            }
        });

        E = (Button)findViewById(R.id.E);
        E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterfunc('E');
                show();
            }
        });

        mod = (Button)findViewById(R.id.mod);
        mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterfunc('%');
                show();
            }
        });

        squared = (Button)findViewById(R.id.squared);
        squared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterfunc('^');
                enternum(2);
                show();
            }
        });

        sqrt = (Button)findViewById(R.id.sqrt);
        sqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expressions.add(new Expression(new ArrayList<Character>(), new ArrayList<Double>(), '√',')'));
                if(expressions.get(currentExpression).c.size()>expressions.get(currentExpression).i.size()){
                    interFuncs.add(expressions.get(currentExpression).c.get(expressions.get(currentExpression).c.size()-1));
                    expressions.get(currentExpression).c.remove(expressions.get(currentExpression).c.size()-1);
                }
                currentfunc = 0;
                currentnum = 0;
                isDecimal = false;
                currentExpression++;
                show();
    }
});


        decimal = (Button)findViewById(R.id.decimal);
        decimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isDecimal = true;
                show();
            }
        });

        openbracket = (Button)findViewById(R.id.openbracket);
        openbracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
            }
        });

        closedbracket = (Button)findViewById(R.id.closedbracket);
        closedbracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                betweenExpressions=true;
                show();
            }
        });




        one = (Button)findViewById(R.id.one);
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enternum(1);
                show();
            }
        });

        two = (Button)findViewById(R.id.two);
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enternum(2);
                show();
            }
        });

        three = (Button)findViewById(R.id.three);
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enternum(3);
                show();
            }
        });

        four = (Button)findViewById(R.id.four);
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enternum(4);
                show();
            }
        });

        five = (Button)findViewById(R.id.five);
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enternum(5);
                show();
            }
        });

        six = (Button)findViewById(R.id.six);
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enternum(6);
                show();
            }
        });

        seven = (Button)findViewById(R.id.seven);
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enternum(7);
                show();
            }
        });

        eight = (Button)findViewById(R.id.eight);
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enternum(8);
                show();
            }
        });

        nine = (Button)findViewById(R.id.nine);
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enternum(9);
                show();
            }
        });

        zero = (Button)findViewById(R.id.zero);
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enternum(0);
                show();
            }
        });


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
            if(isDecimal){
                expressions.get(currentExpression).i.set(currentnum, expressions.get(currentExpression).i.get(currentnum)+(i/Math.pow(10,getDecimalPlaces(expressions.get(currentExpression).i.get(currentnum))+1)));
            }
            else
                expressions.get(currentExpression).i.set(currentnum, expressions.get(currentExpression).i.get(currentnum)*10+i);
        }

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
                    if(getDecimalPlaces(c)==0){
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
}
