package com.example.omar.probability;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import Adapters.NumberListAdapter;
import Data.NumberListManager;

public class MainActivity extends AppCompatActivity {

    EditText number;
    LinearLayout linearLayout;
    Intent i;
    Context context = this;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter = new NumberListAdapter();
    NumberListManager numberListManager = new NumberListManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        final Button add = (Button) findViewById(R.id.button);
        final Button calc = (Button) findViewById(R.id.button2);
        final Button clear = (Button) findViewById(R.id.button3);
        number = (EditText) findViewById(R.id.editText);

        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!number.getText().toString().isEmpty()){
                    numberListManager.add(Double.parseDouble(number.getText().toString()));
                    adapter.notifyItemInserted(0);
                    number.setText("");
                }
            }
        });
        calc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(numberListManager.size()>0) {
                    i = new Intent(context, ProbabilityActivity.class);
                    context.startActivity(i);
                }
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Borrar datos")
                        .setMessage("¿Eliminar todos los datos?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                numberListManager.clearAll();
                                adapter.notifyDataSetChanged();
                                NumberListAdapter.n = 0;
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
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
