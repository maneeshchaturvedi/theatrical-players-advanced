package com.stackshala.theatricalplayers.calculation;

import com.stackshala.theatricalplayers.domain.Invoice;
import com.stackshala.theatricalplayers.domain.Performance;
import com.stackshala.theatricalplayers.domain.PlayType;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class StatementCalculator {
    public StatementResult calculate(Invoice invoice) {
        Objects.requireNonNull(invoice, "Invoice cannot be null");
        
        Money totalAmount = Money.zero(CurrencyUnit.USD);
        VolumeCredits totalCredits = VolumeCredits.zero();
        List<LineItem> lineItems = new ArrayList<>();
        
        for (Performance performance : invoice.getPerformances()) {
            PlayType playType = performance.getPlayType();
            PricingStrategy strategy = PricingStrategy.forPlayType(playType);
            
            Money amount = strategy.calculateAmount(performance);
            VolumeCredits credits = strategy.calculateCredits(performance);
            
            totalAmount = totalAmount.plus(amount);
            totalCredits = totalCredits.add(credits);
            
            LineItem lineItem = LineItem.of(performance, amount, credits);
            lineItems.add(lineItem);
        }
        
        return StatementResult.of(
            invoice.getCustomer(),
            lineItems,
            totalAmount,
            totalCredits
        );
    }
}
