import static org.junit.Assert.*;
import org.junit.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

public class LibraryManagementTest {

    @Test
    public void testBookId() {
        
        try {
            Book book1 = new Book(100, "Valid Book 1");
            assertNotNull("Book1 should not be null", book1);
            assertEquals(100, book1.getId());
            assertEquals("Valid Book 1", book1.getTitle());

            Book book2 = new Book(999, "Valid Book 2");
            assertNotNull("Book2 should not be null", book2);
            assertEquals(999, book2.getId());
            assertEquals("Valid Book 2", book2.getTitle());
        } catch (Exception e) {
            fail("Valid book IDs should not throw exceptions: " + e.getMessage());
        }

        
        try {
            new Book(1000, "Invalid Book");
            fail("Expected exception for invalid book ID 1000 was not thrown.");
        } catch (Exception e) {
            assertEquals("Invalid book ID. Must be between 100 and 999.", e.getMessage());
        }

        
        try {
            new Book(99, "Invalid Book");
            fail("Expected exception for invalid book ID 99 was not thrown.");
        } catch (Exception e) {
            assertEquals("Invalid book ID. Must be between 100 and 999.", e.getMessage());
        }
    }

    @Test
    public void testBorrowReturn() throws Exception {
       
        Book book = new Book(101, "Test Book");
        Member member = new Member(1, "Test Member");

        
        assertTrue("Book should be available initially", book.isAvailable());

        
        Transaction transaction = Transaction.getInstance();
        transaction.borrowBook(book, member);
        assertFalse("Book should be unavailable after borrowing", book.isAvailable());

        // Attempt to borrow the book again (should fail)
        boolean borrowFailed = false;
        try {
            transaction.borrowBook(book, member);
        } catch (IllegalStateException e) {
            borrowFailed = true;
        }
        assertTrue("Borrowing the same book again should fail", borrowFailed);

        
        transaction.returnBook(book, member);
        assertTrue("Book should be available after returning", book.isAvailable());

        
        boolean returnFailed = false;
        try {
            transaction.returnBook(book, member);
        } catch (IllegalStateException e) {
            returnFailed = true;
        }
        assertTrue("Returning the same book again should fail", returnFailed);
    }

    @Test
    public void testSingletonTransaction() throws Exception {
        // Use reflection to access the constructor of the Transaction class
        Constructor<Transaction> constructor = Transaction.class.getDeclaredConstructor();

        // Check if the constructor is private
        int modifiers = constructor.getModifiers();
        assertTrue("Constructor should be private", Modifier.isPrivate(modifiers));

        // Attempt to make the constructor accessible
        constructor.setAccessible(true);
        try {
            constructor.newInstance();
            fail("Expected exception for singleton violation was not thrown.");
        } catch (Exception e) {
            
            assertTrue(e.getCause() instanceof IllegalStateException);
            assertEquals("Instance already exists!", e.getCause().getMessage());
        }
    }
}
