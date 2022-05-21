package bowling.domain;

public class Score {
    private final ScoreType scoreType;
    private final int hit;

    public Score() {
        this.scoreType = ScoreType.WAITING;
        this.hit = 0;
    }

    public String payload() {
        return String.format("%-4s", " ");
    }
}
