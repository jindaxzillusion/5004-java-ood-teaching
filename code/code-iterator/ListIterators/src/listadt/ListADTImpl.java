package listadt;

import java.util.Iterator;
import java.util.function.Function;



/**
 * This is the implementation of a generic list. Specifically it implements
 * the listadt.ListADT interface. It represents a singly-linked list of data
 * elements, where every data node stores one piece of data and a reference
 * to the advance item in the list. The list ends with a sentinel empty node.
 */
public class ListADTImpl<T> implements ListADT<T> {
  private GenericListADTNode<T> head;

  public ListADTImpl() {
    head = new GenericEmptyNode();
  }

  //a protected constructor that is used internally (see map)
  protected ListADTImpl(GenericListADTNode<T> head) {
    this.head = head;
  }

  @Override
  public void addFront(T b) {
    head = head.addFront(b);
  }

  @Override
  public void addBack(T b) {
    head = head.addBack(b);
  }

  @Override
  public void add(int index, T b) {
    head = head.add(index,b);
  }

  @Override
  public int getSize() {
    return head.count();
  }

  @Override
  public void remove(T b) {
    head = head.remove(b);
  }

  @Override
  public T get(int index) throws IllegalArgumentException{
    if ((index>=0) && (index<getSize())) {
      return head.get(index);
    } else throw new IllegalArgumentException("Invalid index");

  }

  @Override
  public <R> ListADT<R> map(Function<T,R> converter) {
    return new ListADTImpl(head.map(converter));
  }

  @Override
  public String toString() {
    return "("+head.toString()+")";
  }

  @Override
  public Iterator<T> iterator() {
    return new ListADTIterator(head);
  }

  private class ListADTIterator<T> implements Iterator<T> {

    private GenericListADTNode<T> current;
    private ListADTIterator(GenericListADTNode<T> head) {
      current = head;
    }
    @Override
    public boolean hasNext() {
      return current.canAdvance();
    }

    @Override
    public T next() {
      T element = current.get(0);
      current=current.advance();
      return element;
    }
  }
}
