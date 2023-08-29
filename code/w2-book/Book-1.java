/**
 * This class represents a book. A book has a title, an author and a price
 */
public class Book {
  private String title;
  private Person author;
  private float price;

  /**
   * Construct a Book object that has the
   * provided title, author and  price
   *
   * @param title the title to be given to this book
   * @param author the author to be given to this book
   * @param price the price to be assigned to this book
   */

  public Book(String title, Person author, float price) {
    this.title = title;
    this.author = author;
    this.price = price;
  }

  /**
   * Return the title of this book
   * @return the title of this book
   */

  public String getTitle() {
    return this.title;
  }

  /**
   * Return the price of this book
    * @return the price of this book
   */
  public float getPrice() {
    return this.price;
  }

  /**
   * Return the author of this object
   * @return the author of this object as a @link{Person}
   */
  public Person getAuthor() {
    return this.author;
  }
  
  /**
   * Return the author of this object
   * @return the author of this object as a @link{Person}
   */
  public Person getAuthor() {
    return this.author;
  }

  /**
   * Return a formatted string that contains the information
   * of this object. The string should be in the following format:
   * 
   * Title: [title of the book]
   * Author: [first-name last-name]
   * Price: [Price as a decimal number with two numbers after decimal]
   * 
   * @return the formatted string as above
   */
  public String toString() {
    String str;

    str = "Title: "+ this.title + "\n" +
           "Author: "+this.author + "\n";
    str = str + String.format("Price: %.2f",price);

    return str;
  }


  /**
   * Compute and return the price of this book with the given discount (as a
   * percentage)
   *
   * @param discount the percentage discount to be applied
   * @return the discounted price of this book
   * @throws IllegalArgumentException if a negative discount is passed as an
   * argument
   */
  public float salePrice(float discount) throws IllegalArgumentException {
    /* TEMPLATE:
     this.price: float
     this.author: Person
     this.title: String

     Parameters:
     discount: float
     */
    if (discount<0) {
      throw new IllegalArgumentException("Discount cannot be negative");
    }

    return this.price - (this.price * discount) / 100;
  }

  /**
   * check if this book has the same author as another
   * and return true if so, false otherwise
   * @param other the other book
   * @return true if the two books have the same author, false otherwise
   */
  public boolean sameAuthor(Book other) {

    /* TEMPLATE:
      fields:
      this.title: String
      this.price: float
      this.author: Person

      methods:
      salePrice(float): float

      fields of parameters:
      other.title: String
      other.author: Person
      other.price: float

      methods of parameters:
      other.getTitle(): String
      other.getAuthor(): Person
      other.getPrice(): float
      other.salePrice(float): float
    */

    //get the two authors and delegate
    return this.author.same(other.author);
  }

  /**
   * Compute the sale price of this Book given using
   * the given discount rate (as a percentage) and
   * return a version of this book with the discounted price

   * @param discount the percentage discount to be applied to this book
   * @return the new book that is identical to this book except the price is
   * discounted
   */
  //
  public Book discountBook(float discount) {
  /* TEMPLATE:
  fields:
  this.title: String
  this.author: Person
  this.price: float

  constructor:
  Book(String,Person,float)

  methods:
  this.salePrice(float): float
  */

    float discountedPrice = this.salePrice(discount);
    return new Book(this.title, this.author, discountedPrice);
  }
}