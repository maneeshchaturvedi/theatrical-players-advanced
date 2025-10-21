package com.stackshala.theatricalplayers.domain;

public enum PlayType {
    TRAGEDY("Tragedy"),
    COMEDY("Comedy");
    
    private final String displayName;
    
    PlayType(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public static PlayType fromString(String type) {
        if (type == null) {
            throw new IllegalArgumentException("Play type cannot be null");
        }
        
        String normalized = type.trim().toUpperCase();
        
        switch (normalized) {
            case "TRAGEDY":
                return TRAGEDY;
            case "COMEDY":
                return COMEDY;
            default:
                throw new IllegalArgumentException(
                    "Unknown play type: '" + type + "'. Valid types: TRAGEDY, COMEDY"
                );
        }
    }
    
    public boolean isTragedy() {
        return this == TRAGEDY;
    }
    
    public boolean isComedy() {
        return this == COMEDY;
    }
}
