 package graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.util.Log;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

import graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.Models.Expression;

 public class GraphActivity extends AppCompatActivity {
    private ImageButton home;
    private ImageButton settings;
    private ImageButton origin;
    private GraphView graph;
    private EditText inputX;
    private EditText inputY;
    private EditText inputFunc;
    private Button updateInput;
    private LineGraphSeries<DataPoint> series;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        home = (ImageButton) findViewById(R.id.graphHomeButton);
        settings = (ImageButton) findViewById(R.id.graphSettingsButton);
        origin = (ImageButton) findViewById(R.id.graphOriginButton);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomeActivity();
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettingsActivity();
            }
        });
        origin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToOrigin();
            }
        });
        graph = (GraphView) findViewById(R.id.graph);
        series = new LineGraphSeries<DataPoint>();

        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(-1000, -1000),
        });
        LineGraphSeries<DataPoint> series3 = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(1000, 1000),
        });
        graph.getViewport().setMinY(-5);
        graph.getViewport().setMinX(-5);
        graph.getViewport().setMaxX(5);
        graph.getViewport().setMaxY(5);
        graph.getViewport().setMaxXAxisSize(10000);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setScalable(true);
        graph.getViewport().setScalableY(true);
        graph.addSeries(series2);
        graph.addSeries(series3);

        inputX = (EditText) findViewById(R.id.graphInputX);
        inputY = (EditText) findViewById(R.id.graphInputY);
        updateInput = (Button) findViewById(R.id.click);
        updateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readInput();
            }
        });
        inputFunc = (EditText) findViewById(R.id.enterFunc);
    }


    public void openHomeActivity()
    {
        Intent intent1 = new Intent(this, HomeActivity.class);
        startActivity(intent1);
    }
    public void openSettingsActivity()
    {
        Intent intent1 = new Intent(this, SettingsActivity.class);
        startActivity(intent1);
    }
    public void goToOrigin()
    {
        graph.getViewport().setMinY(-5);
        graph.getViewport().setMaxY(5);
        graph.getViewport().setMinX(-5);
        graph.getViewport().setMaxX(5);

        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setXAxisBoundsManual(true);
    }
    public void readInput()
    {
        try
        {
            if (!inputFunc.getText().toString().equals(""))
            {
                Expression exp = new Expression(new ArrayList<Character>(), new ArrayList<Double>());
                Log.d("readInput()", "reading Input, equation is " + inputFunc.getText().toString());
                graph.addSeries(exp.graphSolve(inputFunc.getText().toString()));
            }
            else
            {
                graph.addSeries(series);
                if (inputX.getText().toString().equals("") && inputY.getText().toString().equals(""))
                {
                    testGraphing();
                }
                else
                {
                    double x = Double.parseDouble(inputX.getText().toString());
                    double y = Double.parseDouble(inputY.getText().toString());
                    inputX.getText().clear();
                    inputY.getText().clear();
                    series.appendData(new DataPoint(x, y), false, 10000);
                }
            }
        }
        catch (Exception e)
        {
            Log.e("readInput()", "something went wrong in reading input");
        }
    }
    public void testGraphing()
     {
         try
         {
             for (double i = -10; i < 100; i += 0.1)
             {
                 series.appendData(new DataPoint(i, Math.sin(i)),false,10000);
                 Log.d("testGraphing()", "RUNNING RUNNING RUNNING");
             }

         }
         catch (Exception e)
         {
             Log.e("testGraphing()", "something went wrong while graphing");
         }
     }
}
