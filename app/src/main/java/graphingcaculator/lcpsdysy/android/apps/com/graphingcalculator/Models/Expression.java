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
    public String separator ;
    public String close;
    public boolean hasSeparator = false;
    public boolean isnum = false;
    public double num;
    public char varchar;
    public boolean isvar = false;
    public int currentvar;
    public boolean isDecimal;
    public boolean onfunc;
    public boolean closed = false;
    public boolean radians = true;


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

    public Expression(ArrayList<Character>ct, ArrayList<Double>it, String sep, String clo){
        expressions = new ArrayList<>();
        for(Double d: it){
            expressions.add(new Expression(d));
        }
        c= ct;
        onfunc=false;
        separator = sep;
        hasSeparator = true;
        close = clo;
        isnum = false;
    }

    public double separatorProperties(double sep){
        if(separator.equals("√")){
            return Math.sqrt(sep);
        }
        if(separator.equals("sin")){
            return toDegrees(Math.sin(sep));
        }
        if(separator.equals("csc")){
            return toDegrees(1/Math.sin(sep));
        }
        if(separator.equals("sec")){
            return toDegrees(1/Math.cos(sep));
        }
        if(separator.equals("cot")){
            return toDegrees(1/Math.tan(sep));
        }
        if(separator.equals("tan")){
            return toDegrees(Math.tan(sep));
        }
        if(separator.equals("cos")){
            return toDegrees(Math.cos(sep));
        }
        if(separator.equals("asin")){
            return toDegrees(Math.asin(sep));
        }
        if(separator.equals("acsc")){
            return toDegrees(1/Math.asin(sep));
        }
        if(separator.equals("asec")){
            return toDegrees(1/Math.acos(sep));
        }
        if(separator.equals("acot")){
            return toDegrees(1/Math.atan(sep));
        }
        if(separator.equals("atan")){
            return toDegrees(Math.atan(sep));
        }
        if(separator.equals("acos")){
            return toDegrees(Math.acos(sep));
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
        if(hasSeparator)
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
            int a =0;
            if(input.charAt(0)=='('){
                Expression e = new Expression();
                e.parseExpression(input.substring(1,getclose(input)));
                input = input.substring(getclose(input)+1);
                expressions.add(e);
            }
            else{
                if (Character.isLowerCase(input.charAt(0))) {
                    expressions.add(new Expression(input.charAt(0)));
                    if(vars.indexOf(input.charAt(0))==-1){
                        vars.add(input.charAt(0));
                        if(input.length()>1)
                            input = input.substring(1);
                        else{
                            input= "";
                            break;
                        }
                    }
                }
                else{

                    while(input.charAt(a)=='-'||Character.isDigit(input.charAt(a))||input.charAt(a)=='.'&&a!=input.length()){
                        if(input.charAt(a)=='-'){
                            if(a==0)
                            a++;
                            else if(Character.isDigit(input.charAt(a-1))){
                                break;
                            }
                        }
                        else{
                            a++;
                        if(a==input.length()){
                            break;
                        }}
                    }
                    expressions.add(new Expression(Double.parseDouble(input.substring(0,a))));
                    if(a!=input.length()){
                        input = input.substring(a);
                    }
                    else{
                        input = "";
                        break;
                    }

                }
                if(!Character.isDigit(input.charAt(0))&&input.charAt(0)!='.'&&0!=input.length()){
                    this.c.add(input.charAt(0));
                    input = input.substring(1);
                }



            }

        }
    }

    public void setvar(char var, double value, boolean set){
        for(Expression e:expressions){
            if(e.isvar&&e.isnum){
                if(e.varchar==var){
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
                if(e.varchar==var){
                    e.isnum=true;
                    e.num = value;
                }
            }
            else if(!e.isnum){
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
        if(isvar){
            stringshown=""+varchar;
        }
        else{
            if(this.hasSeparator){
                stringshown+=this.separator;
            }
            int funcind = 0;
            if(isnum){
                stringshown=""+num;
            }
            else{
                for(Expression e: this.expressions){
                    if(e.isnum){

                        if(e.isnum){
                            if(checkPiFactor(e.num)!=-1){
                                if(checkPiFactor(e.num)==1)
                                    stringshown+="π";
                                else
                                    stringshown+=checkPiFactor(e.num) + "π";
                            }
                            else if(getDecimalPlaces(e.num)==0){
                                stringshown+=""+(int)e.num;
                                if(isDecimal(e.num))
                                    stringshown+=".0";}
                            else{
                                stringshown+=e.num+" ";}
                            if(funcind<c.size()){
                                stringshown+=c.get(funcind)+" ";
                                funcind++;
                            }
                        }
                        else{
                            stringshown+=e.toString();
                            if(funcind<c.size()){
                                stringshown+=c.get(funcind)+" ";
                                funcind++;
                            }
                        }


                    }
                    else if(e.isvar){
                        stringshown+=e.varchar;
                        if(funcind<c.size()){
                            stringshown+=c.get(funcind);
                            funcind++;
                        }
                    }
                    else{
                        stringshown+=e.toString();
                        if(funcind<c.size()){
                            stringshown+=c.get(funcind)+" ";
                            funcind++;
                        }
                    }

                }
            }
            if(this.hasSeparator){
                stringshown+=this.close;
            }
        }

        return stringshown;
    }

    public LineGraphSeries<DataPoint> graphSolve(String input, double start, double end, double increment){
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
            for (double i = start; i < end; i += increment)
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
        if(d%Math.PI == 0&&d!=0){
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


    public boolean enternumber(double d, boolean straight){
        isnum=false;
        boolean b;
        if(vars.size()!=currentvar){
            this.setvar(vars.get(currentvar),d,false);
            return true;
        }
        else{
            if (expressions.get(expressions.size() - 1).isnum) {
                if(straight){
                    expressions.get(expressions.size()-1).num = d;
                    return true;
                }
                if(onfunc){
                    addExpression(new Expression(d));
                    onfunc=false;
                    return true;
                }
                else{
                    if(expressions.get(expressions.size()-1).isnum){
                        double temp = expressions.get(expressions.size()-1).num;
                /*if(firstform){
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
                    e.expressions.get(e.currentExpression).i.set(currentnum, e.expressions.get(e.currentExpression).i.get(currentnum)*10+i);*/
                        if(isDecimal){
                            expressions.set(expressions.size()-1,new Expression(temp+d/Math.pow(10,getDecimalPlaces(temp)+1)));
                            return true;
                        }
                        else{
                            expressions.set(expressions.size()-1,new Expression(temp*10+d));
                            return true;
                        }
                    }
                    else{
                        expressions.get(expressions.size()-1).enternumber(d,false);
                        return true;
                    }
                }
            }
            else{
                if(onfunc){
                    addExpression(new Expression(d));
                    onfunc=false;
                    return true;
                }
                else if(straight){
                    expressions.get(expressions.size() - 1).enternumber(d,true);
                    return true;
                }
                else{
                    expressions.get(expressions.size() - 1).enternumber(d,false);
                    return true;
                }
            }
        }
    }

    public void addsepfunc(Expression a){
        if(expressions.get(expressions.size()-1).isnum||expressions.get(expressions.size()-1).closed){
            addExpression(a);
            this.onfunc = false;
        }
        else{
            expressions.get(expressions.size()-1).addsepfunc(a);
        }
    }

    public void enterfunction(char a){
        if(expressions.get(expressions.size()-1).isnum||expressions.get(expressions.size()-1).closed){
            if(!onfunc){
                this.c.add(a);
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

    public boolean isDecimal(double d){
        return d%1!=0;
    }

    public int getclose(String s){
        int ind = 1;
        int pnum = 1 ;
        while(pnum!=0){
            if(s.charAt(ind)=='(')
                pnum++;
            else if(s.charAt(ind)==')'){
                pnum--;
            }
            ind++;
        }
        return ind;

    }

    public void addExpression(Expression e){
        if(expressions.get(expressions.size()-1).isnum||expressions.get(expressions.size()-1).closed){
            expressions.add(e);
        }
        else{
            expressions.get(expressions.size()-1).addExpression(e);
        }
    }

    public void closeExpression(){
        if(expressions.get(expressions.size()-1).isnum||expressions.get(expressions.size()-1).isvar){
            this.closed=true;
        }
        else{
            expressions.get(expressions.size()-1).closeExpression();
        }
        onfunc=false;
    }

    public void changeradians(){
        if(expressions.get(expressions.size()-1).isnum||expressions.get(expressions.size()-1).closed){
            this.radians = !this.radians;
            this.onfunc = false;
        }
        else{
            expressions.get(expressions.size()-1).changeradians();
        }
    }

    public double toDegrees(double d){
        if(radians){
            return d;
        }
        else{
            return d * (180 / Math.PI);
        }
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


