package Adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.omar.probability.ProbabilityActivity;
import com.example.omar.probability.R;

import org.w3c.dom.Text;

import Data.NumberListManager;

/**
 * Created by Gus MG on 29/02/2016.
 */
public class NumberListAdapter extends RecyclerView.Adapter<NumberListAdapter.MyViewHolder> {

    NumberListManager manager = new NumberListManager();
    public static int n;

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

    public void deleteItem(int position){
        manager.remove(position);
        notifyItemRemoved(position);
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
            TextView delNumber = (TextView) numberCell.findViewById(R.id.delnumber);
            delNumber.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    deleteItem(getAdapterPosition());
                }
            });;
        }

        public void setNumberCell(Double d) {
            TextView textView = (TextView) numberCell.findViewById(R.id.numberTextView);
            textView.setText(d.toString());
            TextView ndata = (TextView) numberCell.findViewById(R.id.ndato);
            ndata.setText(String.valueOf(++n));
        }
    }
}
