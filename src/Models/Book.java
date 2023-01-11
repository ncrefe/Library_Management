package Models;

import Interfaces.BookInterface;

public class Book implements BookInterface {
  private String id;
  private String title;
  private String author;
  private String publisher;
  private int edition;
  private String genre;
  private int quantity;

  public Book(String id, String title, String author, String publisher, int edition, String genre, int quantity) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.publisher = publisher;
    this.edition = edition;
    this.genre = genre;
    this.quantity = quantity;
  }

  public Book(Book book) {
    this.id = book.id;
    this.title = book.title;
    this.author = book.author;
    this.publisher = book.publisher;
    this.edition = book.edition;
    this.genre = book.genre;
    this.quantity = book.quantity;
  }

  public Book(String[] data) {
    this.id = data[0];
    this.title = data[1];
    this.author = data[2];
    this.publisher = data[3];
    this.edition = Integer.parseInt(data[4]);
    this.genre = data[5];
    this.quantity = Integer.parseInt(data[6]);
  }

  public Book() {
  }

  public String getId() {
    return id;
  }


  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public String getPublisher() {
    return publisher;
  }

  public int getEdition() {
    int tempEdition = edition;
    return tempEdition;
  }

  public String getGenre() {
    return genre;
  }

  public int getQuantity() {
    int tempQuantity = quantity;
    return tempQuantity;
  }

  @Override
  public String toString() {
    return "Book{" + "id='" + id + '\'' + ",title='" + title + '\'' + ",author='" + author + '\'' + ",publisher='"
        + publisher + '\'' + ",edition=" + edition + ",genre='" + genre + '\'' + ",quantity=" + quantity + '}';
  }

}
