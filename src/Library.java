import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Member> members = new ArrayList<Member>();
    private List<Book> books = new ArrayList<Book>();

    // Add a new member to the library
    public boolean addMember(Member member) {
    if (findMemberById(member.getId()) != null) {
             System.out.println("Error: A member with ID " + member.getId() + " already exists.");
             return false; // Duplicate ID
         }
    members.add(member);
         return true; // Successfully added
    }
   
   
    // Add a new book to the library
    public boolean addBook(Book book) {
        if (findBookById(book.getId()) != null) {
            System.out.println("Error: A book with ID " + book.getId() + " already exists.");
            return false; // Duplicate ID
        }
        books.add(book);
        return true; // Successfully added
    }

    // Find a member by ID
    public Member findMemberById(int id) {
        for (Member member : members) {
            if (member.getId() == id) {
                return member;
            }
        }
        return null;
    }

    // Find a book by ID
    public Book findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    // Get the list of members
    public List<Member> getMembers() {
        return members;
    }
   
    // Get the list of books
    public List<Book> getBooks() {
        return books;
    }
}
