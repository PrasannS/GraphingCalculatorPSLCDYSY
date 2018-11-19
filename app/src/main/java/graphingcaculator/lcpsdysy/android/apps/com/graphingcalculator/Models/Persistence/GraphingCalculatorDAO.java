package graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.Models.Persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.Models.Expression;

public class GraphingCalculatorDAO {

    private SQLiteDatabase database;
    private GraphingCalculatorDBHelper dbHelper;

    private String[] allColumns = {
            "ID",
            "EquationName",
            "EquationString",
            "EquationInfo"
    };

    private String[] allPlayColumns = {
            "ID",
            "FormulaName",
            "FormulaString",
            "FormulaInfo"
    };

    public GraphingCalculatorDAO(Context context) {
        dbHelper = new GraphingCalculatorDBHelper(context);
    }

    public void open() throws SQLException {
        database=dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public String getInfo(String keyword){
        return "Prasann is an amazing person.";
    }

    public void addEquation(Expression m, String name){

        ContentValues values = new ContentValues();
        String id = java.util.UUID.randomUUID().toString();
        values.put("ID",id);
        values.put("EquationName",name);
        values.put("EquationString",m.toString());
        values.put("EquationInfo",getInfo(name));
        long insertID = database.insert(GraphingCalculatorDBHelper.EQUATIONS_TABLE_NAME, null, values);


    }

    public void addFormula(Expression m, String name){

        ContentValues values = new ContentValues();
        String id = java.util.UUID.randomUUID().toString();
        values.put("ID",id);
        values.put("EquationName",name);
        values.put("EquationString",m.toString());
        values.put("EquationInfo",getInfo(name));
        long insertID = database.insert(GraphingCalculatorDBHelper.FORMULAE_TABLE_NAME, null, values);
    }

    public Expression getFormula(String name){
        Cursor cursor = database.query(GraphingCalculatorDBHelper.FORMULAE_TABLE_NAME, allColumns, "FormulaName" + " = " + "\""+ name+"\"", null, null, null, null );
        cursor.moveToFirst();
        Expression newE = cursorToFormula(cursor);
        cursor.close();
        return newE;
    }

    private Expression cursorToFormula(Cursor cursor) {
        Expression m = new Expression();
        m.info = cursor.getString(cursor.getColumnIndex("FormulaInfo"));
        m.name= cursor.getString(cursor.getColumnIndex("FormulaName"));
        m.parseExpression(cursor.getString(cursor.getColumnIndex("FormulaString")));

        return m;
    }

    public Expression getEquation(String name){
        Cursor cursor = database.query(GraphingCalculatorDBHelper.EQUATIONS_TABLE_NAME, allColumns, "FormulaName" + " = " + "\""+ name+"\"", null, null, null, null );
        cursor.moveToFirst();
        Expression newE = cursorToEquation(cursor);
        cursor.close();
        return newE;
    }

    private Expression cursorToEquation(Cursor cursor) {
        Expression m = new Expression();
        m.info = cursor.getString(cursor.getColumnIndex("EquationInfo"));
        m.name= cursor.getString(cursor.getColumnIndex("EquationName"));
        m.parseExpression(cursor.getString(cursor.getColumnIndex("EquationString")));

        return m;
    }
}
