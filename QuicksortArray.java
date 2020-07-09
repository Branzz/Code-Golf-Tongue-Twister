import java.util.ArrayList;

public class QuicksortArray<T extends Comparable<T>> {

    private ArrayList<T> list;
    private int number;

    public void sort(ArrayList<T> values) {
        // check for empty or null array
        if (values == null || values.size() == 0)
            return;
        this.list = values;
        number = values.size();
        quicksort(0, number - 1);
    }

    private void quicksort(int low, int high) {
        int i = low, j = high;
        T pivot = list.get(low + (high-low)/2);
        while (i <= j) {
            while (list.get(i).compareTo(pivot) < 0)
                i++;
            while (list.get(j).compareTo(pivot) > 0)
                j--;
            if (i <= j) {
                exchange(i, j);
                i++;
                j--;
            }
        }
        if (low < j)
            quicksort(low, j);
        if (i < high)
            quicksort(i, high);
    }

    private void exchange(int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

}
