/*
这个文件是一个泛型树的实现，其中定义了一个GroupNode类，继承了AbstractTreeNode类，表示一个具有分组特性的树节点。GroupNode类有以下几个方法：

addChild(Predicate<T> identifier, TreeNode<T> child)：向树节点添加子节点，当identifier条件满足时添加child节点。如果identifier条件不满足，则递归调用该方法，直到找到符合条件的节点，然后添加child节点到该节点下。

count(Predicate<T> condition)：统计符合条件的节点数，如果condition条件满足当前节点，则递归调用该方法统计子节点中符合条件的节点数。

toList()：将树节点及其所有子节点转换成List列表。

map(Function<T,R> transform)：根据给定的转换函数transform对树节点进行转换，返回一个新的树节点，其中节点数据根据transform函数进行转换。

reduce(T initialValue, BiFunction<T,T,T> combiner)：根据给定的初始化值和组合函数对树节点数据进行归约操作，返回最终的结果。该方法首先对当前节点和子节点进行reduce操作，然后使用给定的组合函数将其结果进行合并。

这个文件提供了一种通用的树的实现方式，可以用来构建具有分组特性的数据结构。
*/

package generictree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class GroupNode<T> extends AbstractTreeNode<T> {

  private List<TreeNode<T>> children;

  public GroupNode(T data) {
    super(data);
    this.children = new LinkedList<TreeNode<T>>();

  }
  @Override
  public TreeNode<T> addChild(Predicate<T> identifier, TreeNode<T> child) {
    if (identifier.test(this.data)) {
      this.children.add(child);
      return this;
    }
    for (int i=0;i<this.children.size();i++) {
      this.children.set(
              i,
              this.children.get(i).addChild(identifier,child));
    }
    return this;
  }

  @Override
  public int count(Predicate<T> condition) {
    Stream<TreeNode<T>> stream = this.children.stream();
    return stream.mapToInt(b -> b.count(condition)).sum()
           + super.count(condition);
  }

  @Override
  public List<T> toList() {
    List<T> result = new ArrayList<T>();
    result.add(this.data);
    for (TreeNode<T> child: children) {
      result.addAll(child.toList());
    }
    return result;
  }

  @Override
  public <R> TreeNode<R> map(Function<T,R> transform) {
    GroupNode<R> newNode = new GroupNode<R>(transform.apply(this.data));
    for (TreeNode<T> child:children) {
      newNode.children.add(child.map(transform));
    }
    return newNode;
  }

  @Override
  public T reduce(T initialValue, BiFunction<T,T,T> combiner) {
    T result = this.data;
    for (TreeNode<T> child:children) {
      result = child.reduce(result,combiner);
    }
    return combiner.apply(initialValue,result);
  }
}
