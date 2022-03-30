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
    
    String[] expected = new String[]{
        "2:2:-1727040520",
        "1:1:-1657178909 3:3:-1160871061",
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }
  
  @Test
  public void removeOneChildLeftRotation() {
    Map<Integer, Integer> map = new TreapMap<>(20);
    map.insert(2,2);
    map.insert(1,1);
    map.insert(3,3);
    map.remove(2);
    
    String[] expected = new String[]{
        "1:1:-1704868423",
        "null 3:3:884779003",
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }
  
  @Test
  public void removeOneChildRightRotation() {
    Map<Integer, Integer> map = new TreapMap<>(20);
    map.insert(2,2);
    map.insert(3,3);
    map.insert(1,1);
    map.remove(2);
    
    String[] expected = new String[]{
        "3:3:-1704868423",
        "1:1:884779003 null",
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }
  
  @Test
  public void removeTwoChildrenLeftRightRotation() {
    Map<Integer, Integer> map = new TreapMap<>(20);
    map.insert(1,1);
    map.insert(2,2);
    map.insert(3,3);
    map.remove(2);
    
    String[] expected = new String[]{
        "1:1:-1150867590",
        "null 3:3:884779003",
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }
  
  @Test
  public void removeTwoChildrenRightLeftRotation() {
    Map<Integer, Integer> map = new TreapMap<>(15);
    map.insert(2,2);
    map.insert(1,1);
    map.insert(3,3);
    map.remove(2);
    
    String[] expected = new String[]{
        "1:1:-898526952",
        "null 3:3:453225476",
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }
  
  @Test
  public void removeLeafNoRotation() {
    Map<Integer, Integer> map = new TreapMap<>(15);
    map.insert(2,2);
    map.insert(1,1);
    map.insert(3,3);
    map.remove(1);
    
    String[] expected = new String[]{
        "2:2:-1159716814",
        "null 3:3:453225476",
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }
  
  @Test
  public void insertNoRotation() {
    Map<Integer, Integer> map = new TreapMap<>(25);
    map.insert(2,2);
    map.insert(1,1);
    map.insert(3,3);
    
    String[] expected = new String[]{
        "2:2:-1152791334",
        "1:1:-222412840 3:3:230749894",
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }
}