package hashcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.String.join;

public class Main {
    public static void main(String[] argv) {
        Scanner scanner = new Scanner(System.in);
        Integer nbBooks = scanner.nextInt();
        Integer nbLibrary = scanner.nextInt();
        Integer nbDays = scanner.nextInt();
        System.err.println(nbBooks);
        System.err.println(nbLibrary);
        System.err.println(nbDays);
        Map<Integer, Book> books = new HashMap<>();
        List<Library> libraries = new ArrayList<>(nbLibrary);
        for (int i = 0; i < nbBooks; i++) {
            Book book = new Book(i, scanner.nextInt());
            System.err.println(String.format("Book %d (%d)", book.id, book.score));
            books.put(book.id, book);
        }

        for (int j = 0; j < nbLibrary; j++) {
            Library library = new Library(j, scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
            libraries.add(library);
            System.err.println(String.format("Library %d (%d books, %d days, %d shipped)", library.id, library.nbBooks, library.nbDays, library.nbShipped));
            for (int k = 0; k < library.nbBooks; k++) {
                int bookId = scanner.nextInt();
                Book book = books.get(bookId);
                library.books.add(book);
            }
        }

        // TODO Resolve

        print();
    }

    private static void print() {
        System.out.println(0);

    }
}
