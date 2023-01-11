package Services;

import Interfaces.LibraryManagementInterface;
import Models.Issue;

public class LibraryManagement implements LibraryManagementInterface {
    private Issue[][] issues = new Issue[3][50];
    private int issueIndex0 = 0;
    private int issueIndex1 = 0;
    private int issueIndex2 = 0;

    public LibraryManagement() {
    }

    public void addIssueToLibrary(int libIndex, Issue issue) {
        switch (libIndex) {
            case 0:
                addIssueToLibrary1(issue);
                break;
            case 1:
                addIssueToLibrary2(issue);
                break;
            case 2:
                addIssueToLibrary3(issue);
                break;
            default:
                throw new IllegalArgumentException("Invalid library index");
        }
    }

    public void addIssueToLibrary1(Issue issue) {
        issues[0][issueIndex0++] = issue;
    }

    public void addIssueToLibrary2(Issue issue) {
        issues[1][issueIndex1++] = issue;
    }

    public void addIssueToLibrary3(Issue issue) {
        issues[2][issueIndex2++] = issue;
    }


    public Issue[][] getIssues() {
        Issue[][] tempIssues = new Issue[3][50];
        for (int i = 0; issues.length > i; i++) {
            for (int a = 0; issues[i].length > a; a++) {
                if (issues[i][a] == null)
                    break;
                tempIssues[i][a] = issues[i][a];
            }
        }
        return issues;
    }

}
