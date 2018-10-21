package graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.Models.Expression;

public class CalcActivity extends AppCompatActivity {

    private Button homebutton;
    private Button settingsbutton;
    private Button clearbutton;
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
    private int currentnum = 0;
    private int currentfunc = 0;
    private boolean onfunc = false;
    private ArrayList<Double> nums = new ArrayList<>();
    private ArrayList<Character> funcs = new ArrayList<>();
    private TextView display;
    private String stringshown= "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        nums.add(0.0);


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
                nums = new ArrayList<>();
                nums.add(0.0);
                funcs = new ArrayList<>();
                currentfunc = 0;
                currentnum = 0;
                show();
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
                calculate();
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

    public void openSettingsActivity(){
        Intent intent1 = new Intent(this,HomeActivity.class);
        startActivity(intent1);
    }

    public void enternum(double i){

        if(onfunc){
            currentnum++;
            nums.add(i);
            onfunc = false;
        }
        else{
            nums.set(currentnum, nums.get(currentnum)*10+i);
        }

    }

    public void enterfunc(char c){
        funcs.add(c);
        currentfunc++;
        onfunc = true;
    }

    public void calculate(){
        Expression e = new Expression(funcs,nums);
        nums = new ArrayList<>();
        nums.add(e.getSolution());
        funcs=new ArrayList<>();
        show();
    }

    public void show(){
            stringshown = "";
            int ind =0;
            for(double c:nums){
                stringshown+=c+" ";
                if(ind<funcs.size()){
                    stringshown+=funcs.get(ind)+" ";
                    ind++;
                }
            }

        display.setText(stringshown);

    }
}
