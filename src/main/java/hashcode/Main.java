package hashcode;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.String.join;

public class Main {
    public static void main(String[] argv) {
        Scanner scanner = new Scanner(System.in);
        Integer nbBooks = scanner.nextInt();
        Integer nbLibrary = scanner.nextInt();
        Integer nbDays = scanner.nextInt();
        //System.err.println(nbBooks);
        //System.err.println(nbLibrary);
        //System.err.println(nbDays);
        Map<Integer, Book> books = new HashMap<>();
        List<Library> libraries = new ArrayList<>(nbLibrary);
        for (int i = 0; i < nbBooks; i++) {
            Book book = new Book(i, scanner.nextInt());
            //System.err.println(String.format("Book %d (%d)", book.id, book.score));
            books.put(book.id, book);
        }

        for (int j = 0; j < nbLibrary; j++) {
            Library library = new Library(j, scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
            libraries.add(library);
            //System.err.println(String.format("Library %d (%d books, %d days, %d shipped)", library.id, library.nbBooks, library.nbDays, library.nbShipped));
            for (int k = 0; k < library.nbBooks; k++) {
                int bookId = scanner.nextInt();
                Book book = books.get(bookId);
                library.books.add(book);
            }
        }

        libraries.forEach(library -> {
            List<Book> sortedBooks = library.books.stream()
                    .sorted(Comparator.comparing(b -> b.score))
                    .sorted(Collections.reverseOrder())
                    .collect(Collectors.toList());
            library.books.clear();
            library.books.addAll(sortedBooks);
        });


        List<Library> sortedLibraries = libraries.stream()
                .sorted(Comparator.comparing(l -> l.nbDays))
                .collect(Collectors.toList());

        // TODO Resolve

        print(sortedLibraries);
    }

    private static void print(List<Library> libraries) {
        System.out.println(libraries.size());
        for (Library library : libraries) {
            System.out.println(String.format("%d %d", library.id, library.nbBooks));
            System.out.println(library.books.stream().map(b -> b.id.toString()).collect(Collectors.joining(" ")));
        }

    }
}
