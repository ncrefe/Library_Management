import FileIO.FileProcessor;
import Services.Library;
import Services.LibraryManagement;
import Services.LibraryQuery;

// Muhammed efe İncir 270201029

public class LibraryManagementApp {
    public static void main(String[] args) {
        final LibraryManagement libraryManagement = new LibraryManagement();
        final Library[] libraries = {new Library(), new Library(), new Library()};
        final FileProcessor fileProcessor = new FileProcessor(libraryManagement, libraries[0], libraries[1], libraries[2]);
        try {
            fileProcessor.scanAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LibraryQuery libraryQuery = new LibraryQuery(libraryManagement, libraries);

        //  mostIssuedBook
        System.out.println("1)\t" + libraryQuery.mostIssuedBook().getTitle());

        //  memberWhoIssuesTheMostBook
        System.out.println("2)\t" + libraryQuery.memberWhoIssuesTheMostBook().getName());

        //  highestPenaltyForLateReturning
        System.out.println("3)\t" + libraryQuery.highestPenaltyForLateReturning());

        //  mostCopiedBook
        System.out.println("4)\t" + libraryQuery.mostCopiedBook().getTitle());

        //  fewestCopiedIssuedBook
        System.out.print("5)");
        for (var book : libraryQuery.fewestCopiedIssuedBook()) {
            if (book != null)
                System.out.println("\t"+ book.getTitle());
        }

        //  memberWithLeastNumberOfBooksFromCLS
        System.out.println("6)\t"+libraryQuery.memberWithLeastNumberOfBooksFromCLS().getName());

    }

}
