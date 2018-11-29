package graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;

public class CustomAutoCompleteTextChangedListener implements TextWatcher {

    public static final String TAG = "CustomAutoCompleteTextChangedListener.java";
    Context context;

    public CustomAutoCompleteTextChangedListener(Context context){
        this.context = context;
    }

    @Override
    public void afterTextChanged(Editable s) {
        // TODO Auto-generated method stub

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTextChanged(CharSequence userInput, int start, int before, int count) {

        // if you want to see in the logcat what the user types
        Log.e("tag", "User input: " + userInput);

        WikiActivity wikiActivity = ((WikiActivity) context);

        // query the database based on the user input
        wikiActivity.item = wikiActivity.getItemsFromDb(userInput.toString());

        // update the adapater
        wikiActivity.myAdapter.notifyDataSetChanged();
        wikiActivity.myAdapter = new ArrayAdapter<String>(wikiActivity, android.R.layout.simple_dropdown_item_1line, wikiActivity.item);
        wikiActivity.myAutoComplete.setAdapter(wikiActivity.myAdapter);

    }
}
