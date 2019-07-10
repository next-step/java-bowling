package bowling.domain;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-11 00:34
 */
public class FinalFrame {
    private FinalFrameScore finalFrameScore;

    private FinalFrame(int fallCount) {
        FinalFrameScore scores = new FinalFrameScore(fallCount);
        this.finalFrameScore = scores;
    }

    public static FinalFrame of(int fallCount) {
        return new FinalFrame(fallCount);
    }

    boolean bowl(int fallCount) {
        boolean isBowl = finalFrameScore.addScore(fallCount);
        if (isBowl) {
            return true;
        }
        return false;
    }
}
