package graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.ArrayList;

import graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.Models.Formula;
import graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.Persistence.GraphingCalculatorDAO;

public class HomeActivity extends AppCompatActivity {

    private Button graphbutton;
    private Button settingsbutton;
    private Button calcbutton;
    private Button wikibutton;
    public static Context homecontext;
    private GraphingCalculatorDAO datasource=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        homecontext = getApplicationContext();

        datasource = new GraphingCalculatorDAO(this.getApplicationContext());
        datasource.open();
        graphbutton = (Button)findViewById(R.id.graphbutton);
        graphbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGraphActivity();
            }
        });

        calcbutton = (Button)findViewById(R.id.calcbutton);
        calcbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCalcActivity();
            }
        });

        settingsbutton = (Button)findViewById(R.id.settingsbutton);
        settingsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSettingsActivity();
            }
        });

        wikibutton = (Button)findViewById(R.id.wikibutton);
        wikibutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWikiActivity();
            }
        });
    }

    public void openGraphActivity(){
        Intent intent = new Intent(this,GraphActivity.class);
        startActivity(intent);
    }

    public void openSettingsActivity(){
        Intent intent1 = new Intent(this,SettingsActivity.class);
        startActivity(intent1);
    }
    public void openCalcActivity(){
        Intent intent1 = new Intent(this,CalcActivity.class);
        startActivity(intent1);
    }
    public void openWikiActivity(){
        Intent intent1 = new Intent(this,WikiActivity.class);
        startActivity(intent1);
    }
    public ArrayList<Formula> loadformulas() throws IOException {
        FormulaListGenerator f = new FormulaListGenerator();
        ArrayList<Formula>fs = new ArrayList<>();
        try {
            fs.addAll(f.loadformulae("MathInfo", 0, homecontext));
            fs.addAll(f.loadformulae("ChemInfo", 1, homecontext));
            fs.addAll(f.loadformulae("PhysicsInfo", 2, homecontext));
        }
        catch (Exception e){
            Log.d("Loading issue", "Data unable to parse from textfiles",e);
        }
        return fs;
    }
}
