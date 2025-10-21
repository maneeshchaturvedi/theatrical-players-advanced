package com.stackshala.theatricalplayers.calculation;

import com.stackshala.theatricalplayers.domain.*;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.*;

class StatementCalculatorTest {
    
    private final StatementCalculator calculator = new StatementCalculator();
    
    @Test
    void calculatesTragedyPricing() {
        Play hamlet = Play.of("Hamlet", PlayType.TRAGEDY);
        Performance perf = Performance.of(hamlet, 55);
        Invoice invoice = Invoice.of("BigCo", Collections.singletonList(perf));
        
        StatementResult result = calculator.calculate(invoice);
        
        assertThat(result.getTotalAmount())
            .isEqualTo(Money.of(CurrencyUnit.USD, 650));
        assertThat(result.getTotalCredits())
            .isEqualTo(VolumeCredits.of(25));
    }
    
    @Test
    void calculatesComedyPricing() {
        Play asLike = Play.of("As You Like It", PlayType.COMEDY);
        Performance perf = Performance.of(asLike, 35);
        Invoice invoice = Invoice.of("TestCo", Collections.singletonList(perf));
        
        StatementResult result = calculator.calculate(invoice);
        
        assertThat(result.getTotalAmount())
            .isEqualTo(Money.of(CurrencyUnit.USD, 580));
        assertThat(result.getTotalCredits())
            .isEqualTo(VolumeCredits.of(12));
    }
    
    @Test
    void calculatesMultiplePerformances() {
        Play hamlet = Play.of("Hamlet", PlayType.TRAGEDY);
        Play asLike = Play.of("As You Like It", PlayType.COMEDY);
        Play othello = Play.of("Othello", PlayType.TRAGEDY);
        
        Performance perf1 = Performance.of(hamlet, 55);
        Performance perf2 = Performance.of(asLike, 35);
        Performance perf3 = Performance.of(othello, 40);
        
        Invoice invoice = Invoice.of("BigCo", Arrays.asList(perf1, perf2, perf3));
        
        StatementResult result = calculator.calculate(invoice);
        
        assertThat(result.getTotalAmount())
            .isEqualTo(Money.of(CurrencyUnit.USD, 1730));
        assertThat(result.getTotalCredits())
            .isEqualTo(VolumeCredits.of(47));
        assertThat(result.getLineItemCount()).isEqualTo(3);
    }
}
