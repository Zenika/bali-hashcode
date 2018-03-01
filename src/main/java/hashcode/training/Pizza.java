package hashcode.training;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

public class Pizza {
    int rows;
    int columns;
    int min;
    int max;

    Boolean[][] cells;
    List<Slice> slices = new ArrayList<>();

    public boolean isSliceValid(Slice slice) {
        return slice.mushrooms <= max && slice.mushrooms > min
                && slice.tomatoes <= max && slice.tomatoes > min
                && slice.c2 < columns && slice.r2 < rows;
    }

    public Pair<Integer,Integer> nextStart() {
        if (slices.size() < 2) {
            Slice currentSlice = slices.get(0);
            int r = currentSlice.r2 < max-1 ? currentSlice.r2+1 : 0;
            int c = currentSlice.r2 == max-1 ? currentSlice.c2 + 1 : 0;
            return new Pair<>(r, c);
        }

        Slice slice1 = slices.get(slices.size()-1);
        Slice slice2 = slices.get(slices.size()-2);
        int size = slices.size();

        int r = slices.get(size-1).r2 < max-1 ? slices.get(size-1).r2+1 : slices.get(size-2).r2+1;
        int c = slices.get(size-1).r2 == max-1 ? slices.get(size-1).c2 + 1 : slices.get(size-2).c2+1;
        return new Pair<>(r, c);
    }
}
