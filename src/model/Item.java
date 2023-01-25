/**
 * TCSS 305 Autumn 2022
 * Assignment 2
 */

package model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;


/**
 * 
 * @author David Hohn
 * @version October 21
 *
 */

public final class Item {
    
    // Add whatever fields you think are necessary.
    
    /**
     * for item names.
     */
    private String myName;
    
    /**
     * for price.
     */
    private BigDecimal myPrice;
    
    /**
     * for bulk amount.
     */
    private int myBulkQuantity;
    
    /**
     * for bulk price.
     */
    private BigDecimal myBulkPrice;
    
    /**
     * Item constructor.
     */
    public Item(final String theName, final BigDecimal thePrice) {
        this.myName = theName;
        this.myPrice = thePrice;
        this.myBulkQuantity = 0;
    }

    /**
     * Item constructor.
     */
    public Item(final String theName, final BigDecimal thePrice,
                final int theBulkQuantity, final BigDecimal theBulkPrice) {
        this.myName = theName;
        this.myPrice = thePrice;
        this.myBulkQuantity = theBulkQuantity;
        this.myBulkPrice = theBulkPrice;
    }


    public String getName() {
        
        return this.myName;
    }
    
    public BigDecimal getPrice() {
        
        return this.myPrice;
    }
    

    public int getBulkQuantity() {
        
        return this.myBulkQuantity;
    }
    

    public BigDecimal getBulkPrice() {
        
        return this.myBulkPrice;
    }

    
    public boolean isBulk() {
        
        return this.myBulkQuantity > 0;
    }

    @Override
    public String toString() {
        final StringBuilder item = new StringBuilder();
        final NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        
        item.append(this.myName + ", ");
        item.append(nf.format(this.myPrice));
        
        if (this.isBulk()) {
            item.append(" (" + this.myBulkQuantity + " for " 
                + nf.format(this.myBulkPrice) + ")");
        }
        
        return item.toString();
    }

    @Override
    public boolean equals(final Object theOther) {
        if (this == theOther) {
            return true;
        }
        if (theOther == null) {
            return false;
        }
        if (getClass() != theOther.getClass()) {
            return false;
        }
        final Item other = (Item) theOther;
        return Objects.equals(myBulkPrice, other.myBulkPrice) 
                && myBulkQuantity == other.myBulkQuantity
                && Objects.equals(myName, other.myName) 
                && Objects.equals(myPrice, other.myPrice);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(myBulkPrice, myBulkQuantity, myName, myPrice);
    }

}
