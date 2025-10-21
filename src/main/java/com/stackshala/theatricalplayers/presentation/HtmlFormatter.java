package com.stackshala.theatricalplayers.presentation;

import com.stackshala.theatricalplayers.calculation.LineItem;
import com.stackshala.theatricalplayers.calculation.StatementResult;

import java.util.Objects;

public final class HtmlFormatter implements StatementFormatter {
    @Override
    public String format(StatementResult result) {
        Objects.requireNonNull(result, "StatementResult cannot be null");
        
        StringBuilder html = new StringBuilder();
        
        html.append("<h1>Statement for ")
            .append(escapeHtml(result.getCustomer()))
            .append("</h1>\n");
        
        html.append("<table>\n");
        html.append("  <tr><th>Play</th><th>Seats</th><th>Amount</th></tr>\n");
        
        for (LineItem item : result.getLineItems()) {
            html.append("  <tr>")
                .append("<td>").append(escapeHtml(item.getPlayName())).append("</td>")
                .append("<td>").append(item.getAudienceSize()).append("</td>")
                .append("<td>").append(item.getAmount().toString()).append("</td>")
                .append("</tr>\n");
        }
        
        html.append("</table>\n");
        
        html.append("<p>Amount owed is <strong>")
            .append(result.getTotalAmount().toString())
            .append("</strong></p>\n");
        
        html.append("<p>You earned <strong>")
            .append(result.getTotalCredits().getValue())
            .append(result.getTotalCredits().getValue() == 1 ? " credit" : " credits")
            .append("</strong></p>\n");
        
        return html.toString();
    }
    
    private String escapeHtml(String text) {
        return text
            .replace("&", "&amp;")
            .replace("<", "&lt;")
            .replace(">", "&gt;")
            .replace("\"", "&quot;")
            .replace("'", "&#x27;");
    }
}
