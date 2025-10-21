package com.stackshala.theatricalplayers;

import com.stackshala.theatricalplayers.calculation.StatementCalculator;
import com.stackshala.theatricalplayers.calculation.StatementResult;
import com.stackshala.theatricalplayers.domain.*;
import com.stackshala.theatricalplayers.presentation.HtmlFormatter;
import com.stackshala.theatricalplayers.presentation.PlainTextFormatter;
import com.stackshala.theatricalplayers.presentation.StatementFormatter;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Create plays
        Play hamlet = Play.of("Hamlet", PlayType.TRAGEDY);
        Play asLike = Play.of("As You Like It", PlayType.COMEDY);
        Play othello = Play.of("Othello", PlayType.TRAGEDY);
        
        // Create performances
        Performance perf1 = Performance.of(hamlet, 55);
        Performance perf2 = Performance.of(asLike, 35);
        Performance perf3 = Performance.of(othello, 40);
        
        // Create invoice
        Invoice invoice = Invoice.of("BigCo", Arrays.asList(perf1, perf2, perf3));
        
        // Calculate statement
        StatementCalculator calculator = new StatementCalculator();
        StatementResult result = calculator.calculate(invoice);
        
        // Format as plain text
        System.out.println("=== PLAIN TEXT FORMAT ===");
        StatementFormatter textFormatter = new PlainTextFormatter();
        String textOutput = textFormatter.format(result);
        System.out.println(textOutput);
        
        // Format as HTML
        System.out.println("\n=== HTML FORMAT ===");
        StatementFormatter htmlFormatter = new HtmlFormatter();
        String htmlOutput = htmlFormatter.format(result);
        System.out.println(htmlOutput);
    }
}
