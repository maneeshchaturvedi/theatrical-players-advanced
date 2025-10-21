package com.stackshala.theatricalplayers.calculation;

import com.stackshala.theatricalplayers.domain.Performance;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

public final class ComedyPricing implements PricingStrategy {
    private static final Money BASE_AMOUNT = Money.of(CurrencyUnit.USD, 300);
    private static final int LARGE_AUDIENCE_THRESHOLD = 20;
    private static final Money LARGE_AUDIENCE_FEE = Money.of(CurrencyUnit.USD, 100);
    private static final Money AMOUNT_PER_EXTRA_ATTENDEE = Money.of(CurrencyUnit.USD, 5);
    private static final Money AMOUNT_PER_ATTENDEE = Money.of(CurrencyUnit.USD, 3);
    private static final int BASE_CREDITS_THRESHOLD = 30;
    private static final int BONUS_CREDITS_DIVISOR = 5;
    
    @Override
    public Money calculateAmount(Performance performance) {
        int audience = performance.getAudienceSize();
        Money amount = BASE_AMOUNT;
        
        if (audience > LARGE_AUDIENCE_THRESHOLD) {
            amount = amount.plus(LARGE_AUDIENCE_FEE);
            
            int extraAttendees = audience - LARGE_AUDIENCE_THRESHOLD;
            Money extraCharge = AMOUNT_PER_EXTRA_ATTENDEE.multipliedBy(extraAttendees);
            amount = amount.plus(extraCharge);
        }
        
        Money perAttendeeCharge = AMOUNT_PER_ATTENDEE.multipliedBy(audience);
        amount = amount.plus(perAttendeeCharge);
        
        return amount;
    }
    
    @Override
    public VolumeCredits calculateCredits(Performance performance) {
        int audience = performance.getAudienceSize();
        int baseCredits = Math.max(audience - BASE_CREDITS_THRESHOLD, 0);
        int bonusCredits = audience / BONUS_CREDITS_DIVISOR;
        int totalCredits = baseCredits + bonusCredits;
        return VolumeCredits.of(totalCredits);
    }
}
