package graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.Models;

import java.util.ArrayList;

public class Expressions {

    public ArrayList<Expression>expressions;
    public ArrayList<Character> interFuncs = new ArrayList<>();
    public int currentExpression=0;
    public String info;
    public String name;

    public Expressions(ArrayList<Expression>e){
        expressions = e;
    }

    public int getDecimalPlaces(double d){
        String s = Double.toString(d);
        String[] result = s.split("\\.");
        if(Double.parseDouble(result[1])!=0)
            return result[1].length();
        else
            return 0;
    }

    public boolean isDecimal(double d){
        return d%1!=0;
    }

    public String toString(){
        String stringshown = "";
        int ind;
        int funcind = 0;
        for(Expression e: this.expressions){
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
                    if(isDecimal(c))
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

        return stringshown;
    }

    public Expressions parseExpressions(String s){
        Expressions a = new Expressions(new ArrayList<Expression>());
        a.expressions.add(new Expression().parseExpression(s,false));
        return a;
        //YOU NEED TO CREATE A TREELIKE OBJECT
    }

    public void solveAll(){
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
        currentExpression = 0;

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
        show(false);
        */
    }

}
