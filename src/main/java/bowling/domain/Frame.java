package bowling.domain;

public class Frame {

    private static final int MINIMAL_SCORE = 0;
    private static final int MAXIMUM_SCORE = 10;

    private static final String ERR_SCORE_RANGE = "0부터 10까지의 값만 입력할 수 있습니다.";
    private static final String ERR_SUM_OF_SCORE_VALUE = "한 프레임 당 투구 점수는 최대 10점입니다.";

    private int sumOfScores;

    public Frame() {
        this.sumOfScores = 0;
    }

    public Frame nextFrame(boolean isFinalRound) {
        return isFinalRound ? new FinalFrame() : new Frame();
    }

    public void swing(int score) {
        verifyScoreRange(score);
        sumOfScores += score;
        verifySumOfScore();
    }

    private void verifyScoreRange(int score) {
        if (score < MINIMAL_SCORE || score > MAXIMUM_SCORE) {
            throw new IllegalArgumentException(ERR_SCORE_RANGE);
        }
    }

    private void verifySumOfScore() {
        if (sumOfScores > MAXIMUM_SCORE) {
            throw new IllegalArgumentException(ERR_SUM_OF_SCORE_VALUE);
        }
    }
}
