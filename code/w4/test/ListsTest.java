import static org.junit.Assert.assertEquals;

import book.Book;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Test;

/**
 * Created by ashesh on 2/8/2017.
 */
public class ListsTest {

  @Test public void testStringLinkedList() {
    List<String> llist = new LinkedList<>();
    llist.add(0, "won");
    llist.add(0, "Patriots");
    llist.add("Super");
    llist.add("Bowl");
    llist.add(2, "the");
    assertEquals("[Patriots, won, the, Super, Bowl]", llist.toString());
    assertEquals(5, llist.size());
    assertEquals("Super", llist.get(3));

    llist.remove("Patriots");
    llist.add(0, "Falcons");
    llist.add(1, "did");
    llist.add(2, "not");
    llist.remove("won");
    llist.add(3, "win");
    assertEquals("[Falcons, did, not, win, the, Super, Bowl]", llist.toString());
    assertEquals(7, llist.size());

  }

  @Test public void testSwap() {
    List<String> linkedList = new LinkedList<>();

    linkedList.add("I");
    linkedList.add("am");
    linkedList.add("a");
    linkedList.add("teacher");

    assertEquals("[I, am, a, teacher]", linkedList.toString());
    StringListUtils.swap(linkedList, 0, 2);
    StringListUtils.swap(linkedList, 1, 3);
    assertEquals("[a, teacher, I, am]", linkedList.toString());
  }

  @Test public void testToString() {
    List<String> linkedList = new LinkedList<>();

    linkedList.add("I");
    linkedList.add("am");
    linkedList.add("a");
    linkedList.add("teacher");

    assertEquals("(I) (am) (a) (teacher)", StringListUtils.toStringLoop(linkedList));
  }

  @Test public void testToStringPos() {
    List<String> linkedList = new LinkedList<>();

    linkedList.add("I");
    linkedList.add("am");
    linkedList.add("a");
    linkedList.add("teacher");
  }

  @Test public void testBookListWithStreams() {
    List<Book> bookList = new LinkedList<>();

    bookList.add(new Book("HP 1", "J.K. Rowling", 1997, 19.99f));
    bookList.add(new Book("HP 2", "J.K. Rowling", 1998, 10.99f));
    bookList.add(new Book("HP 3", "J.K. Rowling", 1999, 12.99f));
    bookList.add(new Book("HP 4", "J.K. Rowling", 2001, 15.99f));

    //Objective: extract all the titles
    List<String> allTitles = new LinkedList<>();
    //1. Accomplishing this with a for-each  loop
    for (Book b : bookList) {
      allTitles.add(b.getTitle());
    }
    assertEquals("[HP 1, HP 2, HP 3, HP 4]", allTitles.toString());

    //2. Accomplishing this with a map function (List<Book> => List<String>)
    allTitles = bookList.stream().map(Book::getTitle).collect(Collectors.toList());

    assertEquals("[HP 1, HP 2, HP 3, HP 4]", allTitles.toString());

    //find the sum of all prices of the books
    double totalPrice;
    // 1. Accomplishing this with a for-each loop with a counter
    totalPrice = 0;
    for (Book b : bookList) {
      totalPrice += b.getPrice();
    }
    assertEquals(59.96, totalPrice, 0.01);

    // 2. Accomplishing this with the built-in reduce (fold)
    // (List<Book> => double)

    totalPrice = bookList.stream().mapToDouble(Book::getPrice).sum();
    assertEquals(59.96, totalPrice, 0.01);

    // 3. Accomplishing this with a reduce function that explicitly
    // starts with initial value of 0, and then combines two elements
    // by adding them

    totalPrice = bookList.stream().mapToDouble(Book::getPrice).reduce(0, Double::sum);

    assertEquals(59.96, totalPrice, 0.01);

    //Objective: find the total price of all books written before 2000
    // 1. Accomplishing this with a for loop
    totalPrice = 0;

    for (Book b : bookList) {
      if (b.getYear() < 2000) {
        totalPrice += b.getPrice();
      }
    }

    assertEquals(43.97, totalPrice, 0.01);

    //2. Accomplishing this with a filter (List<Book> => List<Book>)
    // followed by a map (List<Book> => List<Double>) and then a fold

    totalPrice =
        bookList.stream().filter(b -> b.getYear() < 2000).mapToDouble(Book::getPrice).sum();

    assertEquals(43.97, totalPrice, 0.01);
  }
}