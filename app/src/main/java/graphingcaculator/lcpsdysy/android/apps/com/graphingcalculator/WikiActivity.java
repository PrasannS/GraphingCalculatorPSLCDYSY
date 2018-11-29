package graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.List;

import graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.Models.Formula;
import graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.Models.FormulaName;
import graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.Persistence.GraphingCalculatorDAO;
import graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.CustomAutoCompleteView;

public class WikiActivity extends AppCompatActivity {
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
        datasource = new GraphingCalculatorDAO(this.getApplicationContext());
        datasource.open();

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

        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertSampleData(){

        for(String s:FormulaListGenerator.chemFormulae){
            databaseH.create( new FormulaName(s));
        }
        for(String s:FormulaListGenerator.mathFormulae){
            databaseH.create( new FormulaName(s));
        }
        for(String s:FormulaListGenerator.physicsFormulae){
            databaseH.create( new FormulaName(s));
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
