import java.util.LinkedList;
import java.util.function.Function;

public class LinkedListADTImpl<T> implements ListADT<T> {
  private LinkedList<T> list;

  public LinkedListADTImpl() {
    list = new LinkedList<T>();
  }

  @Override
  public void addFront(T b) {
    list.addFirst(b);
  }

  @Override
  public void addBack(T b) {
    list.addLast(b);
  }

  @Override
  public void add(int index, T b) {
    list.add(index, b);
  }

  @Override
  public int getSize() {
    return list.size();
  }

  @Override
  public void remove(T b) {
    list.remove(b);
  }

  @Override
  public T get(int index) throws IllegalArgumentException {
    if ((index >= 0) && (index < getSize())) {
      return list.get(index);
    } else {
      throw new IllegalArgumentException("Invalid index");
    }
  }

  @Override
  public <R> ListADT<R> map(Function<T, R> converter) {
    LinkedListADTImpl<R> result = new LinkedListADTImpl<R>();
    for (T elem : list) {
      result.addBack(converter.apply(elem));
    }
    return result;
  }

  @Override
  public String toString() {
    return list.toString();
  }
}