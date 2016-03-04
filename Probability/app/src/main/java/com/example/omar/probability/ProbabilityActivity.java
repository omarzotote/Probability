package com.example.omar.probability;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Data.NumberListManager;

public class ProbabilityActivity extends AppCompatActivity {

    NumberListManager manager = new NumberListManager();
    ArrayList<Double> data = new ArrayList<>();
    LinearLayout linearLayout;
    double sumatoria;
    int n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_probability);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent i = getIntent();
        data = manager.get();

        //Ordenados
        sortData();
        TextView orderedTitle = new TextView(ProbabilityActivity.this);
        orderedTitle.setText("Ordenados\n");
        orderedTitle.setTextSize(20);
        orderedTitle.setGravity(Gravity.CENTER);
        TextView ordered = new TextView(ProbabilityActivity.this);
        for (Double n:data) {
            ordered.setText(ordered.getText() + String.valueOf(n) + "\n");
            sumatoria +=n;
        };
        //Media
        TextView avg = new TextView(ProbabilityActivity.this);
        avg.setText("Media: " + String.valueOf(getAverage()));
        //mediana
        TextView mid = new TextView(ProbabilityActivity.this);
        mid.setText("Mediana: " + String.valueOf(getMedian()));
        //moda
        TextView mode = new TextView(ProbabilityActivity.this);
        if(getMode()!=-100000.0) {
            mode.setText("Moda: " + String.valueOf(getMode()));
        }
        else
        {
            mode.setText("Moda: No hay moda");
        }
        //Range
        TextView range = new TextView(ProbabilityActivity.this);
        range.setText("Rango: " + String.valueOf(getRange()));
        //Variance
        TextView variance = new TextView(ProbabilityActivity.this);
        variance.setText("Varianza: " + String.valueOf(getVariance()));
        //Desviación estandar
        TextView de = new TextView(ProbabilityActivity.this);
        de.setText("Desviación estandar: " + String.valueOf(getStandardDeviation()));
        //Desviación media
        TextView dm = new TextView(ProbabilityActivity.this);
        dm.setText("Desviación media: " + String.valueOf(getMeanDeviation()));
        //Put TextView in layout
        linearLayout =(LinearLayout) findViewById(R.id.probabilitylayout);
        linearLayout.addView(ordered);
        linearLayout.addView(avg);
        linearLayout.addView(mid);
        linearLayout.addView(mode);
        linearLayout.addView(range);
        linearLayout.addView(variance);
        linearLayout.addView(de);
        linearLayout.addView(dm);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void sortData(){
        Collections.sort(data);
        n = data.size();
    }

    public double getAverage(){
        double average;
        average = sumatoria / n;
        return average;
    }

    public double getMedian(){
        double median,mid;
        mid = n/2.0;
        if(mid%1.0==0){
            median = data.get((int) mid);
            median+= data.get((int)mid+1);
            median/=2.0;
        }
        else{
            return data.get((int)mid);
        }
        return median;
    }

    public double getMode(){
        int frecuenciaTemporal=0,frecuenciaModa=0,i=0;
        double mode = -100000;
        double[] dataTemp = new double[n];
        for(Double d : data){
            dataTemp[i++] = d;
        }
        for(i=0;i<n-1;i++){
            if(dataTemp[i+1] == dataTemp[i]){
                frecuenciaTemporal++;
            }
            else{
                if(frecuenciaTemporal>frecuenciaModa){
                    frecuenciaModa=frecuenciaTemporal;
                    mode=dataTemp[i];
                    frecuenciaTemporal = 0;
                }
            }
        }
        return mode;
    }

    public Double getRange(){
        double range;
        range = data.get(n-1) - data.get(0);
        return range;
    }

    public Double getVariance(){
        double variance=0;
        for(Double d : data){
            variance += Math.pow(d-getAverage(), 2);
        }
        variance/=(n-1);
        return variance;
    }

    public Double getStandardDeviation(){
        double de;
        de=Math.sqrt(getMeanDeviation());
        return de;
    }

    public Double getMeanDeviation(){
        double dm=0;
        for(Double d : data){
            dm += Math.pow(d-getAverage(), 2);
        }
        dm/=n;
        return dm;
    }

}