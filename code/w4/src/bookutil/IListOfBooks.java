package bookutil;

import book.Book;
import java.util.function.Predicate;

/**
 * This interface represents all the operations to be supported by a list of books
 */
public interface IListOfBooks {
  /**
   * Return the number of books in this list
   *
   * @return the size of this list
   */
  int count();

  int countHelp(int acc);

  /**
   * Return the total price of all books in this list
   *
   * @return the total price of all books
   */
  float totalPrice();

  /**
   * Return a list of all books within this list that are published before the given year
   *
   * @param year the year before which all the returned books are published
   * @return the list of all books published before the given year
   */2
  IListOfBooks allBefore(int year);

  /**
   * Return a sorted list of books in increasing order of price
   *
   * @return a sorted list of book
   */
  IListOfBooks sortByPrice();

  /**
   * Return a list obtained by adding the specified book in the list.
   *
   * @param book the book to be added.
   * @return the updated list.
   */
  IListOfBooks insert(Book book);

  /**
   * Create and return a string that can be used to print this list
   *
   * @return the String with the list information.
   */
  String toString();

  /**
   * General filter function on list of books
   *
   * @param test the predicate to be tested for filtering
   * @return a list of books that pass the provided test
   */
  IListOfBooks getList(Predicate<Book> test);

}