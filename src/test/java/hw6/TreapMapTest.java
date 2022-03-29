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
  
  }
  
  @Test
  public void insertRightRotation() {
  
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