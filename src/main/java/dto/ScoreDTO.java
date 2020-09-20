package dto;

import bowling.Frame;

public class ScoreDTO {

    private final int score;
    private final boolean hasScore;
    private final boolean isLast;

    private ScoreDTO(int score, boolean hasScore, boolean isLast) {
        this.score = score;
        this.hasScore = hasScore;
        this.isLast = isLast;
    }

    public static ScoreDTO from(Frame frame, int accumulator) {
        int score = accumulator;
        boolean hasScore = frame.canCalculateScore();
        boolean isLast = frame.isLastFrame();

        if (hasScore) {
            score += frame.calculateScore();
        }
        return new ScoreDTO(score, hasScore, isLast);
    }

    public int getScore() {
        return this.score;
    }

    public boolean hasScore() {
        return this.hasScore;
    }

    public boolean isLast() {
        return this.isLast;
    }
}
