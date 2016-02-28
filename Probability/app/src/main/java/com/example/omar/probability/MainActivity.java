package com.example.omar.probability;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<Double> data = new ArrayList<>();
    EditText number;
    LinearLayout linearLayout;
    ScrollView scrollView;
    LinearLayout layout2;
    Intent i;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Button add = (Button) findViewById(R.id.button);
        final Button calc = (Button) findViewById(R.id.button2);
        final Button clear = (Button) findViewById(R.id.button3);
        linearLayout = (LinearLayout) findViewById(R.id.linearlayout);
        layout2 = (LinearLayout) findViewById(R.id.layout2);
        number = (EditText) findViewById(R.id.editText);
        scrollView = (ScrollView) findViewById(R.id.scrollview);

        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!number.getText().toString().isEmpty()){
                    data.add(Double.parseDouble(number.getText().toString()));
                    TextView newNumber = new TextView(MainActivity.this);
                    newNumber.setText(number.getText().toString());
                    newNumber.setTextSize(20);
                    number.setText("");
                    layout2.addView(newNumber);
                }
            }
        });
        calc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                i = new Intent(context, ProbabilityActivity.class);
                i.putExtra("data",data);
                context.startActivity(i);
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });
    }

    public void addNumber(){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
