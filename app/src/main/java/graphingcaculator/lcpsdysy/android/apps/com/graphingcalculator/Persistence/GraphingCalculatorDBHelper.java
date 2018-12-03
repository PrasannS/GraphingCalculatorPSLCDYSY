package graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.Persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.CalcActivity;
import graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.FormulaListGenerator;
import graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.HomeActivity;
import graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.Models.Expression;
import graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.Models.Formula;

public class GraphingCalculatorDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "DB_GRAPHINGCALCULATOR";
    private static final int DATABASE_VERSION = 1;
    public static final String FORMULAE_TABLE_NAME = "TBL_FORMULAE";

    private static final String FORMULAE_TABLE_CREATE =
            "CREATE TABLE " + FORMULAE_TABLE_NAME + " (" +
                    "ID" + " TEXT primary key, " +
                    "FormulaName" + " TEXT, " +
                    "FormulaString" + " TEXT, " +
                    "FormulaInfo" + " TEXT);";

    GraphingCalculatorDBHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //create tables below
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(FORMULAE_TABLE_CREATE);
        try{
        loadallformulae(db);
        }
        catch (IOException e){
            Log.d("!@#$%^","filenotfound or already loaded",e);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        db.execSQL("DROP TABLE IF EXISTS " + FORMULAE_TABLE_NAME);
        onCreate(db);
    }

    public void addFormula(Formula m,SQLiteDatabase database){
        ContentValues values = new ContentValues();
        String id = java.util.UUID.randomUUID().toString();
        values.put("ID",id);
        values.put("FormulaName",m.name);
        values.put("FormulaString",m.equationString);
        values.put("FormulaInfo",m.info);
        long insertID = database.insert(GraphingCalculatorDBHelper.FORMULAE_TABLE_NAME, null, values);
    }

    public void loadallformulae(SQLiteDatabase database)throws IOException{
        HomeActivity c = new HomeActivity();
        try{
        ArrayList<Formula> fs = c.loadformulas();
            for(Formula formula:fs){
                addFormula(formula,database);
            }}
        catch(Exception e){
            Log.d("LOADERROR","problem with data loading");
        }

    }
}
