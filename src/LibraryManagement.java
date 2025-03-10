import java.util.Scanner;

public class LibraryManagement {
    private Library library = new Library();

    public static void main(String[] args) throws Exception {
        new LibraryManagement().run();
    }

    private void run() throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("===========================");
            System.out.println("Library Management System");
            System.out.println("1. Add Member");
            System.out.println("2. Add Book");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. View Borrowed Books");
            System.out.println("6. View Transaction History");
            System.out.println("7. Exit");
            System.out.println("===========================");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter member ID: ");
                    int id = scanner.nextInt();
                	System.out.print("Enter member name: ");
                    String name = scanner.next();
                    
                    scanner.nextLine();

                    Member newMember = new Member(id, name);
                    library.addMember(newMember);
                    System.out.println("Member added successfully.");
                    break;
                case 2:
                    System.out.print("Enter book ID: ");
                    id = scanner.nextInt();
                    System.out.print("Enter book title: ");
                    name = scanner.next();

                    try {
                        // Attempt to create and add a new book
                        Book newBook = new Book(id, name);
                        library.addBook(newBook);
                        System.out.println("Book added to library successfully.");
                    } catch (Exception e) {
                        // Handle invalid ID error
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 3:
                	System.out.println("\n--- Available Members ---");
                    for (Member member : library.getMembers()) {
                        System.out.println(member.getId() + ". " + member.getName());
                    }
                    
                    System.out.print("Enter member ID: ");
                    int memberId = scanner.nextInt();
                    
                    System.out.println("\n--- Available Books ---");
                    for (Book book : library.getBooks()) {
                        if (book.isAvailable())
                            System.out.println(book.getId() + ". " + book.getTitle());
                    }
                    
                    System.out.print("Enter book ID: ");
                    int bookId = scanner.nextInt();
                    
                    scanner.nextLine();

                    Member member = library.findMemberById(memberId);
                    Book book = library.findBookById(bookId);

                    if (member != null && book != null) {
                    	Transaction transaction = new Transaction();
						transaction.borrowBook(book, member);
                    } else {
                        System.out.println("Invalid member or book ID.");
                    }
                    break;
                case 4:
                	System.out.print("Enter member ID: ");
                    memberId = scanner.nextInt();
                    
                    System.out.print("Enter book ID: ");
                    bookId = scanner.nextInt();
                    
                    scanner.nextLine();

                    member = library.findMemberById(memberId);
                    book = library.findBookById(bookId);

                    if (member != null && book != null) {
                    	Transaction.returnBook(book, member);
                    } else {
                        System.out.println("Invalid member or book ID.");
                    }
                    break;
                case 5:
                	System.out.print("Enter member ID: ");
                    memberId = scanner.nextInt();
                    scanner.nextLine();

                    Member specificMember = library.findMemberById(memberId);
                    
                    if (specificMember != null) {
                        System.out.println("Books borrowed by " + specificMember.getName() + ":");
                        for (Book bk : specificMember.getBorrowedBooks()) {
                            System.out.println(" - " + bk.getTitle());
                        }
                    } else {
                        System.out.println("Invalid member ID.");
                    }
                    break;
                case 6:
                	Transaction.getTransaction().displayTransactionHistory();
                    break;
                case 7:
                    System.out.println("Exiting. Good Bye..");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }


    private void addMember(Scanner scanner) {

        System.out.print("Enter member's ID number: ");

        int id = scanner.nextInt();

        System.out.print("Enter member's name: ");

        String name = scanner.next();

        scanner.nextLine();



        Member newMember = new Member(id, name);

        if (library.addMember(newMember)) {

            System.out.println("Member added!!.");

        } else {

            System.out.println("Fail to add member. Duplicate ID.");

        }

    }



    private void addBook(Scanner scanner) throws Exception {

        System.out.print("Enter the book ID number: ");

        int id = scanner.nextInt();

        System.out.print("Enter the book title: ");

        String title = scanner.next();

        scanner.nextLine();



        Book newBook = new Book(id, title);

        if (library.addBook(newBook)) {

            System.out.println("Book successfully added to library.");

        } else {

            System.out.println("Failed to add book to library. Duplicate ID.");

        }

    }



    private void borrowBook(Scanner scanner) {

        System.out.println("\n Members Available");

        for (Member member : library.getMembers()) {

            System.out.println(member.getId() + ". " + member.getName());

        }



        System.out.print("Enter member ID: ");

        int memberId = scanner.nextInt();



        System.out.println("\n Books Available");

        for (Book book : library.getBooks()) {

            if (book.isAvailable()) {

                System.out.println(book.getId() + ". " + book.getTitle());

            }

        }



        System.out.print("Enter book ID: ");

        int bookId = scanner.nextInt();

        scanner.nextLine();



        Member member = library.findMemberById(memberId);

        Book book = library.findBookById(bookId);



        if (member != null && book != null) {

            Transaction transaction = Transaction.getTransaction();

            if (transaction.borrowBook(book, member)) {

                System.out.println("Book borrowed.");

            }

        } else {

            System.out.println("Invalid member or book ID.");

        }

    }



    private void returnBook(Scanner scanner) {

        System.out.print("Enter member ID number : ");

        int memberId = scanner.nextInt();



        System.out.print("Enter book ID number: ");

        int bookId = scanner.nextInt();

        scanner.nextLine();



        Member member = library.findMemberById(memberId);

        Book book = library.findBookById(bookId);



        if (member != null && book != null) {

            Transaction transaction = Transaction.getTransaction();

            transaction.returnBook(book, member);

            System.out.println("Book returned.");

        } else {

            System.out.println("Invalid member or book ID.");

        }

    }



    private void viewBorrowedBooks(Scanner scanner) {

        System.out.print("Enter member ID: ");

        int memberId = scanner.nextInt();

        scanner.nextLine();



        Member specificMember = library.findMemberById(memberId);



        if (specificMember != null) {

            System.out.println("Books borrowed by " + specificMember.getName() + ":");

            for (Book bk : specificMember.getBorrowedBooks()) {

                System.out.println(" - " + bk.getTitle());

            }

        } else {

            System.out.println("Invalid member ID.");

        }

    }



    private void viewTransactionHistory() {

        Transaction transaction = Transaction.getTransaction();

        transaction.displayTransactionHistory();

    }

}