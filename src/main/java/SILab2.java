import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class SILab2StatementCoverageTest {

    private Item createItem(String name, int price, double discount, int quantity) {
        return new Item(name, quantity, price, discount);
    }

    @Test
    public void testNullItemList() {
        RuntimeException ex = assertThrows(RuntimeException.class, () ->
            SILab2.checkCart(null, "1234567890123456")
        );
        assertEquals("allItems list can't be null!", ex.getMessage());
    }

    @Test
    public void testEmptyItemList() {
        List<Item> items = List.of();
        double sum = SILab2.checkCart(items, "1234567890123456");
        assertEquals(0.0, sum);
    }

    @Test
    public void testValidItemNoDiscountNoPenalty() {
        Item item = createItem("item", 300, 0, 10); // price = 300, discount = 0, quantity = 10 (none > limit)
        List<Item> items = List.of(item);
        double sum = SILab2.checkCart(items, "1234567890123456");
        assertEquals(300 * 10, sum);
    }

    @Test
    public void testValidItemWithPenalty() {
        Item item = createItem("item", 301, 0, 10); // price > 300
        List<Item> items = List.of(item);
        double sum = SILab2.checkCart(items, "1234567890123456");
        assertEquals(301 * 10 - 30, sum);
    }

    @Test
    public void testItemWithNullName() {
        Item item = createItem(null, 100, 0, 1);
        List<Item> items = List.of(item);
        RuntimeException ex = assertThrows(RuntimeException.class, () ->
            SILab2.checkCart(items, "1234567890123456")
        );
        assertEquals("Invalid item!", ex.getMessage());
    }

    @Test
    public void testInvalidCardNumberLength() {
        Item item = createItem("item", 100, 0, 1);
        List<Item> items = List.of(item);
        RuntimeException ex = assertThrows(RuntimeException.class, () ->
            SILab2.checkCart(items, "12345")
        );
        assertEquals("Invalid card number!", ex.getMessage());
    }

    @Test
    public void testInvalidCardNumberCharacter() {
        Item item = createItem("item", 100, 0, 1);
        List<Item> items = List.of(item);
        RuntimeException ex = assertThrows(RuntimeException.class, () ->
            SILab2.checkCart(items, "12345678901234a6")
        );
        assertEquals("Invalid character in card number!", ex.getMessage());
    }
}
