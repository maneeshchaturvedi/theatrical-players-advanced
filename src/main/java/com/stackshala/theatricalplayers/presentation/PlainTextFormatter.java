package com.stackshala.theatricalplayers.presentation;

import com.stackshala.theatricalplayers.calculation.LineItem;
import com.stackshala.theatricalplayers.calculation.StatementResult;

import java.util.Objects;

public final class PlainTextFormatter implements StatementFormatter {
    @Override
    public String format(StatementResult result) {
        Objects.requireNonNull(result, "StatementResult cannot be null");
        
        StringBuilder output = new StringBuilder();
        
        output.append("Statement for ").append(result.getCustomer()).append("\n");
        
        for (LineItem item : result.getLineItems()) {
            output.append("  ")
                  .append(item.getPlayName())
                  .append(": ")
                  .append(item.getAmount().toString())
                  .append(" (")
                  .append(item.getAudienceSize())
                  .append(item.getAudienceSize() == 1 ? " seat" : " seats")
                  .append(")\n");
        }
        
        output.append("Amount owed is ")
              .append(result.getTotalAmount().toString())
              .append("\n");
        
        output.append("You earned ")
              .append(result.getTotalCredits().getValue())
              .append(result.getTotalCredits().getValue() == 1 ? " credit" : " credits")
              .append("\n");
        
        return output.toString();
    }
}
