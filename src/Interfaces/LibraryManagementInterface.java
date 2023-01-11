package Interfaces;

import Models.Issue;

public interface LibraryManagementInterface {


    public void addIssueToLibrary(int libIndex, Issue issue);

    public void addIssueToLibrary1(Issue issue);

    public void addIssueToLibrary2(Issue issue);

    public void addIssueToLibrary3(Issue issue);

    public Issue[][] getIssues();
}
