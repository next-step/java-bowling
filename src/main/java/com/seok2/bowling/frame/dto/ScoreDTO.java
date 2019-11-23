package com.seok2.bowling.frame.dto;

public class ScoreDTO {

    private final int score;
    private final boolean pending;

    public ScoreDTO(int score, boolean pending) {
        this.score = score;
        this.pending = pending;
    }

    public int getScore() {
        return score;
    }

    public boolean isNotPending() {
        return !pending;
    }
}
