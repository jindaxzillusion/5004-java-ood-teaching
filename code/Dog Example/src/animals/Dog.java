package animals;

/**
 * This is the Dog class. A dog has a name, age, weight, size (small / medium / large), and
 * vaccination status.
 */
public class Dog {

  public String name;
  public int age;
  public double weight;
  public Size size;
  public boolean vaccinated;

  /**
   * Constructor for the class Dog.
   *
   * @param name the name of the dog.
   * @param age the age of the dog in years.
   * @param weight the dog's weight in kg.
   * @param size the size od the dog (small, medium or large).
   * @param vaccinated vaccination status (true / false)
   * @throws IllegalArgumentException when age or weight are negative.
   */
  public Dog(String name, int age, double weight, Size size, boolean vaccinated)
      throws IllegalArgumentException {
    if (age < 0) {
      throw new IllegalArgumentException("Dog's age cannot be negative");
    }
    if (weight < 0) {
      throw new IllegalArgumentException("Dog's weight cannot be negative");
    }

    this.name = name;
    this.age = age;
    this.weight = weight;
    this.size = size;
    this.vaccinated = vaccinated;
  }

  /**
   * Get the dog's name.
   *
   * @return the dog's name.
   */
  String getName() {
    return this.name;
  }

  /**
   * Get the dog's age in years.
   *
   * @return the dog's age.
   */
  int getAge() {
    return this.age;
  }

  /**
   * Get the dog's weight in kilograms.
   *
   * @return The dog's weight in kilograms.
   */
  double getWeight() {
    return this.weight;
  }

  /**
   * Get the dog's size.
   *
   * @return an enum with one of the options for dog size.
   */
  Size getSize() {
    return this.size;
  }

  /**
   * Get the dog's vaccination status.
   *
   * @return true if the dog is vaccinated; false otherwise.
   */
  boolean isVaccinated() {
    return this.vaccinated;
  }

  @Override
  public String toString() {
    String str;
    str = "Dog's name: " + getName() + "\n"
        + String.format("Dog's age: %d years\n", this.getAge())
        + String.format("Dog's weight: %.2f kg\n", this.getWeight())
        + "Dog's size: " + this.getSize() + "\n";
    if (this.isVaccinated()) {
      str = str + "Dog is vaccinated";
    } else {
      str = str + "Dog is not vaccinated";
    }
    return str;
  }
}
