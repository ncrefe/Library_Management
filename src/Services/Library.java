package Services;

import Interfaces.LibraryInterface;
import Models.Book;

public class Library implements LibraryInterface {
    private Book[] books;
    private int index = 0;

    private static final int DEFAULT_CAPACITY = 50;

    public Library() {
        this(DEFAULT_CAPACITY);
    }

    public Library(int initialCapacity) {
        Book[] tempArray = new Book[initialCapacity + 1];
        books = tempArray;

    }

    public void add(Book newEntry) {
        books[index++] = newEntry;
    }

    public void clear() {
        books = new Book[DEFAULT_CAPACITY];
    }

    public Book remove(int index) {
        Book returnedBook = books[index];
        books[index] = null;
        return returnedBook;
    }

    public Book[] getBooks() {
        Book[] tempArray = new Book[books.length];

        for (int i = 0; i < books.length; i++) {
            tempArray[i] = books[i];
        }
        return tempArray;
    }

}
