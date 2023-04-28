package first;

/**
 * This class represents a MenuItem with a name, a description and
 * whether the menu item is vegetarian, and price.
 */
public class MenuItem {
  private final String name;
  private final String description;
  private final boolean isVegetarian;
  private final float price;

  /**
   * This is the constructor that builds a menu item.
   *
   * @param name the name of the menu item.
   * @param description description of the menu item
   * @param isVegetarian Information on whether the menu item is vegetarian
   * @param price price of menu item
   */
  public MenuItem(String name, String description, boolean isVegetarian, int price) {
    this.name = name;
    this.description = description;
    this.isVegetarian = isVegetarian;
    this.price = price;
  }

  /**
   * Return the name of the menu item.
   *
   * @return the name of the menu item.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Return the description of the menu item.
   *
   * @return the description of the menu item.
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Return whether the menu item is vegetarian.
   *
   * @return whether the menu item is vegetarian.
   */
  public boolean getIsVegetarian() {
    return this.isVegetarian;
  }

  /**
   * Return the price of the movie.
   *
   * @return the price of the movie.
   */
  public float getPrice() {
    return this.price;
  }
}
