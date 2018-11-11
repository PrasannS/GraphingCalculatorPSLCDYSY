package graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.Models;

import android.util.Log;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Arrays;

public class Expression {
    private double solution;
    public char[] bedmas = {'E', '%', '^','/','*','+','-'};
    public double[] varcodes = {624.62461224,118.1182341,1234532.1243414949,2359259.8912415,232302.9234123,23223230.234123};
    public int numvars=0;
    public ArrayList<Double>i ;
    public ArrayList<Character>c;
    public char separator ;
    public char close;
    public boolean hasSeparator = false;

    public ArrayList<Character> vars= new ArrayList<>();


    public Expression(ArrayList<Character>ct, ArrayList<Double>it){
        i = it;
        c= ct;
    }

    public Expression(ArrayList<Character>ct, ArrayList<Double>it, char sep, char clo){
        i = it;
        c= ct;
        separator = sep;
        hasSeparator = true;
        close = clo;
    }

    public double separatorProperties(double sep){
        if(separator=='√'){
            return Math.sqrt(sep);
        }
        return sep;
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
        solution = separatorProperties(solution);
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
            case '√':
                return Math.sqrt(a);
            case '%':
                return a%b;
            case 'E':
                return a * Math.pow(10, b);
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

    public Expression parseExpression(String input, boolean recur){
        if(recur||input.indexOf('(')<0){
            ArrayList<Double> nums = new ArrayList<>();
            ArrayList<Character> operators = new ArrayList<>();
            String tempnum = "";
            for(int i = 0;i<input.length();i++){
                if(Character.isDigit(input.charAt(i))||input.charAt(i)=='.'){
                    tempnum+=input.charAt(i);
                }
                else if(Character.isLowerCase(input.charAt(i))){
                    if(vars.size()>0){
                        if(vars.indexOf(input.charAt(i))>=0){
                            tempnum+=""+varcodes[vars.get(input.charAt(i))];
                        }
                        else{
                            vars.add(input.charAt(i));
                            tempnum+=""+varcodes[numvars];
                            numvars++;
                        }
                    }
                    else{
                        vars.add(input.charAt(i));
                        tempnum+=""+varcodes[numvars];
                        numvars++;
                    }
                }
                else{
                    operators.add(input.charAt(i));
                    nums.add(Double.parseDouble(tempnum));
                    tempnum = "";
                }
            }
            if(tempnum.length()>0)
                nums.add(Double.parseDouble(tempnum));
            Expression answer =  new Expression(operators,nums);
            answer.vars = this.vars;
            return answer;
        }
        else{
            ArrayList<Double> expressions = new ArrayList<>();
            ArrayList<String> strings = new ArrayList<>();
            ArrayList<Character> interfuncs;
            String in = input;
            while (in.indexOf('(')>=0){
                strings.add(in.substring(in.indexOf('(')+1,in.indexOf(')')));
                in = in.substring(0,in.indexOf('('))+in.substring(in.indexOf(')')+1);
            }
            Expression temp = parseExpression(in,true);
            interfuncs = (ArrayList<Character>)temp.c.subList(temp.i.size(),temp.c.size()-1);
            temp.c = (ArrayList<Character>)temp.c.subList(0,temp.i.size());
            expressions.add(temp.getSolution());
            for(String s:strings){
                if(s.indexOf('(')<0){
                    expressions.add(parseExpression(s,true).getSolution());
                }
                else{
                    expressions.add(parseExpression(s,false).getSolution());
                }
            }
            Expression answer = new Expression(interfuncs,expressions);
            answer.vars = this.vars;
            return answer;
        }


    }

    public void setvar(char var, double value){
        boolean done = false;
        double val = varcodes[vars.indexOf(var)];
        while(i.indexOf(val)>=0){
            i.set(i.indexOf(val),value);
        }
        varcodes = Arrays.copyOfRange(varcodes, 1, varcodes.length-1);
        vars.remove(vars.indexOf(var));
    }

    public char getvar(double d){
        char ans = '0';
        for(int i = 0; i<varcodes.length;i++){
            if(varcodes[i]==d){
                return vars.get(i);
            }
        }
        return ans;
    }

    public LineGraphSeries<DataPoint> graphSolve(String input){
        Log.d("readInput()", "recieved input to solve: " + input);
        ArrayList<Expression> expressions = new ArrayList<>();
        ArrayList<Double> nums = new ArrayList<Double>();
        ArrayList<Character> operators = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> inds = new ArrayList<>();

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
}
