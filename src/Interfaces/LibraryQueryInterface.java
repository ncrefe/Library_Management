package Interfaces;

import Models.Book;
import Models.Member;

public interface LibraryQueryInterface {
    //1- The most issued book (among the three libraries).
    public Book mostIssuedBook();

    //2- The member who issues the most books (for all three libraries and all years).
    public Member memberWhoIssuesTheMostBook();

    //3- Highest penalty for late returning (Note that borrowing period of a book is 14 days. After 14 days, 0.50 TL penalty for each day is charged).
    public double highestPenaltyForLateReturning();

    //4- The book with the most copies (among the three libraries).
    public Book mostCopiedBook();

    //5- The book with the fewest copies of previously issued books.
    public Book[] fewestCopiedIssuedBook();

    //6- The member who issues the least number of books from the Computer Science Library.
    public Member memberWithLeastNumberOfBooksFromCLS();

}
