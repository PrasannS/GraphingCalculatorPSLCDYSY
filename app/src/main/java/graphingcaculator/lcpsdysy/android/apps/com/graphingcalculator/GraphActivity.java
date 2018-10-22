 package graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.w3c.dom.Text;

import java.util.StringTokenizer;

 public class GraphActivity extends AppCompatActivity {
    private ImageButton home;
    private ImageButton settings;
    private ImageButton origin;
    private GraphView graph;
    private EditText input;
    private Button updateInput;
    private String currentInput;

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
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6),
                new DataPoint(100, 200)
        });
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
        graph.addSeries(series);
        graph.addSeries(series2);
        graph.addSeries(series3);

        input = (EditText) findViewById(R.id.graphInput);
        updateInput = (Button) findViewById(R.id.click);
        updateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readInput();
            }
        });
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
         currentInput = input.getText().toString();
         int x = 0;
         if (currentInput.charAt(0) == '(')
         {
             StringBuilder int1 = new StringBuilder();
             for (int i = 1; i < currentInput.length() && currentInput.charAt(i) != ','; i++)
                 int1.append(currentInput.charAt(i));
             x = Integer.parseInt(int1.toString());
             StringBuilder inty = new StringBuilder();
             for (int i = 1; i < currentInput.length() && currentInput.charAt(i) != ')'; i++)
                 int1.append(currentInput.charAt(i));
             x = Integer.parseInt(int1.toString());
         }
     }
}
