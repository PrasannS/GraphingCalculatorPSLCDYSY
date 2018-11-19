package graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.Models;

import android.util.Log;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Arrays;

public class Expression {
    private double solution;
    public char[] bedmas = {'E', '%', '^','/','*','+','-'};
    //public double[] varcodes = {624.62461224,118.1182341,1234532.1243414949,2359259.8912415,232302.9234123,23223230.234123};
    public ArrayList<Character>c;
    public ArrayList<Expression>expressions;
    public char separator ;
    public char close;
    public boolean hasSeparator = false;
    public int currentExpression=0;
    public String info;
    public String name;
    public boolean isnum = false;
    public double num;
    public char varchar;
    public boolean isvar = false;
    public int currentvar;
    public boolean isDecimal;
    public boolean onfunc;

    public ArrayList<Character> vars= new ArrayList<>();

    public Expression(){
        expressions = new ArrayList<>();
        c = new ArrayList<>();
    }

    public Expression(char c){
        isvar = true;
        varchar = c;
    }

    public Expression(double a){
        isnum = true;
        num = a;
    }


    public Expression(ArrayList<Character>ct, ArrayList<Double>it){
        expressions = new ArrayList<>();
        for(Double d: it){
            expressions.add(new Expression(d));
        }
        c= ct;
    }

