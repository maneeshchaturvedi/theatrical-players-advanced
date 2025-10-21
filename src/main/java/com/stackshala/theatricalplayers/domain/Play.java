package com.stackshala.theatricalplayers.domain;

import java.util.Objects;

public final class Play {
    private final String name;
    private final PlayType type;
    
    private Play(String name, PlayType type) {
        this.name = validateName(name);
        this.type = Objects.requireNonNull(type, "Play type cannot be null");
    }
    
    public static Play of(String name, PlayType type) {
        return new Play(name, type);
    }
    
    private static String validateName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Play name cannot be null");
        }
        
        String trimmed = name.trim();
        
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("Play name cannot be empty");
        }
        
        return trimmed;
    }
    
    public String getName() {
        return name;
    }
    
    public PlayType getType() {
        return type;
    }
    
    public boolean isTragedy() {
        return type.isTragedy();
    }
    
    public boolean isComedy() {
        return type.isComedy();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Play play = (Play) o;
        return name.equals(play.name) && type == play.type;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }
    
    @Override
    public String toString() {
        return name + " (" + type.getDisplayName() + ")";
    }
}
