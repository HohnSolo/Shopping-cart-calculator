/**
 * TCSS 305 Autumn 2022
 * Assignment 2
 */

package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import model.Item;
import model.ShoppingCart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * @author David Hohn
 * @version October 21
 *
 */
class ShoppingCartTest {
    
    /**
     * non bulk item.
     */
    private static final String ITEM_NO_BULK = "water";
    
    /**
     * bulk item.
     */
    private static final String ITEM_BULK = "toothpaste";
    
    /**
     * Item price.
     */
    private static final BigDecimal ITEM_PRICE1 = BigDecimal.valueOf(5.94);
    
    /**
     * Item price.
     */
    private static final BigDecimal ITEM_PRICE2 = BigDecimal.valueOf(2.83);
    
    /**
     * amount to qualify for bulk price.
     */
    private static final int BULK_AMOUNT = 5;
    
    /**
     * the bulk price.
     */
    private static final BigDecimal BULK_PRICE = BigDecimal.valueOf(10);
        
    /**
     * shopping cart for testing.
     */
    private ShoppingCart myShoppingCart;
    
    /**
     * item for the shopping cart.
     */
    private Item myItem;
    
    /**
     * price for bulk amount.
     */
    private Item myItemBulk;


    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception {
        myShoppingCart = new ShoppingCart();
        myItem = new Item(ITEM_NO_BULK, ITEM_PRICE1);
        myItemBulk = new Item(ITEM_BULK, ITEM_PRICE2, BULK_AMOUNT, BULK_PRICE);
    }


    /**
     * Test method for {@link model.ShoppingCart#setMembership(boolean)}.
     */
    @Test
    void testSetMembership() {
        final BigDecimal itemTotal = BigDecimal.valueOf(21.38);
        myShoppingCart.add(myItem, 4);
        myShoppingCart.setMembership(true);
        assertEquals(itemTotal, myShoppingCart.calculateTotal());
    }

    /**
     * Test method for {@link model.ShoppingCart#calculateTotal()}.
     */
    @Test
    void testCalculateTotal() {
        final BigDecimal itemTotal = BigDecimal.valueOf(23.76);
        myShoppingCart.add(myItem, 4);
        assertEquals(itemTotal, myShoppingCart.calculateTotal());
    }
    
    /**
     * Test method for {@link model.ShoppingCart#calculateTotal()}.
     */
    @Test
    void testCalculateTotalBulk() {
        final BigDecimal itemTotal = BigDecimal.valueOf(15.66);
        myShoppingCart.add(myItemBulk, 7);
        assertEquals(itemTotal, myShoppingCart.calculateTotal());
    }
    

    /**
     * Test method for {@link model.ShoppingCart#clear()}.
     */
    @Test
    void testClear() {
        final BigDecimal itemTotal = BigDecimal.ZERO;
        myShoppingCart.add(myItem, 4);
        myShoppingCart.clear();
        assertEquals(itemTotal, myShoppingCart.calculateTotal());
    }

}
