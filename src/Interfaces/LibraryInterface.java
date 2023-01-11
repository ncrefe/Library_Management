package Interfaces;

import Models.Book;

public interface LibraryInterface {
    public void add(Book newEntry);

    public void clear();

    public Book remove(int index);

    public Book[] getBooks();
}
