/**
 * TCSS 305 Autumn 2022
 * Assignment 2
 */

package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;



/**
 * 
 * @author David Hohn
 * @version October 21
 *
 */

public class ShoppingCart {
    
    /**
     * constant for item map key.
     */
    private static final String ITEM_KEY = "item";
    
    /**
     * constant for quantity map key.
     */
    private static final String QUANTITY_KEY = "quantity";
    
    /**
     * constant for the price of member discount.
     */
    private static final double MEMBER_DISCOUNT = 0.9;
    
    /**
     * constant for the amount that has to purchased for membership to apply.
     */
    private static final int AMOUNT_FOR_MEMBERSHIP = 15;
    
    // Add whatever fields you think are necessary.

    /**
     * map to store item and price values.
     */
    private final Map<String, Map<String, Object>> myItemCollection = new HashMap<>();
    
    /**
     * field for checking if member.
     */
    private boolean myIsMember;

    public ShoppingCart() {
        this.myIsMember = false;
    }


    public void add(final Item theItem, final int theQuantity) {
        final Map<String, Object> itemObject = new HashMap<>();
        itemObject.put(QUANTITY_KEY, theQuantity);
        itemObject.put(ITEM_KEY, theItem);
        myItemCollection.put(theItem.getName(), (Map<String, Object>) itemObject);
    }


    public void setMembership(final boolean theMembership) {
        this.myIsMember = theMembership;
    }


    public BigDecimal calculateTotal() {
        BigDecimal priceTotal = BigDecimal.ZERO;
        if (myItemCollection.size() == 0) {
            return priceTotal;
        }
        for (String key : myItemCollection.keySet()) {
            final Map<String, Object> itemObject = myItemCollection.get(key);
            final Item item = (Item) itemObject.get(ITEM_KEY);
            final Integer quantity = (Integer) itemObject.get(QUANTITY_KEY);
            BigDecimal itemTotal = BigDecimal.valueOf(0);
            
            // calculating the bulk price
            if (item.isBulk()) {
                final int remainder = quantity % (int) item.getBulkQuantity();
                
                if (remainder == 0) {
                    final int bulkTimes = quantity / item.getBulkQuantity();
                    itemTotal = item.getBulkPrice().multiply(BigDecimal.valueOf(bulkTimes));
                    
                } else if (quantity < item.getBulkQuantity()) {
                    itemTotal = item.getPrice().multiply(BigDecimal.valueOf(quantity));
                    
                } else if (remainder > 0) {
                    final int bulkTimes = quantity / item.getBulkQuantity();
                    final BigDecimal remainderPrice = 
                            item.getPrice().multiply(BigDecimal.valueOf(remainder));
                    final BigDecimal bulkPricing = 
                            item.getBulkPrice().multiply(BigDecimal.valueOf(bulkTimes));
                    
                    itemTotal = bulkPricing.add(remainderPrice);
                    
                } else {
                    
                    final BigDecimal remainderPrice = 
                            item.getPrice().multiply(BigDecimal.valueOf(remainder));
                    
                    final BigDecimal bulkAmount = item.getBulkPrice();
                   
                    itemTotal = bulkAmount.add(remainderPrice);
                    
                }
            } else {
                itemTotal = item.getPrice().multiply(BigDecimal.valueOf(quantity));
            }
            
            
            
            priceTotal = priceTotal.add(itemTotal);
        }
        if (this.myIsMember && priceTotal.compareTo
                (BigDecimal.valueOf(AMOUNT_FOR_MEMBERSHIP)) > 0) {
            priceTotal = priceTotal.multiply(BigDecimal.valueOf(MEMBER_DISCOUNT));
        }
        return priceTotal.setScale(2, RoundingMode.HALF_UP);
    }
    
    public void clear() {
        myItemCollection.clear();
    }

}
