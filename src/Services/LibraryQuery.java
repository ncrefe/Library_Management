package Services;

import java.util.concurrent.TimeUnit;

import Interfaces.LibraryQueryInterface;
import Models.Book;
import Models.Issue;
import Models.Member;

public class LibraryQuery implements LibraryQueryInterface {
    private static final Double BORROW_PENALTY = 0.5;
    private static final Integer BORROW_PERIOD = 14;

    private final Library[] libraries;
    private final Issue[][] issues;

    private final Member[] members;
    private final Book[] books;

    public LibraryQuery(LibraryManagement libraryManagement, Library[] libraries) {
        this.issues = libraryManagement.getIssues();
        this.libraries = libraries;

        int bookIndex = 0;
        books = new Book[200];
        for (Library library : libraries) {
            for (Book book : library.getBooks()) {
                books[bookIndex++] = book;
            }
        }

        int memberIndex = 0;
        members = new Member[10];

        for (int i = 0; i < issues.length; i++) {
            for (int j = 0; j < issues[i].length; j++) {
                if (issues[i][j] != null && issues[i][j].getMember() != null) {
                    boolean isAdded = false;

                    for (Member member : members) {
                        if (member != null && member.getId() == issues[i][j].getMember().getId()) {
                            isAdded = true;
                            break;
                        }
                    }
                    if (!isAdded) {
                        members[memberIndex++] = issues[i][j].getMember();
                    }
                }
            }
        }
    }

    public Book mostIssuedBook() {
        int[] bookIssues = new int[books.length];

        for (int i = 0; i < issues.length; i++) {
            for (int j = 0; j < issues[i].length; j++) {
                if (issues[i][j] != null) {
                    for (int bookIndex = 0; bookIndex < books.length; bookIndex++) {
                        if (books[bookIndex] != null && books[bookIndex].getId() == issues[i][j].getBook().getId()) {
                            bookIssues[bookIndex]++;
                        }
                    }
                } else {
                    break;
                }
            }
        }

        int mostIssuedBookIndex = 0;
        int mostIssuedBookCount = 0;
        for (int i = 0; i < bookIssues.length; i++) {
            if (bookIssues[i] > mostIssuedBookCount) {
                mostIssuedBookIndex = i;
                mostIssuedBookCount = bookIssues[i];
            }
        }

        return books[mostIssuedBookIndex];
    }

    public Member memberWhoIssuesTheMostBook() {
        int[] issueCount = new int[members.length];

        for (int libraryIndex = 0; libraryIndex < issues.length; libraryIndex++) {
            for (int issueIndex = 0; issueIndex < issues[libraryIndex].length; issueIndex++) {
                if (issues[libraryIndex][issueIndex] != null) {
                    int index = findMemberIndexFromMemberId(issues[libraryIndex][issueIndex].getMember().getId());
                    issueCount[index]++;
                } else {
                    break;
                }
            }
        }

        int mostIssueCount = issueCount[0];
        int indexOfMember = 0;

        for (int i = 0; i < issueCount.length; i++) {
            if (mostIssueCount < issueCount[i]) {
                mostIssueCount = issueCount[i];
                indexOfMember = i;
            }
        }

        return members[indexOfMember];
    }

    // Highest penalty for late returning (Note that borrowing period of a book is
    // 14 days. After 14 days, 0.50 TL penalty for each day is charged).
    public double highestPenaltyForLateReturning() {
        double highestPenalty = 0;

        for (int libraryIndex = 0; libraryIndex < issues.length; libraryIndex++) {
            for (int issueIndex = 0; issueIndex < issues[libraryIndex].length; issueIndex++) {
                if (issues[libraryIndex][issueIndex] != null) {
                    Issue issue = issues[libraryIndex][issueIndex];
                    long diff = issue.getReturningDate().getTime() - issue.getIssueDate().getTime();
                    double days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

                    if (days > BORROW_PERIOD) {
                        double penalty = (days - BORROW_PERIOD) * BORROW_PENALTY;
                        if (penalty > highestPenalty) {
                            highestPenalty = penalty;
                        }
                    }
                } else {
                    break;
                }
            }
        }

        return highestPenalty;

    }

    public Book mostCopiedBook() {
        Book mostCopiedBook = new Book();

        for (Library library : libraries) {
            for (Book book : library.getBooks()) {
                if (book == null) {
                    break;
                } else if (book.getQuantity() > mostCopiedBook.getQuantity()) {
                    mostCopiedBook = book;
                }
            }
        }

        return mostCopiedBook;
    }

    public Book[] fewestCopiedIssuedBook() {
        Book[] fewestCopiedIssuedBooks = new Book[20];
        int fewestCopiedIssuedBooksIndex = 1;

        Book fewestCopiedIssuedBook = null;

        for (Library library : libraries) {
            for (Book book : library.getBooks()) {
                if (book == null) {
                    break;
                } else {
                    // Check for issue by book id
                    boolean isIssued = false;
                    for (int i = 0; i < issues.length; i++) {
                        for (int j = 0; j < issues[i].length; j++) {
                            if (issues[i][j] != null && issues[i][j].getBook().getId() == book.getId()) {
                                isIssued = true;
                                break;
                            }
                        }
                    }

                    if (isIssued) {
                        if (fewestCopiedIssuedBook == null) {
                            fewestCopiedIssuedBook = book;

                        } else if (book.getQuantity() < fewestCopiedIssuedBook.getQuantity()) {
                            fewestCopiedIssuedBook = book;
                            for (int i = 1; i < fewestCopiedIssuedBooks.length; i++) {
                                fewestCopiedIssuedBooks[i] = null;
                            }
                            fewestCopiedIssuedBooks[0] = book;
                            fewestCopiedIssuedBooksIndex = 1;
                        } else if (book.getQuantity() == fewestCopiedIssuedBook.getQuantity()) {
                            fewestCopiedIssuedBooks[fewestCopiedIssuedBooksIndex++] = book;
                        }
                    }
                }
            }
        }

        return fewestCopiedIssuedBooks;

    }

    public Member memberWithLeastNumberOfBooksFromCLS() {
        int[] issueCount = new int[members.length];

        for (int issueIndex = 0; issueIndex < issues[2].length; issueIndex++) {
            if (issues[2][issueIndex] != null) {
                int index = findMemberIndexFromMemberId(issues[2][issueIndex].getMember().getId());
                issueCount[index]++;
            } else {
                break;
            }
        }

        int leastIssueCount = issueCount[0];
        int indexOfMember = 0;

        for (int i = 0; i < issueCount.length; i++) {
            if (leastIssueCount > issueCount[i] && issueCount[i] != 0) {
                leastIssueCount = issueCount[i];
                indexOfMember = i;
            }
        }

        return members[indexOfMember];
    }

    private int findMemberIndexFromMemberId(int memberId) {
        for (int i = 0; i < members.length; i++) {
            if (members[i].getId() == memberId) {
                return i;
            }
        }
        return -1;
    }
}
