package hw6.bst;

import hw6.OrderedMap;
import java.util.Iterator;
import java.util.Stack;

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
    
    if (bf < -1) { // right heavy
      if (rightBf <= 0) { // right heavy
        n = leftRotation(n);
      } else { // left heavy
        n = rightLeftRotation(n);
      }
    } else if (bf > 1) { // left heavy
      if (leftBf >= 0) { // right heavy
        n = rightRotation(n);
      } else { // left heavy
        n = leftRightRotation(n);
      }
    }
    return n;
  }
  
  private Node<K,V> leftRotation(Node<K,V> n) {
    Node<K,V> child = n.right;
    n.right = child.left;
    child.left = n;
    adjustHeight(n);
    return child;
  }
  
  private Node<K,V> rightRotation(Node<K,V> n) {
    Node<K,V> child = n.left;
    n.left = child.right;
    child.right = n;
    adjustHeight(n);
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
    Node<K, V> node = findForSure(k);
    V value = node.value;
    root = remove(root, node);
    size--;
    return value;
  }
  
  // Remove node with given key from subtree rooted at given node;
  // Return changed subtree with given key missing.
  private Node<K, V> remove(Node<K, V> subtreeRoot, Node<K, V> toRemove) {
    int cmp = subtreeRoot.key.compareTo(toRemove.key);
    if (cmp == 0) {
      subtreeRoot = remove(subtreeRoot);
    } else if (cmp > 0) {
      subtreeRoot.left = remove(subtreeRoot.left, toRemove);
    } else {
      subtreeRoot.right = remove(subtreeRoot.right, toRemove);
    }
    adjustHeight(subtreeRoot);
    if (subtreeRoot != null) {
      subtreeRoot = rotate(subtreeRoot);
    }
    adjustHeight(subtreeRoot);
    
    return subtreeRoot;
  }
  
  // Remove given node and return the remaining tree (structural change).
  private Node<K, V> remove(Node<K, V> node) {
    // Easy if the node has 0 or 1 child.
    if (node.right == null) {
      return node.left;
    } else if (node.left == null) {
      return node.right;
    }
    
    // If it has two children, find the predecessor (max in left subtree),
    Node<K, V> toReplaceWith = max(node);
    // then copy its data to the given node (value change),
    node.key = toReplaceWith.key;
    node.value = toReplaceWith.value;
    // then remove the predecessor node (structural change).
    node.left = remove(node.left, toReplaceWith);
    
    return node;
  }
  
  // Return a node with maximum key in subtree rooted at given node.
  private Node<K, V> max(Node<K, V> node) {
    Node<K, V> curr = node.left;
    while (curr.right != null) {
      curr = curr.right;
    }
    return curr;
  }

  @Override
  public void put(K k, V v) throws IllegalArgumentException {
    Node<K, V> n = findForSure(k);
    n.value = v;
  }

  @Override
  public V get(K k) throws IllegalArgumentException {
    Node<K, V> n = findForSure(k);
    return n.value;
  }
  
  @Override
  public boolean has(K k) {
    if (k == null) {
      return false;
    }
    return find(k) != null;
  }
  
  // Return node for given key,
  // throw an exception if the key is not in the tree.
  private Node<K, V> findForSure(K k) {
    Node<K, V> n = find(k);
    if (n == null) {
      throw new IllegalArgumentException("cannot find key " + k);
    }
    return n;
  }
  
  private Node<K, V> find(K k) {
    if (k == null) {
      throw new IllegalArgumentException("cannot handle null key");
    }
    Node<K, V> n = root;
    while (n != null) {
      int cmp = k.compareTo(n.key);
      if (cmp < 0) {
        n = n.left;
      } else if (cmp > 0) {
        n = n.right;
      } else {
        return n;
      }
    }
    return null;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public Iterator<K> iterator() {
    return new InorderIterator();
  }
  
  // Iterative in-order traversal over the keys
  private class InorderIterator implements Iterator<K> {
    private final Stack<Node<K, V>> stack;
    
    InorderIterator() {
      stack = new Stack<>();
      pushLeft(root);
    }
    
    private void pushLeft(Node<K, V> curr) {
      while (curr != null) {
        stack.push(curr);
        curr = curr.left;
      }
    }
    
    @Override
    public boolean hasNext() {
      return !stack.isEmpty();
    }
    
    @Override
    public K next() {
      Node<K, V> top = stack.pop();
      pushLeft(top.right);
      return top.key;
    }
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
