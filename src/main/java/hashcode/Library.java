package hashcode;

import java.util.ArrayList;
import java.util.List;

public class Library {
    public Integer id;
    public Integer nbBooks;
    public Integer nbDays;
    public Integer nbShipped;
    public List<Book> books = new ArrayList<>();


    public Library(Integer id, Integer nbBooks, Integer nbDays, Integer nbShipped) {
        this.id = id;
        this.nbBooks = nbBooks;
        this.nbDays = nbDays;
        this.nbShipped = nbShipped;
    }
}
