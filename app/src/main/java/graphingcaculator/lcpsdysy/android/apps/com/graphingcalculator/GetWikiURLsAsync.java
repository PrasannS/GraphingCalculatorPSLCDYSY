package graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.Models.Formula;
import graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.SearchResultModel;
import graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.WikiSearchTermToFullUrlSvc;

public class GetWikiURLsAsync extends AsyncTask<Void, Void, ArrayList<Formula>> {

    //This string list will store all of the names of all the formulae stored on the app
    String[] allFormulae = {

    };

    public ArrayList<Formula> formulas = new ArrayList<>();



    @Override
    protected ArrayList<Formula> doInBackground(Void... params) {
        for (String s: allFormulae
                ) {
            SearchResultModel srm = WikiSearchTermToFullUrlSvc.getSearchResultsSummary(s);
            SearchResultModel srm2 = WikiSearchTermToFullUrlSvc.getURLPartFromPageId(srm);
            if (srm2!=null && srm2.URLPart!=null && srm2.URLPart.trim().length()>0){
                Formula m = WikiSearchTermToFullUrlSvc.getFormula(srm2);
                formulas.add(m);
                //Log.d("MonumentModel",srm2.URLPart);
            }

        }
        return formulas;
    }

    @Override
    protected void onPostExecute(final ArrayList<Formula> mms) {
        int duration = Toast.LENGTH_LONG;
        //Toast toast = Toast.makeText(this.context, "Done", duration);
        //toast.show();
        for (Formula s: mms
                ){
            String  outputCSVLine = "%#%"+s.name+"%#%"+s.info+"%#%"+s.equationString;
            Log.d("Formula",outputCSVLine);
        }
    }

}
