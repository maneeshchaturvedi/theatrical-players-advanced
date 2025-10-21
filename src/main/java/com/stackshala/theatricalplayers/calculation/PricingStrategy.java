package com.stackshala.theatricalplayers.calculation;

import com.stackshala.theatricalplayers.domain.Performance;
import com.stackshala.theatricalplayers.domain.PlayType;
import org.joda.money.Money;

public interface PricingStrategy {
    Money calculateAmount(Performance performance);
    VolumeCredits calculateCredits(Performance performance);
    
    static PricingStrategy forPlayType(PlayType playType) {
        switch (playType) {
            case TRAGEDY:
                return new TragedyPricing();
            case COMEDY:
                return new ComedyPricing();
            default:
                throw new IllegalArgumentException("Unknown play type: " + playType);
        }
    }
}
