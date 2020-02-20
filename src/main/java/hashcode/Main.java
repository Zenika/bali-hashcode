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
                    .sorted(Comparator.comparing(b -> -b.score))
                    .collect(Collectors.toList());
            library.sendBooks.addAll(sortedBooks);
        });

        libraries.parallelStream().forEach(library -> library.score = getLibraryScore(nbDays, library));

        List<Library> sortedLibraries = libraries.stream()
                .sorted(Comparator.comparing(l -> -l.score))
                .collect(Collectors.toList());

        Collections.reverse(sortedLibraries);
        List<Library> filteredLibraries = fetchBooksToSend(sortedLibraries);
        Collections.reverse(filteredLibraries);

        print(filteredLibraries);
    }

    private static void print(List<Library> libraries) {
        System.out.println(libraries.size());
        for (Library library : libraries) {
            System.out.println(String.format("%d %d", library.id, library.nbBooks));
            System.out.println(library.sendBooks.stream().map(b -> b.id.toString()).collect(Collectors.joining(" ")));
        }

    }

    private static List<Library> fetchBooksToSend(List<Library> libraries) {
        Set<Book> blacklist = new HashSet<>();
        libraries.forEach(library -> {
            for (Book book : library.books) {
                if (!blacklist.contains(book)) {
                    blacklist.add(book);
                    library.sendBooks.add(book);
                }
            }
        });
        return libraries;
    }

    private static long getLibraryScore(int nbDays, Library library) {
        int currentNbDay;
        int nbBooks;
        // Calculer le nombre de jours effectifs
        // A améliorer
        currentNbDay = nbDays - library.nbDays;
        if (currentNbDay > 20) {
            currentNbDay = 20;
        }

        // Calculer le nombre de livre envoyable
        nbBooks = currentNbDay * library.nbShipped;

        // Calculer le score de ces livres
        long score = 0;

        for (int i = 0; i < nbBooks; i++) {
            if (library.books.size() > i){
                score += library.books.get(i).score;
            }
        }

        return score;
    }
}