package graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.Persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.Models.Expression;
import graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.Models.Formula;

public class GraphingCalculatorDAO {

    private SQLiteDatabase database;
    private GraphingCalculatorDBHelper dbHelper;

    private String[] allColumns = {
            "ID",
            "FormulaName",
            "FormulaString",
            "FormulaInfo"
    };

    public GraphingCalculatorDAO(Context context){
        try{
        dbHelper = new GraphingCalculatorDBHelper(context);}
        catch (Exception e){
            Log.d("DBLoadError","DBHelper Constructor not working",e);
        }
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

    public void addFormula(Expression m, String name){

        ContentValues values = new ContentValues();
        String id = java.util.UUID.randomUUID().toString();
        values.put("ID",id);
        values.put("FormulaName",name);
        values.put("FormulaString",m.toString());
        values.put("FormulaInfo",getInfo(name));
        long insertID = database.insert(GraphingCalculatorDBHelper.FORMULAE_TABLE_NAME, null, values);
    }

    public Formula getFormula(String name){
        Cursor cursor = database.query(GraphingCalculatorDBHelper.FORMULAE_TABLE_NAME, allColumns, "FormulaName" + " = " + "\""+ name+"\"", null, null, null, null );
        cursor.moveToFirst();
        Formula newE = cursorToFormula(cursor);
        cursor.close();
        return newE;
    }

    private Formula cursorToFormula(Cursor cursor) {
        Formula m = new Formula();
        m.info = cursor.getString(cursor.getColumnIndex("FormulaInfo"));
        m.name= cursor.getString(cursor.getColumnIndex("FormulaName"));
        m.equationString = cursor.getString(cursor.getColumnIndex("FormulaString"));

        return m;
    }

    // check if a record exists so it won't insert the next time you run this code
    public boolean checkIfExists(String objectName){

        boolean recordExists = false;

        database = dbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT " + allColumns[0] + " FROM " + dbHelper.FORMULAE_TABLE_NAME + " WHERE " + allColumns[1] + " = '" + objectName + "'", null);

        if(cursor!=null) {

            if(cursor.getCount()>0) {
                recordExists = true;
            }
        }

        cursor.close();
        database.close();

        return recordExists;
    }

    // Read records related to the search term
    public List<Formula> read(String searchTerm) {

        List<Formula> recordsList = new ArrayList<Formula>();

        // select query
        String sql = "";
        sql += "SELECT * FROM " + dbHelper.FORMULAE_TABLE_NAME;
        sql += " WHERE " + allColumns[0] + " LIKE '%" + searchTerm + "%'";
        sql += " ORDER BY " + allColumns[1] + " DESC";
        sql += " LIMIT 0,5";

        database = dbHelper.getWritableDatabase();

        // execute the query
        Cursor cursor = database.rawQuery(sql, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                // int productId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(fieldProductId)));
                String objectName = cursor.getString(cursor.getColumnIndex(allColumns[1]));
                Formula myObject = new Formula(objectName);

                // add to list
                recordsList.add(myObject);

            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        // return the list of records
        return recordsList;
    }

}
