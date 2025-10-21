package com.stackshala.theatricalplayers.calculation;

import com.stackshala.theatricalplayers.domain.Performance;
import org.joda.money.Money;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class StatementResult {
    private final String customer;
    private final List<LineItem> lineItems;
    private final Money totalAmount;
    private final VolumeCredits totalCredits;
    
    private StatementResult(
            String customer,
            List<LineItem> lineItems,
            Money totalAmount,
            VolumeCredits totalCredits) {
        
        this.customer = Objects.requireNonNull(customer, "Customer cannot be null");
        this.lineItems = Collections.unmodifiableList(new ArrayList<>(lineItems));
        this.totalAmount = Objects.requireNonNull(totalAmount, "Total amount cannot be null");
        this.totalCredits = Objects.requireNonNull(totalCredits, "Total credits cannot be null");
    }
    
    public static StatementResult of(
            String customer,
            List<LineItem> lineItems,
            Money totalAmount,
            VolumeCredits totalCredits) {
        return new StatementResult(customer, lineItems, totalAmount, totalCredits);
    }
    
    public String getCustomer() {
        return customer;
    }
    
    public List<LineItem> getLineItems() {
        return lineItems;
    }
    
    public Money getTotalAmount() {
        return totalAmount;
    }
    
    public VolumeCredits getTotalCredits() {
        return totalCredits;
    }
    
    public int getLineItemCount() {
        return lineItems.size();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatementResult that = (StatementResult) o;
        return customer.equals(that.customer) &&
               lineItems.equals(that.lineItems) &&
               totalAmount.equals(that.totalAmount) &&
               totalCredits.equals(that.totalCredits);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(customer, lineItems, totalAmount, totalCredits);
    }
    
    @Override
    public String toString() {
        return String.format(
            "StatementResult{customer='%s', items=%d, total=%s, credits=%s}",
            customer, lineItems.size(), totalAmount, totalCredits
        );
    }
}
