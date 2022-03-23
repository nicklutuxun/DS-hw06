package hw6.bst;

import hw6.OrderedMap;
import java.util.Iterator;

/**
 * Map implemented as an AVL Tree.
 *
 * @param <K> Type for keys.
 * @param <V> Type for values.
 */
public class AvlTreeMap<K extends Comparable<K>, V> implements OrderedMap<K, V> {

  /*** Do not change variable name of 'root'. ***/
  private Node<K, V> root;
  private int size;
  
  private Node<K, V> insert(Node<K, V> n, K k, V v) {
    if (n == null) {
      return new Node<>(k, v);
    }
    
    int cmp = k.compareTo(n.key);
    if (cmp < 0) {
      n.left = insert(n.left, k, v);
    } else if (cmp > 0) {
      n.right = insert(n.right, k, v);
    } else {
      throw new IllegalArgumentException("duplicate key " + k);
    }
    adjustHeight(n);
    n = rotate(n);
    adjustHeight(n);
    
    return n;
  }
  
  @Override
  public void insert(K k, V v) throws IllegalArgumentException {
    // TODO Implement Me!
    if (k == null) {
      throw new IllegalArgumentException("cannot handle null key");
    }
    root = insert(root, k, v);
    size++;
  }
  
  private int getBf(Node<K,V> n) {
    if (n == null) {
      return 0;
    }
    int leftHeight = n.left == null ? -1 : n.left.height;
    int rightHeight = n.right == null ? -1 : n.right.height;
    return leftHeight - rightHeight;
  }
  
  private Node<K,V> rotate(Node<K,V> n) {
    int bf = getBf(n);
    int leftBf = getBf(n.left);
    int rightBf = getBf(n.right);
    
    if (bf == -2) { // right heavy
      if (rightBf == -1) { // right heavy
        n = leftRotation(n);
      } else if (rightBf == 1) { // left heavy
        n = rightLeftRotation(n);
      }
    } else if (bf == 2) { // left heavy
      if (leftBf == -1) { // right heavy
        n = leftRightRotation(n);
      } else if (leftBf == 1) { // left heavy
        n = rightRotation(n);
      }
    }
    return n;
  }
  
  private Node<K,V> leftRotation(Node<K,V> n) {
    Node<K,V> child = n.right;
    n.right = child.left;
    child.left = n;
    return child;
  }
  
  private Node<K,V> rightRotation(Node<K,V> n) {
    Node<K,V> child = n.left;
    n.left = child.right;
    child.right = n;
    return child;
  }
  
  private Node<K,V> rightLeftRotation(Node<K,V> n) {
    n.right = rightRotation(n.right);
    return leftRotation(n);
  }
  
  private Node<K,V> leftRightRotation(Node<K,V> n) {
    n.left = leftRotation(n.left);
    return rightRotation(n);
  }
  
  private void adjustHeight(Node<K,V> n) {
    if (n == null) {
      return;
    }
    if (n.left == null && n.right == null) {
      n.height = 0;
    } else if (n.left == null) {
      n.height = n.right.height + 1;
    } else if (n.right == null) {
      n.height = n.left.height + 1;
    } else {
      n.height = n.left.height > n.right.height ? n.left.height + 1 : n.right.height + 1;
    }
  }

  @Override
  public V remove(K k) throws IllegalArgumentException {
    // TODO Implement Me!
    return null;
  }

  @Override
  public void put(K k, V v) throws IllegalArgumentException {
    // TODO Implement Me!
  }

  @Override
  public V get(K k) throws IllegalArgumentException {
    // TODO Implement Me!
    return null;
  }

  @Override
  public boolean has(K k) {
    // TODO Implement Me!
    return false;
  }

  @Override
  public int size() {
    // TODO Implement Me!
    return 0;
  }

  @Override
  public Iterator<K> iterator() {
    // TODO Implement Me!
    return null;
  }

  /*** Do not change this function's name or modify its code. ***/
  @Override
  public String toString() {
    return BinaryTreePrinter.printBinaryTree(root);
  }

  /**
   * Feel free to add whatever you want to the Node class (e.g. new fields).
   * Just avoid changing any existing names, deleting any existing variables,
   * or modifying the overriding methods.
   *
   * <p>Inner node class, each holds a key (which is what we sort the
   * BST by) as well as a value. We don't need a parent pointer as
   * long as we use recursive insert/remove helpers.</p>
   **/
  private static class Node<K, V> implements BinaryTreeNode {
    Node<K, V> left;
    Node<K, V> right;
    K key;
    V value;
    int height;

    // Constructor to make node creation easier to read.
    Node(K k, V v) {
      // left and right default to null
      key = k;
      value = v;
      height = 0;
    }

    @Override
    public String toString() {
      return key + ":" + value;
    }

    @Override
    public BinaryTreeNode getLeftChild() {
      return left;
    }

    @Override
    public BinaryTreeNode getRightChild() {
      return right;
    }
  }

}
