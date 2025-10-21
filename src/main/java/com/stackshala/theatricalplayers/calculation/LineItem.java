package com.stackshala.theatricalplayers.calculation;

import com.stackshala.theatricalplayers.domain.Performance;
import org.joda.money.Money;

import java.util.Objects;

public final class LineItem {
    private final Performance performance;
    private final Money amount;
    private final VolumeCredits credits;
    
    private LineItem(Performance performance, Money amount, VolumeCredits credits) {
        this.performance = Objects.requireNonNull(performance, "Performance cannot be null");
        this.amount = Objects.requireNonNull(amount, "Amount cannot be null");
        this.credits = Objects.requireNonNull(credits, "Credits cannot be null");
    }
    
    public static LineItem of(Performance performance, Money amount, VolumeCredits credits) {
        return new LineItem(performance, amount, credits);
    }
    
    public Performance getPerformance() {
        return performance;
    }
    
    public Money getAmount() {
        return amount;
    }
    
    public VolumeCredits getCredits() {
        return credits;
    }
    
    public String getPlayName() {
        return performance.getPlay().getName();
    }
    
    public int getAudienceSize() {
        return performance.getAudienceSize();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineItem lineItem = (LineItem) o;
        return performance.equals(lineItem.performance) &&
               amount.equals(lineItem.amount) &&
               credits.equals(lineItem.credits);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(performance, amount, credits);
    }
    
    @Override
    public String toString() {
        return String.format(
            "LineItem{play='%s', audience=%d, amount=%s, credits=%s}",
            getPlayName(), getAudienceSize(), amount, credits
        );
    }
}
