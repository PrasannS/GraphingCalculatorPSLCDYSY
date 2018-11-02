package graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.Models;

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

    public double graphSolve(String input){
        ArrayList<Expression> expressions = new ArrayList<>();
        ArrayList<Double> nums = new ArrayList<Double>();
        ArrayList<Character> operators = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> inds = new ArrayList<>();
        for (int i = 0; i < input.length(); i++)
        {
            char cur = input.charAt(i);
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
}
