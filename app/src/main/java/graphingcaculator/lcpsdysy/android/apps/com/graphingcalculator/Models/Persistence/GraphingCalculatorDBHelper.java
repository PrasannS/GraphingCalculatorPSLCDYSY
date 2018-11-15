package graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.Models.Persistence;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.GraphActivity;

public class GraphingCalculatorDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "DB_GRAPHINGCALCULATOR";
    private static final int DATABASE_VERSION = 0;
    public static final String EQUATIONS_TABLE_NAME = "TBL_EQUATIONS";
    public static final String FORMULAE_TABLE_NAME = "TBL_FORMULAE";

    private static final String EQUATIONS_TABLE_CREATE =
            "CREATE TABLE " + EQUATIONS_TABLE_NAME + " (" +
                    "ID" + " TEXT primary key, " +
                    "EquationName" + " TEXT, " +
                    "EquationString" + " TEXT, " +
                    "EquationInfo" + " TEXT);";

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
        db.execSQL(EQUATIONS_TABLE_CREATE);
        db.execSQL(FORMULAE_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        db.execSQL("DROP TABLE IF EXISTS " + EQUATIONS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + FORMULAE_TABLE_NAME);
        onCreate(db);
    }
}
