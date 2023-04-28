import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * JUnit test class for books
 */
public class BookTest {

  private Person pat;
  private Book beaches;

  @Before
  public void setUp() {
    pat = new Person("Pat", "Conroy", 1948);
    // example of books
    beaches = new Book("Beaches", this.pat, TypeOfBook.HARDCOVER,20);
  }

  @Test
  public void testBookString() {
    String expected;
    expected = "Title: Beaches\nAuthor: Pat Conroy\n"
            +  "Type: Hard Cover\nPrice: 20.00";
    assertEquals(expected,beaches.toString());
  }

  @Test
  public void testDiscount() {
    float discountedPrice = beaches.salePrice(20);
    assertEquals(16.0f, discountedPrice, 0.01);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalDiscount() {
    float discountedPrice;

    discountedPrice = beaches.salePrice(-20);
  }

  @Test
  public void testSameAuthors() {
    Person anotherauthor = new Person("Pat", "Conroy II", 1948);
    Book pirateBeaches = new Book("Beaches",
            anotherauthor, beaches.getBookType(),1948);
    Book sequel = new Book("Some more beaches",this.pat,beaches.getBookType()
            ,1952);
    assertTrue(beaches.sameAuthor(sequel));
    assertFalse(beaches.sameAuthor(pirateBeaches));

  }

  @Test
  public void testDiscountBook() {
    Book discountedBook = beaches.discountBook(20);
    assertTrue(beaches.sameAuthor(discountedBook));
    assertEquals(beaches.getTitle(),discountedBook.getTitle());
    assertEquals(16,discountedBook.getPrice(),0.01);
  }
}