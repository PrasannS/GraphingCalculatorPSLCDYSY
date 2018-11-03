package graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.Models;

import android.util.Log;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class Expression {
    private double solution;
    public char[] bedmas = {'^','/','*','+','-'};
    public ArrayList<Double>i ;
    public ArrayList<Character>c ;
    public char separator ;
    public char close;
    public boolean hasSeparator = false;
    public Expression(ArrayList<Character>ct,ArrayList<Double>it){
        i = it;
        c= ct;
        if(i.size()>0) {
            int ref = c.size();
            int ind;
            char symbol;
            while (ref > 1) {
                ind = getInd();
                symbol = c.get(ind);
                i.add(ind, evaluate(i.get(ind), i.get(ind + 1), symbol));
                i.remove(ind + 1);
                i.remove(ind + 1);
                c.remove(ind);
                ref--;
            }
            solution = i.get(0);
        }
    }

    public Expression(ArrayList<Character>ct,ArrayList<Double>it, char sep, char clo){
        i = it;
        c= ct;
        if(i.size()>0) {
            int ref = c.size();
            int ind;
            char symbol;
            while (ref >= 1) {
                ind = getInd();
                symbol = c.get(ind);
                i.add(ind, evaluate(i.get(ind), i.get(ind + 1), symbol));
                i.remove(ind + 1);
                i.remove(ind + 1);
                c.remove(ind);
                ref--;
            }
            solution = i.get(0);
        }
        separator = sep;
        hasSeparator = true;
        close = clo;
    }

    public double getSolution(){
        int ref = c.size();
        int ind;
        char symbol;
        while(ref>=1){
            ind = getInd();
            symbol = c.get(ind);
            i.add(ind,evaluate(i.get(ind),i.get(ind+1),symbol));
            i.remove(ind+1);
            i.remove(ind+1);
            c.remove(ind);
            ref--;
        }
        solution = i.get(0);
        return solution;
    }

    public double evaluate(double a, double b, char c){
        switch (c){
            case '*':
                return a*b;
            case '/':
                return a/b;
            case '+':
                return a+b;
            case '-':
                return a-b;
            case '^':
                return Math.pow(a, b);
        }
        return a;
    }

    public int getInd(){
        boolean ready = false;
        int ans = 100;
        int ind = 0;
        char current;
        while(ind<=bedmas.length-1&&!ready){
            current = bedmas[ind];
            for(int i = 0; i<c.size(); i++){
                if(c.get(i)==current){
                    ans = i;
                    ready = true;
                    break;
                }
            }
            ind++;
        }
        return ans;

    }

    public double simpleSolveFromString(String input){
        ArrayList<Expression> expressions = new ArrayList<>();
        ArrayList<Double> nums = new ArrayList<Double>();
        ArrayList<Character> operators = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++)
        {
            char cur = input.charAt(i);
            if (cur > 47 && cur < 58 || cur == 46)
                sb.append(cur);
            else
            {
                nums.add(Double.parseDouble(sb.toString()));
                sb.delete(0, sb.length());
                operators.add(cur);
            }
        }
        Expression answer =  new Expression(operators,nums);
        return answer.getSolution();


    }

    public LineGraphSeries<DataPoint> graphSolve(String input){
        Log.d("readInput()", "recieved input to solve: " + input);
        ArrayList<Expression> expressions = new ArrayList<>();
        ArrayList<Double> nums = new ArrayList<Double>();
        ArrayList<Character> operators = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> inds = new ArrayList<>();
        try
        {
            for (int i = 0; i < input.length(); i++)
            {
                char cur = input.charAt(i);
                //Log.d("readInput()", cur + "");
                if (cur > 47 && cur < 58 || cur == 46)
                    sb.append(cur);
                else if (cur == 120)
                {
                    if (sb.length() == 0)
                        sb.append(1);
                    nums.add(Double.parseDouble(sb.toString()));
                    operators.add('*');
                    sb.delete(0, sb.length());
                    inds.add(nums.size());
                    nums.add(0.0);
                }
                else if (cur == 32);
                else
                {
                    if (!(sb.toString().length() == 0))
                        nums.add(Double.parseDouble(sb.toString()));
                    sb.delete(0, sb.length());
                    operators.add(cur);
                }
            }
            if (!(sb.toString().length() == 0))
                nums.add(Double.parseDouble(sb.toString()));
            LineGraphSeries<DataPoint> ansSeries = new LineGraphSeries<DataPoint>();
            for (double i = -1000; i < 1000; i += 0.1)
            {
                for (int j = 0; j < inds.size(); j++)
                    nums.set(inds.get(j), i);
                ArrayList<Double> holdNums = new ArrayList<>();
                ArrayList<Character> holdOp = new ArrayList<>();
                for (int j = 0; j < nums.size(); j++)
                    holdNums.add(nums.get(j));
                for (int j = 0; j < operators.size(); j++)
                    holdOp.add(operators.get(j));
                Expression answer = new Expression(holdOp, holdNums);
                ansSeries.appendData(new DataPoint(i, answer.getSolution()), false, 100000);
            }
            return ansSeries;
        }
        catch (Exception e)
        {
            return new LineGraphSeries<DataPoint>();
        }
    }
}
