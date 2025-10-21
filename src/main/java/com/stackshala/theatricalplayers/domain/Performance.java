package com.stackshala.theatricalplayers.domain;

import java.util.Objects;

public final class Performance {
    private final Play play;
    private final int audienceSize;
    
    private Performance(Play play, int audienceSize) {
        this.play = Objects.requireNonNull(play, "Play cannot be null");
        this.audienceSize = validateAudienceSize(audienceSize);
    }
    
    public static Performance of(Play play, int audienceSize) {
        return new Performance(play, audienceSize);
    }
    
    private static int validateAudienceSize(int size) {
        if (size < 0) {
            throw new IllegalArgumentException(
                "Audience size cannot be negative. Got: " + size
            );
        }
        return size;
    }
    
    public Play getPlay() {
        return play;
    }
    
    public int getAudienceSize() {
        return audienceSize;
    }
    
    public boolean wasTragedy() {
        return play.isTragedy();
    }
    
    public boolean wasComedy() {
        return play.isComedy();
    }
    
    public PlayType getPlayType() {
        return play.getType();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Performance that = (Performance) o;
        return audienceSize == that.audienceSize &&
               play.equals(that.play);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(play, audienceSize);
    }
    
    @Override
    public String toString() {
        return String.format("Performance{play=%s, audience=%d}", 
            play.getName(), audienceSize);
    }
}
