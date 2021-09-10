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

    public Score(int score, int waitingFrameCount) {
        validateScoreRange(score);
        validateWaitingFrameCountRange(waitingFrameCount);

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
        if (score < MIN || score > MAX) {
            throw new IllegalArgumentException(String.format("볼링 점수는 %d점 이상 %d점 이하이어야 합니다.", MIN, MAX));
        }
    }

    private void validateWaitingFrameCountRange(int waitingFrameCount) {
        if (waitingFrameCount < NO_WAITING_FRAME_COUNT || waitingFrameCount > WAITING_FRAME_COUNT_OF_STRIKE) {
            throw new IllegalArgumentException(String.format("기다리고 있는 프레임 개수는 %d개 이상 %d개 이하이어야 합니다.",
                    NO_WAITING_FRAME_COUNT, WAITING_FRAME_COUNT_OF_STRIKE));
        }
    }
}
