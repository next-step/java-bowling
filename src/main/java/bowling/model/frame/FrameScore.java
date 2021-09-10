package bowling.model.frame;

public class FrameScore {
    private final Score score;
    private final WaitingFrameCount count;

    private FrameScore(Score score, WaitingFrameCount count) {
        this.score = score;
        this.count = count;
    }

    public static FrameScore of(int score) {
        return new FrameScore(Score.of(score), WaitingFrameCount.noCount());
    }

    public static FrameScore strike(int score) {
        return new FrameScore(Score.of(score), WaitingFrameCount.ofStrike());
    }

    public static FrameScore spare(int score) {
        return new FrameScore(Score.of(score), WaitingFrameCount.ofSpare());
    }
}
