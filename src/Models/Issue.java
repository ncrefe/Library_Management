package Models;

import Interfaces.IssueInterface;

import java.util.Date;

public class Issue implements IssueInterface {
    private String id;
    private Member member;
    private Book book;
    private Date issueDate;
    private Date returningDate;

    public Issue(String id, Member member, Book book, Date issueDate, Date returningDate) {
        this.id = id;
        this.member = member;
        this.book = book;
        this.issueDate = issueDate;
        this.returningDate = returningDate;
    }

    public String getId() {
        return id;
    }

    public Member getMember() {
        return new Member(member);
    }

    public Book getBook() {
        return new Book(book);
    }

    public Date getIssueDate() {
        Date copy = new Date(this.issueDate.getTime());
        return copy;
    }

    public Date getReturningDate() {
        Date copy = new Date(this.returningDate.getTime());
        return copy;
    }

    @Override
    public String toString() {
        return "Issue{" + "id='" + id + '\'' + ",member=" + member + ",book=" + book + ",issueDate='" + issueDate + '\''
                + ",returningDate='" + returningDate + '\'' + '}';
    }
}
