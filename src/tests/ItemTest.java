/**
 * TCSS 305 Autumn 2022
 * Assignment 2
 */

package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * @author David Hohn
 * @version October 22
 */
class ItemTest {

    /**
     * item for the shopping cart to test constructor.
     */
    private Item myItem;
    
    /**
     * item for the shopping cart to test constructor.
     */
    private Item myItem2;
    

    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception {
        myItem = new Item("water", BigDecimal.valueOf(13), 10, BigDecimal.valueOf(20));
        myItem2 = new Item("Pepsi", BigDecimal.valueOf(5));
    }

    /**
     * Test method for {@link model.Item#Item(java.lang.String, java.math.BigDecimal)}.
     */
    @Test
    void testItemStringBigDecimal() {
        assertEquals("Pepsi", myItem2.getName());
        assertEquals(BigDecimal.valueOf(5), myItem2.getPrice());
    }

    /**
     * Test method for {@link model.Item#Item(java.lang.String,
     *  java.math.BigDecimal, int, java.math.BigDecimal)}.
     */
    @Test
    void testItemStringBigDecimalIntBigDecimal() {
        assertEquals("water", myItem.getName());
        assertEquals(BigDecimal.valueOf(13), myItem.getPrice());
        assertEquals(10, myItem.getBulkQuantity());
        assertEquals(BigDecimal.valueOf(20), myItem.getBulkPrice());
    }

    /**
     * Test method for {@link model.Item#toString()}.
     */
    @Test
    void testToString() {
        assertEquals("Pepsi, $5.00", myItem2.toString());
    }

}
