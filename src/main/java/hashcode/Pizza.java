package hashcode;

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
}
