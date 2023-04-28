import java.math.BigInteger;
import java.util.Iterator;

public class SquaresSequence implements Iterable<BigInteger> {
  private BigInteger start,end,increment;
  public SquaresSequence(BigInteger start,BigInteger end,BigInteger increment) {
    this.start = start;
    this.end = end;
    this.increment = increment;
  }

  public Iterator<BigInteger> iterator() {
    return new SequenceIterator(start,end,increment);
  }

  private class SequenceIterator implements Iterator<BigInteger> {
    private BigInteger counter;
    private BigInteger end;
    private BigInteger increment;

    private SequenceIterator(BigInteger start,BigInteger end,
                             BigInteger increment) {
      counter = start;
      this.end = end;
      this.increment = increment;
    }

    @Override
    public BigInteger next() {
      BigInteger result = counter.multiply(counter);
      counter = counter.add(increment);
      return result;
    }

    @Override
    public boolean hasNext() {
      return counter.compareTo(end)<=0;
    }
  }
}
