package hw6;

import hw6.bst.AvlTreeMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * In addition to the tests in BinarySearchTreeMapTest (and in OrderedMapTest & MapTest),
 * we add tests specific to AVL Tree.
 */
@SuppressWarnings("All")
public class AvlTreeMapTest extends BinarySearchTreeMapTest {

  @Override
  protected Map<String, String> createMap() {
    return new AvlTreeMap<>();
  }

  @Test
  public void insertLeftRotation() {
    map.insert("1", "a");
    // System.out.println(avl.toString());
    // must print
    /*
        1:a
     */

    map.insert("2", "b");
    // System.out.println(avl.toString());
    // must print
    /*
        1:a,
        null 2:b
     */

    map.insert("3", "c"); // it must do a left rotation here!
    // System.out.println(avl.toString());
    // must print
    /*
        2:b,
        1:a 3:c
     */

    String[] expected = new String[]{
        "2:b",
        "1:a 3:c"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  // TODO Add more tests
  @Test
  public void insertRightRotation() {
    map.insert("3", "c");
    map.insert("2", "b");
    map.insert("1", "a"); // it must do a right rotation here!

    String[] expected = new String[]{
        "2:b",
        "1:a 3:c"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }
  
  @Test
  public void insertRightLeftRotation() {
    map.insert("1", "a");
    map.insert("3", "c");
    map.insert("2", "b"); // it must do a right-left rotation here!
    
    String[] expected = new String[]{
        "2:b",
        "1:a 3:c"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }
  
  @Test
  public void insertLeftRightRotation() {
    map.insert("3", "c");
    map.insert("1", "a");
    map.insert("2", "b"); // it must do a left-right rotation here!
    
    String[] expected = new String[]{
        "2:b",
        "1:a 3:c"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }
  
  @Test
  public void insertNoRotation() {
    map.insert("2", "b");
    map.insert("1", "a");
    map.insert("3", "c"); // no rotation here!
 
    String[] expected = new String[]{
        "2:b",
        "1:a 3:c"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }
  
  @Test
  public void removeLeafLeftRotation() {
    map.insert("1", "1");
    map.insert("2", "2");
    map.insert("3", "3");
    map.insert("4", "4");
    map.remove("1");
  
    String[] expected = new String[]{
        "3:3",
        "2:2 4:4"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }
  
  @Test
  public void removeLeafRightRotation() {
    map.insert("2", "2");
    map.insert("3", "3");
    map.insert("4", "4");
    map.insert("1", "1");
    map.remove("4");
  
    String[] expected = new String[]{
        "2:2",
        "1:1 3:3"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }
  
  @Test
  public void removeLeafLeftRightRotation() {
    map.insert("3", "3");
    map.insert("1", "1");
    map.insert("4", "4");
    map.insert("2", "2");
    map.remove("4");
  
    String[] expected = new String[]{
        "2:2",
        "1:1 3:3"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }
  
  @Test
  public void removeLeafRightLeftRotation() {
    map.insert("2", "2");
    map.insert("1", "1");
    map.insert("4", "4");
    map.insert("3", "3");
    map.remove("1");
  
    String[] expected = new String[]{
        "3:3",
        "2:2 4:4"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }
  
  @Test
  public void removeOneChildLeftRotation() {
    map.insert("3", "3");
    map.insert("2", "2");
    map.insert("5", "5");
    map.insert("1", "1");
    map.insert("4", "4");
    map.insert("6", "6");
    map.insert("7", "7");
    map.remove("2");
  
    String[] expected = new String[]{
        "5:5",
        "3:3 6:6",
        "1:1 4:4 null 7:7"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }
  
  @Test
  public void removeOneChildRightRotation() {
    map.insert("5", "5");
    map.insert("3", "3");
    map.insert("6", "6");
    map.insert("2", "2");
    map.insert("4", "4");
    map.insert("7", "7");
    map.insert("1", "1");
    map.remove("6");
  
    String[] expected = new String[]{
        "3:3",
        "2:2 5:5",
        "1:1 null 4:4 7:7"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }
  
  @Test
  public void removeOneChildLeftRightRotation() {
    map.insert("5", "5");
    map.insert("2", "2");
    map.insert("6", "6");
    map.insert("1", "1");
    map.insert("3", "3");
    map.insert("7", "7");
    map.insert("4", "4");
    map.remove("6");
  
    String[] expected = new String[]{
        "3:3",
        "2:2 5:5",
        "1:1 null 4:4 7:7"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }
  
  @Test
  public void removeOneChildRightLeftRotation() {
    map.insert("3", "3");
    map.insert("2", "2");
    map.insert("6", "6");
    map.insert("1", "1");
    map.insert("5", "5");
    map.insert("7", "7");
    map.insert("4", "4");
    map.remove("2");
  
    String[] expected = new String[]{
        "5:5",
        "3:3 6:6",
        "1:1 4:4 null 7:7"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }
  
  @Test
  public void removeTwoChildrenLeftRotation() {
    map.insert("2", "2");
    map.insert("1", "1");
    map.insert("3", "3");
    map.insert("4", "4");
    map.remove("2");
  
    String[] expected = new String[]{
        "3:3",
        "1:1 4:4"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }
  
  @Test
  public void removeTwoChildrenRightRotation() {
    map.insert("5","5");
    map.insert("3","3");
    map.insert("6","6");
    map.insert("2","2");
    map.insert("4","4");
    map.insert("7","7");
    map.insert("1","1");
    map.remove("5");
    String[] expected = new String[]{
        "4:4",
        "2:2 6:6",
        "1:1 3:3 null 7:7"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }
  
  @Test
  public void removeTwoChildrenLeftRightRotation() {
    map.insert("5","5");
    map.insert("3","3");
    map.insert("6","6");
    map.insert("1","1");
    map.insert("4","4");
    map.insert("7","7");
    map.insert("2","2");
    map.remove("5");
    String[] expected = new String[]{
        "4:4",
        "2:2 6:6",
        "1:1 3:3 null 7:7"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }
  
  @Test
  public void removeTwoChildrenRightLeftRotation() {
    map.insert("1", "1");
    map.insert("2", "2");
    map.insert("4", "4");
    map.insert("3", "3");
    map.remove("2");
  
    String[] expected = new String[]{
        "3:3",
        "1:1 4:4"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }
  
  @Test
  public void removeLeafNoRotation() {
    map.insert("1", "1");
    map.insert("2", "2");
    map.insert("3", "3");
    map.insert("4", "4");
    map.remove("4");
  
    String[] expected = new String[]{
        "2:2",
        "1:1 3:3"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }
  
  @Test
  public void removeOneChildNoRotation() {
    map.insert("1", "1");
    map.insert("2", "2");
    map.insert("3", "3");
    map.insert("4", "4");
    map.remove("3");
  
    String[] expected = new String[]{
        "2:2",
        "1:1 4:4"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }
  
  @Test
  public void removeTwoChildrenNoRotation() {
    map.insert("1", "1");
    map.insert("2", "2");
    map.insert("3", "3");
    map.insert("4", "4");
    map.insert("5", "5");
    map.insert("6", "6");
    map.insert("7", "7");
    map.remove("4");
  
    String[] expected = new String[]{
        "3:3",
        "2:2 6:6",
        "1:1 null 5:5 7:7"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }
  
  @Test
  public void ExerciseFromWebpage() {
    map.insert("15", "15");
    map.insert("20", "20");
    map.insert("24", "24");
    map.insert("10", "10");
    map.insert("13", "13");
    map.insert("07", "07");
    map.insert("30", "30");
    map.insert("36", "36");
    map.insert("25", "25");
    map.remove("24");
    map.remove("20");
    String[] expected = new String[]{
        "13:13",
        "10:10 30:30",
        "07:07 null 15:15 36:36",
        "null null null null null 25:25 null null"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }
}
