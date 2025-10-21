package com.stackshala.theatricalplayers.calculation;

import java.util.Objects;

public final class VolumeCredits {
    private final int value;
    
    private VolumeCredits(int value) {
        if (value < 0) {
            throw new IllegalArgumentException(
                "Volume credits cannot be negative. Earned: " + value
            );
        }
        this.value = value;
    }
    
    public static VolumeCredits of(int amount) {
        return new VolumeCredits(amount);
    }
    
    public static VolumeCredits zero() {
        return new VolumeCredits(0);
    }
    
    public VolumeCredits add(VolumeCredits other) {
        Objects.requireNonNull(other, "Cannot add null credits");
        return new VolumeCredits(this.value + other.value);
    }
    
    public int getValue() {
        return value;
    }
    
    public boolean hasCredits() {
        return value > 0;
    }
    
    public boolean isGreaterThan(VolumeCredits other) {
        Objects.requireNonNull(other, "Cannot compare with null credits");
        return this.value > other.value;
    }
    
    public String formatted() {
        return value + (value == 1 ? " credit" : " credits");
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VolumeCredits that = (VolumeCredits) o;
        return value == that.value;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
    
    @Override
    public String toString() {
        return formatted();
    }
}
