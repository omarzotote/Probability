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

import java.lang.reflect.Array;
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
    double sumatoria, average;
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
        }
        //Media
        TextView avg = new TextView(ProbabilityActivity.this);
        avg.setText("Media: " + String.valueOf(getAverage()));
        //mediana
        TextView mid = new TextView(ProbabilityActivity.this);
        mid.setText("Mediana: " + String.valueOf(getMedian()));
        //moda
        TextView mode = new TextView(ProbabilityActivity.this);
        StringBuilder modeString = new StringBuilder();
        modeString.append("Moda:");
        ArrayList<Double> modeList = getMode();
        for(Double number : modeList) {
            modeString.append("\n\t" + String.valueOf(number));
        }
        mode.setText(modeString.toString());
        //Range
        TextView range = new TextView(ProbabilityActivity.this);
        range.setText("Rango: " + String.valueOf(getRange()));
        //Variance
        TextView variance = new TextView(ProbabilityActivity.this);
        variance.setText("Varianza poblacional: " + String.valueOf(getVariance()));
        //Sample Variance
        TextView sample_variance = new TextView(ProbabilityActivity.this);
        if(n > 1) {
            sample_variance.setText("Varianza muestral: " + String.valueOf(getSampleVariance()));
        }
        //Desviación estandar
        TextView de = new TextView(ProbabilityActivity.this);
        de.setText("Desviación estandar poblacional: " + String.valueOf(getStandardDeviation()));
        //Sample Desviación estandar
        TextView sample_de = new TextView(ProbabilityActivity.this);
        if(n > 1) {
            sample_de.setText("Desviación estandar muestral: " + String.valueOf(getSampleStandardDeviation()));
        }
        //Desviación media
        TextView dm = new TextView(ProbabilityActivity.this);
        dm.setText("Desviación media: " + String.valueOf(getMeanDeviation()));
        //Put TextView in layout
        linearLayout =(LinearLayout) findViewById(R.id.probabilitylayout);
        linearLayout.addView(orderedTitle);
        linearLayout.addView(ordered);
        linearLayout.addView(avg);
        linearLayout.addView(mid);
        linearLayout.addView(mode);
        linearLayout.addView(range);
        linearLayout.addView(dm);
        linearLayout.addView(variance);
        if(n > 1) {
            linearLayout.addView(sample_variance);
        }
        linearLayout.addView(de);
        if(n > 1) {
            linearLayout.addView(sample_de);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void sortData(){
        Collections.sort(data);
        n = data.size();
    }

    public double getAverage(){
        average = sumatoria / n;
        return average;
    }

    public double getMedian(){
        double median = data.get(n / 2);
        if(n % 2 == 0){
            median+= data.get(n / 2 - 1);
            median/=2.0;
        }
        return median;
    }

    public ArrayList<Double> getMode(){
        int[] freq = new int[n];
        ArrayList<Double> mode = new ArrayList<>();
        int modeFreq = 1;
        freq[0] = 1;
        for(int i = 1; i < n; i++){
            if(Double.compare(data.get(i-1),data.get(i)) == 0){
                freq[i] = freq[i - 1] + 1;
            }
            else{
                freq[i] = 1;
            }
            if(modeFreq < freq[i])
                modeFreq = freq[i];
        }
        for(int i = 0; i < n; i++) {
            if(freq[i] == modeFreq)
                mode.add(data.get(i));
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
            variance += Math.pow(d-average, 2);
        }
        variance/=n;
        return variance;
    }

    public Double getSampleVariance(){
        double variance=0;
        for(Double d : data){
            variance += Math.pow(d-average, 2);
        }
        variance/=(n-1);
        return variance;
    }

    public Double getStandardDeviation(){
        double de;
        de=Math.sqrt(getVariance());
        return de;
    }

    public Double getSampleStandardDeviation(){
        double de;
        de=Math.sqrt(getSampleVariance());
        return de;
    }

    public Double getMeanDeviation(){
        double dm=0;
        for(Double d : data){
            dm += Math.abs(d-average);
        }
        dm/=n;
        return dm;
    }

}