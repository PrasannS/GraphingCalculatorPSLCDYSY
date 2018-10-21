package graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.Models;

import java.util.ArrayList;

public class Expression {
    private double solution;
    public char[] bedmas = {'^','/','*','+','-'};
    private ArrayList<Double>i ;
    ArrayList<Character>c ;
    public Expression(ArrayList<Character>ct,ArrayList<Double>it){
        i = it;
        c= ct;
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
    }

    public double getSolution(){
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
}
