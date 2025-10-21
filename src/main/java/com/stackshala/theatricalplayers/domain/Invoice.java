package com.stackshala.theatricalplayers.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class Invoice {
    private final String customer;
    private final List<Performance> performances;
    
    private Invoice(String customer, List<Performance> performances) {
        this.customer = validateCustomer(customer);
        this.performances = validateAndCopyPerformances(performances);
    }
    
    public static Invoice of(String customer, List<Performance> performances) {
        return new Invoice(customer, performances);
    }
    
    private static String validateCustomer(String customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer name cannot be null");
        }
        
        String trimmed = customer.trim();
        
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be empty");
        }
        
        return trimmed;
    }
    
    private static List<Performance> validateAndCopyPerformances(
            List<Performance> performances) {
        
        if (performances == null) {
            throw new IllegalArgumentException("Performances list cannot be null");
        }
        
        if (performances.isEmpty()) {
            throw new IllegalArgumentException(
                "Invoice must have at least one performance"
            );
        }
        
        for (Performance perf : performances) {
            if (perf == null) {
                throw new IllegalArgumentException(
                    "Performance list cannot contain null entries"
                );
            }
        }
        
        return Collections.unmodifiableList(new ArrayList<>(performances));
    }
    
    public String getCustomer() {
        return customer;
    }
    
    public List<Performance> getPerformances() {
        return performances;
    }
    
    public int getPerformanceCount() {
        return performances.size();
    }
    
    public boolean hasMultiplePerformances() {
        return performances.size() > 1;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return customer.equals(invoice.customer) &&
               performances.equals(invoice.performances);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(customer, performances);
    }
    
    @Override
    public String toString() {
        return String.format("Invoice{customer='%s', performances=%d}", 
            customer, performances.size());
    }
    
    public static class Builder {
        private String customer;
        private final List<Performance> performances = new ArrayList<>();
        
        public Builder forCustomer(String customer) {
            this.customer = customer;
            return this;
        }
        
        public Builder addPerformance(Performance performance) {
            if (performance != null) {
                this.performances.add(performance);
            }
            return this;
        }
        
        public Builder addPerformances(List<Performance> performances) {
            if (performances != null) {
                this.performances.addAll(performances);
            }
            return this;
        }
        
        public Invoice build() {
            return Invoice.of(customer, performances);
        }
    }
    
    public static Builder builder() {
        return new Builder();
    }
}
