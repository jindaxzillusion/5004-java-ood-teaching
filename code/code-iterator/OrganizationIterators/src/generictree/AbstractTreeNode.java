package generictree;

import java.util.function.Predicate;

public abstract class AbstractTreeNode<T> implements TreeNode<T> {
  protected T data;

  public AbstractTreeNode(T data) {
    this.data = data;
  }

  @Override
  public int count(Predicate<T> condition) {
    if (condition.test(this.data)) {
      return 1;
    }
    return 0;
  }

  public T getData() {
    return data;
  }

  protected abstract <R> TreeNode<R> createNode(R data);
}
