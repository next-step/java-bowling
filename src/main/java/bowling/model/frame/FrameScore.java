package bowling.model.frame;

public class FrameScore {
    private final Score score;
    private final WaitingPitchingCount count;

    private FrameScore(Score score, WaitingPitchingCount count) {
        this.score = score;
        this.count = count;
    }

    public static FrameScore of(int score) {
        return new FrameScore(Score.of(score), WaitingPitchingCount.noCount());
    }

    public static FrameScore strike(int score) {
        return new FrameScore(Score.of(score), WaitingPitchingCount.ofStrike());
    }

    public static FrameScore spare(int score) {
        return new FrameScore(Score.of(score), WaitingPitchingCount.ofSpare());
    }
}
