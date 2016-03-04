package Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.omar.probability.R;

import Data.NumberListManager;

/**
 * Created by Gus MG on 29/02/2016.
 */
public class NumberListAdapter extends RecyclerView.Adapter<NumberListAdapter.MyViewHolder> {

    NumberListManager manager = new NumberListManager();

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.number_cell, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.setNumberCell(manager.get(position));
    }

    @Override
    public int getItemCount() {
        return manager.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder {

        private View numberCell;

        public MyViewHolder(View itemView) {
            super(itemView);
            numberCell = itemView;
        }

        public void setNumberCell(Double d) {
            TextView textView = (TextView) numberCell.findViewById(R.id.numberTextView);
            textView.setText(d.toString());
        }
    }
}
