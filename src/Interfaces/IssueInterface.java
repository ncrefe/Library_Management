package Interfaces;

import Models.Book;
import Models.Member;

import java.util.Date;

public interface IssueInterface {

    public String getId();

    public Member getMember();

    public Book getBook();

    public Date getIssueDate();

    public Date getReturningDate();

}
