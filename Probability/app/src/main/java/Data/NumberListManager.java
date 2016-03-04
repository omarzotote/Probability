package Data;

import java.util.ArrayList;

/**
 * Created by Gus MG on 29/02/2016.
 */
public class NumberListManager{
    private static ArrayList<Double> data = new ArrayList<>();

    public void add(Double element) {
        data.add(0, element);
    }

    public Double get(int index) {
        return data.get(index);
    }

    public ArrayList<Double> get() {
        return (ArrayList<Double>) data.clone();
    }

    public int size() {
        return data.size();
    }

    public void remove(int index) {
        data.remove(index);
    }

    public void clearAll(){
        data.clear();
    }
}
