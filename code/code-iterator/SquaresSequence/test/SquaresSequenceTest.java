import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class SquaresSequenceTest {

  @Test
  public void testSquaresSequence() {
    SquaresSequence seq = new SquaresSequence(
            new BigInteger("" + 0)
            ,new BigInteger("1000000000")
    ,new BigInteger(""+100));

    BigInteger counter = new BigInteger(""+0);

    for (BigInteger result:seq) {
      assertEquals(counter.multiply(counter),result);
      counter = counter.add(new BigInteger(""+100));
    }
  }

}