    public Expression(ArrayList<Character>ct, ArrayList<Double>it, char sep, char clo){
        for(Double d: it){
            expressions.add(new Expression(d));
        }
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
        if(this.isnum)
            return this.num;
        ArrayList<Double> endList = new ArrayList<>();
        for(Expression e:expressions){
            if(e.isnum){
                endList.add(e.num);
            }
            else{
                endList.add(e.getSolution());
            }

        }
        int ref = c.size();
        int ind;
        char symbol;
        while(ref>=1){
            ind = getInd();
            symbol = c.get(ind);
            endList.add(ind,evaluate(endList.get(ind),endList.get(ind+1),symbol));
            endList.remove(ind+1);
            endList.remove(ind+1);
            c.remove(ind);
            ref--;

        }

        solution = endList.get(0);
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

    public void parseExpression(String input){
        /*ArrayList<String> strings = new ArrayList<>();
        ArrayList<Character> interfuncs;
        String in = input;
        while (in.indexOf('(')>=0){
            strings.add(in.substring(in.indexOf('(')+1,in.indexOf(')')));
            in = in.substring(0,in.indexOf('('))+in.substring(in.indexOf(')')+1);
        }
        Expression temp = parseExpression(in,true);
        interfuncs = (ArrayList<Character>)temp.c.subList(temp.i.size(),temp.c.size()-1);
        temp.c = (ArrayList<Character>)temp.c.subList(0,temp.i.size());
        Expression.add(temp.getSolution());
        for(String s:strings){
            if(s.indexOf('(')<0){
                Expression.add(parseExpression(s,true).getSolution());
            }
            else{
                Expression.add(parseExpression(s,false).getSolution());
            }
        }
        Expression answer = new Expression(interfuncs,Expression);
        answer.vars = this.vars;
        return answer;*/

        //this is a line after which ill put my own code
        expressions = new ArrayList<>();
        while(input.length()!=0){
            if(input.charAt(0)=='('){
                Expression e = new Expression();
                e.parseExpression(input.substring(1,input.indexOf(')')));
                input = input.substring(input.indexOf(')')+1);
                expressions.add(e);
            }
            else{
                if (Character.isLowerCase(input.charAt(0))) {
                    expressions.add(new Expression(input.charAt(0)));
                    if(vars.indexOf(input.charAt(0))==-1){
                        vars.add(input.charAt(0));
                    }
                }
                else{
                    int a =0;
                    while(Character.isDigit(input.charAt(a))||input.charAt(a)=='.'&&a!=input.length()){
                        a++;
                    }
                    expressions.add(new Expression(Double.parseDouble(input.substring(0,a))));
                    if(!Character.isDigit(input.charAt(0))&&input.charAt(0)!='.'&&a!=input.length()-1){
                        input = input.substring(a);
                        this.c.add(input.charAt(0));
                        input= input.substring(1);
                    }
                    if(a==input.length()-1){
                        break;
                    }
                }



            }

        }
    }

    public void setvar(char var, double value, boolean set){
        for(Expression e:expressions){
            if(e.isvar&&e.isnum){
                if(varchar==var){
                    if(set){
                        e.num = value;
                    }
                    else{
                        if(isDecimal){
                            e.num = e.num+value/Math.pow(10,getDecimalPlaces(e.num)+1);
                        }
                        else{
                            e.num  = e.num*10+value;
                        }
                    }
                }
            }
            else if(e.isvar){
                e.isnum=true;
                e.num = value;
            }
            else{
                e.setvar(var,value,set);
            }

        }
    }

    /*public char getvar(double d){
        char ans = '0';
        for(int i = 0; i<varcodes.length;i++){
            if(varcodes[i]==d){
                return vars.get(i);
            }
        }
        return ans;
    }*/

    public String toString(){
        String stringshown = "";
        int ind;
        int funcind = 0;
        for(Expression e: this.expressions){
            if(e.isnum){
                if(e.hasSeparator){
                    stringshown+=e.separator;
                }
                ind = 0;
                if(e.isnum){
                    if(checkPiFactor(num)!=-1){
                        if(checkPiFactor(num)==1)
                            stringshown+="π";
                        else
                            stringshown+=checkPiFactor(num) + "π";
                    }
                    else if(getDecimalPlaces(num)==0){
                        stringshown+=(int)num;
                        if(isDecimal(num))
                            stringshown+=".0";}
                    else{
                        stringshown+=num+" ";}
                    if(ind<c.size()){
                        stringshown+=c.get(ind)+" ";
                        ind++;
                    }
                }
                else{
                    stringshown+=e.toString();
                }
                if(e.hasSeparator){
                    stringshown+=e.close;
                }
                if(funcind<c.size()){
                    stringshown+=c.get(funcind);
                }
                funcind++;
            }
            else if(e.isvar){
                stringshown+=e.varchar;
                if(funcind<c.size()){
                    stringshown+=c.get(funcind);
                }
                funcind++;
            }
            else{
                stringshown+=e.toString();
            }

        }

        return stringshown;
    }

    public LineGraphSeries<DataPoint> graphSolve(String input){
        Log.d("readInput()", "recieved input to solve: " + input);
        ArrayList<Expression> Expression = new ArrayList<>();
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

    public boolean isSpecialMultiplier(String str){
        switch (str){
            case "π": return true;
        }
        return false;
    }

    public int checkPiFactor(double d){
        if(d%Math.PI == 0){
            return (int)(d/Math.PI);
        }
        else
            return -1;
    }

    public int getDecimalPlaces(double d){
        String s = Double.toString(d);
        String[] result = s.split("\\.");
        if(Double.parseDouble(result[1])!=0)
            return result[1].length();
        else
            return 0;
    }

    public void enterfunction(char a){
        if(expressions.get(expressions.size()-1).isnum){
            if(!onfunc){
                c.add(a);
                onfunc = true;
            }
            else{
                c.set(c.size()-1,a);
                isDecimal=false;
            }
        }
        else{
            expressions.get(expressions.size()-1).enterfunction(a);
        }
    }

    public void enternumber(double d, boolean straight){
        if(vars.size()!=currentvar){
            setvar(vars.get(currentvar),d,false);
        }
    }

    public boolean isDecimal(double d){
        return d%1!=0;
    }





    /*public void solveAll(){
        //boolean done = false;
        //int ind = funcs.indexOf('(');
            /*while(!done&&ind<funcs.size()){
                if(ind==-1)
                    break;
                tempnums.add(nums.remove(ind));
                tempfuncs.add(funcs.remove(ind));
                if(funcs.get(ind)==')'){
                    Expression.add(new Expression(tempfuncs, tempnums));
                    ind = funcs.indexOf('(');
                    tempnums.add(nums.remove(ind));
                    funcs.remove(ind);
                }

            }
        if(Expression.size()>1) {
            Expression solved = new Expression(c, new ArrayList<Double>());
            for (Expression e : Expression) {
                if(e.c.size()>=e.i.size()){
                    c.add(e.c.get(e.c.size()-1));
                    e.c.remove(e.c.size()-1);
                }
                solved.i.add(e.getSolution());
            }
            Expression = new ArrayList<>();
            double solution = solved.getSolution();
            solved.c = new ArrayList<>();
            solved.i = new ArrayList<>();
            solved.i.add(solution);
            Expression.add(solved);
        }
        else{
            double solution = Expression.get(currentExpression).getSolution();
            Expression.get(currentExpression).c = new ArrayList<>();
            Expression.get(currentExpression).i = new ArrayList<>();
            Expression.get(currentExpression).i.add(solution);
        }
        currentExpression = 0;

        while(funcs.indexOf('(')!=-1){
            Expression.add(new Expression(new ArrayList<Character>(funcs.subList(funcs.indexOf('(')+1,funcs.indexOf(')'))),new ArrayList<Double>(nums.subList(funcs.indexOf('('),funcs.indexOf(')')+1))));
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
        Expression.add(answer);
        for(Expression e: Expression){
            calculate(e);
        }
        nums = new ArrayList<>();
        funcs = new ArrayList<>();
        nums.add(answer.getSolution());
        show(false);

    }*/
}
