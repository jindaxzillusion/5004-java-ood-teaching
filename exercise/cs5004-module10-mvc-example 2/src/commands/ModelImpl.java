package commands;

import java.util.Random;

/**
 * Implementation of the Model Interface.
 */
public class ModelImpl implements Model {
//  private String input;
//  // when you start input is nothing
//  public ModelImpl() {
//    input = "";
//  }


  /**
   * Set a new String.
   *
   * @param text the String we want to use
   */
  @Override public String outputString() {
    String[] myArray = {
        "Yes",
        "No",
        "Maybe",
        "Outlook not so good",
        "Signs point to yes",
        "Cannot predict now",
        "Don't count on it",
        "Very doubtful",
        "It is certain",
        "As I see it, yes"
    };
    Random random = new Random();
    int index = random.nextInt(myArray.length);
    String randomString = myArray[index];
    return randomString;
  }
}