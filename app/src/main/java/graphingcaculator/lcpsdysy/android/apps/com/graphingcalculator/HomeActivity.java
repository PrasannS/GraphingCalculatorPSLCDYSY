package graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    private Button graphbutton;
    private Button settingsbutton;
    private Button calcbutton;
    private Button wikibutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
}
