package bowling.domain;

public class FrameScore {
    private final int score;

    public FrameScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }

    @Override
    public String toString() {
        return "FrameScore{" +
                "score=" + score +
                '}';
    }
}
