package FileIO;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

import Models.Book;
import Models.Issue;
import Models.Member;
import Models.DTO.IssueDTO;
import Services.Library;
import Services.LibraryManagement;

public class FileProcessor {
    private final LibraryManagement libraryManagement;
    private final Library library1;
    private final Library library2;
    private final Library library3;
    private Book[] books = new Book[50];
    private Member[] members = new Member[50];
    private IssueDTO[][] issueDTOs = new IssueDTO[3][50];
    private int bookIndex = 0;
    private int issueIndex0 = 0;
    private int issueIndex1 = 0;
    private int issueIndex2 = 0;
    private int memberIndex = 0;

    public FileProcessor(LibraryManagement libraryManagement, Library library1, Library library2, Library library3) {
        this.libraryManagement = libraryManagement;
        this.library1 = library1;
        this.library2 = library2;
        this.library3 = library3;
    }

    private void scan(String file) throws ParseException {
        Scanner scanner;

        try {
            scanner = new Scanner(new File("src/assets/" + file));

            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");

                if (file.contains("Books")) {
                    Book book = new Book(data);
                    books[bookIndex++] = book;

                    if (file.contains("L1")) {
                        library1.add(book);
                    } else if (file.contains("L2")) {
                        library2.add(book);
                    } else if (file.contains("L3")) {
                        library3.add(book);
                    }
                } else if (file.contains("Issues")) {
                    IssueDTO issueDto = new IssueDTO(data);
                    if (file.contains("L1")) {
                        issueDTOs[0][issueIndex0++] = issueDto;
                    } else if (file.contains("L2")) {
                        issueDTOs[1][issueIndex1++] = issueDto;
                    } else if (file.contains("L3")) {
                        issueDTOs[2][issueIndex2++] = issueDto;
                    }
                } else if (file.contains("Members")) {
                    Member member = new Member(data);
                    members[memberIndex++] = member;
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void scanAll() throws ParseException {
        String[] files = {"L1_Books.csv", "L1_Issues.csv", "L2_Books.csv", "L2_Issues.csv", "L3_Books.csv",
                "L3_Issues.csv", "Members.csv",};

        for (String file : files) {
            scan(file);
        }

        for (int i = 0; i < 3; i++) {
            for (int x = 0; x < issueDTOs[i].length; x++) {
                if (issueDTOs[i][x] != null) {
                    Member member = null;
                    for (Member mmbr : members) {
                        if (mmbr != null) {
                            if (mmbr.getId() == issueDTOs[i][x].getMemberId()) {
                                member = mmbr;
                                break;
                            }
                        }
                    }

                    Book book = null;
                    for (Book bk : books) {
                        if (bk != null) {
                            if (bk.getId().equals(issueDTOs[i][x].getBookId())) {
                                book = bk;
                                break;
                            }
                        }
                    }

                    libraryManagement.addIssueToLibrary(i, new Issue(issueDTOs[i][x].getId(), member, book,
                            issueDTOs[i][x].getIssueDate(), issueDTOs[i][x].getReturningDate()));

                }
            }

        }
    }
}
