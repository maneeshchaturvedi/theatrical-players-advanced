package com.stackshala.theatricalplayers.calculation;

import com.stackshala.theatricalplayers.domain.Performance;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

public final class TragedyPricing implements PricingStrategy {
    private static final Money BASE_AMOUNT = Money.of(CurrencyUnit.USD, 400);
    private static final int AUDIENCE_THRESHOLD = 30;
    private static final Money AMOUNT_PER_EXTRA_ATTENDEE = Money.of(CurrencyUnit.USD, 10);
    private static final int CREDITS_THRESHOLD = 30;
    
    @Override
    public Money calculateAmount(Performance performance) {
        Money amount = BASE_AMOUNT;
        
        int audience = performance.getAudienceSize();
        if (audience > AUDIENCE_THRESHOLD) {
            int extraAttendees = audience - AUDIENCE_THRESHOLD;
            Money extraCharge = AMOUNT_PER_EXTRA_ATTENDEE.multipliedBy(extraAttendees);
            amount = amount.plus(extraCharge);
        }
        
        return amount;
    }
    
    @Override
    public VolumeCredits calculateCredits(Performance performance) {
        int audience = performance.getAudienceSize();
        int credits = Math.max(audience - CREDITS_THRESHOLD, 0);
        return VolumeCredits.of(credits);
    }
}
