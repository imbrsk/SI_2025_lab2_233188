import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class SILab2Test {

    @Test
    public void testNullItemList() {
        RuntimeException ex = assertThrows(RuntimeException.class, () ->
            SILab2.checkCart(null, "1234567890123456")
        );
        assertEquals("allItems list can't be null!", ex.getMessage());
    }

    @Test
    public void testNullItemName() {
        Item item = new Item(null, 1, 100, 0);
        List<Item> items = List.of(item);
        RuntimeException ex = assertThrows(RuntimeException.class, () ->
            SILab2.checkCart(items, "1234567890123456")
        );
        assertEquals("Invalid item!", ex.getMessage());
    }

    @Test
    public void testValidDiscountedItem() {
        Item item = new Item("item1", 5, 350, 0.1);
        List<Item> items = List.of(item);
        double result = SILab2.checkCart(items, "1234567890123456");
        assertEquals(1545.0, result);
    }

    @Test
    public void testInvalidCardCharacter() {
        Item item = new Item("item2", 1, 200, 0);
        List<Item> items = List.of(item);
        RuntimeException ex = assertThrows(RuntimeException.class, () ->
            SILab2.checkCart(items, "12345678901234a6")
        );
        assertEquals("Invalid character in card number!", ex.getMessage());
    }
}
