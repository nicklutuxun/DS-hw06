package hw6;

import hw6.bst.TreapMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * In addition to the tests in BinarySearchTreeMapTest (and in OrderedMapTest & MapTest),
 * we add tests specific to Treap.
 */
@SuppressWarnings("All")
public class TreapMapTest extends BinarySearchTreeMapTest {

  @Override
  protected Map<String, String> createMap() {
    return new TreapMap<>();
  }

  // TODO Add tests
  //  (think about how you might write tests while randomness is involved in TreapMap implementation!)
  
  @Test
  public void insertLeftRotation() {
    Map<Integer, Integer> map = new TreapMap<>(50);
    map.insert(1,1);
    map.insert(2,2);
    map.insert(3,3);
    System.out.println(map);
    String[] expected = new String[]{
        "2:2:-1727040520",
        "1:1:-1160871061 3:3:-1657178909",
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }
  
  @Test
  public void insertRightRotation() {
    Map<Integer, Integer> map = new TreapMap<>(50);
    map.insert(3,3);
    map.insert(2,2);
    map.insert(1,1);
    System.out.println(map);
    String[] expected = new String[]{
        "2:2:-1727040520",
        "1:1:-1657178909 3:3:-1160871061",
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }
  
  @Test
  public void removeOneChildLeftRotation() {
  
  }
  
  @Test
  public void removeOneChildRightRotation() {
  
  }
  
  @Test
  public void removeTwoChildrenLeftRotation() {
  
  }
  
  @Test
  public void removeTwoChildrenRightRotation() {
  
  }
  
  @Test
  public void removeLeafNoRotation() {
  
  }
  
  @Test
  public void insertNoRotation() {
  
  }
}