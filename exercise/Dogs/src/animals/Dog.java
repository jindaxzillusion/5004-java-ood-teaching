package animals;

public class Dog {
  private final String name;
  private final int age;
  private final int weight;
  private final String size;
  private boolean gotVaccines;
  /**
   * This is the constructor that builds a dog.
   *
   * @param name        name of dog
   * @param age         age of dog
   * @param weight      weight of dog
   * @param size        size of dog
   * @param gotVaccines if the dog get vaccined
   * @throws IllegalArgumentException when dogs age and weight is smaller or equal to 0
   */
  public Dog(String name, int age, int weight, String size, boolean gotVaccines) throws IllegalArgumentException {
    if (age <= 0){
      throw new IllegalArgumentException("Age must be greater than or equal to 0.");
    }
    if (weight <= 0){
      throw new IllegalArgumentException("weight must be greater than or equal to 0.");
    }
    this.name = name;
    this.age = age;
    this.weight = weight;
    this.size = size;
    this.gotVaccines = gotVaccines;
  }
  public String getName() {
    return this.name;
  }
  public int getAge() {
    return this.age;
  }
  public int getWeight() {
    return this.weight;
  }
  public String getSize() {
    return this.size;
  }
  public boolean getVaccines() {
    return this.gotVaccines;
  }
  @Override
  public String toString() {
    String str;
    str = "Name: " + this.name + "\n" +
          "Age: " + this.age + "\n" +
          "Weight: " + this.weight + "\n" +
          "Size: " + this.size + "\n" +
          "Vaccines Status: " + this.gotVaccines + "\n";
    return str;
  }
}
