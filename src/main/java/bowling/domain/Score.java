package bowling.domain;

public class Score {
    private final ScoreType scoreType;
    private final int hit;

    public Score() {
        this.scoreType = ScoreType.INIT;
        this.hit = 0;
    }

    public Score(int hit) {
        this.hit = hit;
        this.scoreType = ScoreType.of(hit);
    }

    public String payload() {
        if (this.scoreType == ScoreType.INIT) {
            return String.format("%-4s", " ");
        }
        if (this.scoreType == ScoreType.STRIKE) {
            return String.format("%-4s", "X");
        }
        if (this.scoreType == ScoreType.SECOND) {
            return String.format("%-4s", this.hit);
        }
        throw new RuntimeException("unreachable");
    }
}
