package bowling.model.frame;

public class Score {
    private static final int WAITING_FRAME_COUNT_OF_STRIKE = 2;
    private static final int WAITING_FRAME_COUNT_OF_SPARE = 1;
    private static final int NO_WAITING_FRAME_COUNT = 0;
    private static final int MIN = 0;
    private static final int MAX = 300;

    private int score;
    private int waitingFrameCount;

    private Score(int score) {
        validateScoreRange(score);

        this.score = score;
        this.waitingFrameCount = NO_WAITING_FRAME_COUNT;
    }

    private Score(int score, int waitingFrameCount) {
        validateScoreRange(score);

        this.score = score;
        this.waitingFrameCount = waitingFrameCount;
    }

    public static Score of(int score) {
        return new Score(score);
    }

    public static Score strike(int score) {
        return new Score(score, WAITING_FRAME_COUNT_OF_STRIKE);
    }

    public static Score spare(int score) {
        return new Score(score, WAITING_FRAME_COUNT_OF_SPARE);
    }

    private void validateScoreRange(int score) {
        if (score < MIN || score > 300) {
            throw new IllegalArgumentException(String.format("볼링 점수는 %d점 이상 %d점 이하이어야 합니다.", MIN, MAX));
        }
    }
}
