package graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;

import graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.Models.Formula;
import graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.Models.FormulaName;
import graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.Persistence.GraphingCalculatorDAO;
import graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.CustomAutoCompleteView;

public class WikiActivity extends Activity {

    public String cur;
    public Formula curF;
    public TextView wikiframe;

    GraphingCalculatorDAO datasource = null;
    CustomAutoCompleteView myAutoComplete;

    // adapter for auto-complete
    ArrayAdapter<String> myAdapter;

    // for database operations
    DatabaseHandler databaseH;

    // just to add some initial value
    String[] item = new String[] {"Please search..."};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wiki);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        datasource = new GraphingCalculatorDAO(this.getApplicationContext());
        datasource.open();

        wikiframe = (TextView) findViewById(R.id.wikiframe);


        try{

            // instantiate database handler
            databaseH = new DatabaseHandler(WikiActivity.this);

            // put sample data to database
            insertSampleData();

            // autocompletetextview is in activity_main.xml
            myAutoComplete = (CustomAutoCompleteView) findViewById(R.id.myautocomplete);

            // add the listener so it will tries to suggest while the user types
            myAutoComplete.addTextChangedListener(new CustomAutoCompleteTextChangedListener(this));

            // set our adapter
            myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, item);
            myAutoComplete.setAdapter(myAdapter);

            myAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    myAutoComplete.performCompletion();
                    cur = myAutoComplete.getText().toString();
                    curF = datasource.getFormula(cur);
                    wikiframe.setText(curF.toString());

                }


            });

        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertSampleData(){

        for(String s:FormulaListGenerator.chemFormulae){
            if(!databaseH.create( new FormulaName(s)))break;
        }
        for(String s:FormulaListGenerator.mathFormulae){
            if(!databaseH.create( new FormulaName(s)))break;
        }
        for(String s:FormulaListGenerator.physicsFormulae){
            if(!databaseH.create( new FormulaName(s)))break;
        }

    }

    // this function is used in CustomAutoCompleteTextChangedListener.java
    public String[] getItemsFromDb(String searchTerm){

        // add items on the array dynamically
        List<FormulaName> products = databaseH.read(searchTerm);
        int rowCount = products.size();

        String[] item = new String[rowCount];
        int x = 0;

        for (FormulaName record : products) {

            item[x] = record.objectName;
            x++;
        }

        return item;
    }
}
