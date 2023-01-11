package Models.DTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IssueDTO {
    //Issue Data Transfer Object

    private String id;
    private int memberId;
    private String bookId;
    private Date issueDate;
    private Date returningDate;

    public IssueDTO(String id, int memberId, String bookId, Date issueDate, Date returningDate) {
        this.id = id;
        this.memberId = memberId;
        this.bookId = bookId;
        this.issueDate = issueDate;
        this.returningDate = returningDate;
    }

    public IssueDTO(String[] data) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", java.util.Locale.ENGLISH);

        this.id = data[0];
        this.memberId = Integer.parseInt(data[1]);
        this.bookId = data[2];

        this.issueDate = simpleDateFormat.parse(data[3]);
        this.returningDate = simpleDateFormat.parse(data[4]);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMemberId() {
        return this.memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getBookId() {
        return this.bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Date getIssueDate() {
        Date copy = new Date(this.issueDate.getTime());
        return copy;

    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getReturningDate() {
        Date copy = new Date(this.returningDate.getTime());
        return copy;
    }

    public void setReturningDate(Date returningDate) {
        this.returningDate = returningDate;
    }

    public String toString() {
        return "IssueDTO{" + "id='" + id + '\'' + ",memberId=" + memberId + ",bookId='" + bookId + '\'' + ",issueDate="
                + issueDate + ",returningDate=" + returningDate + '}';
    }

}
