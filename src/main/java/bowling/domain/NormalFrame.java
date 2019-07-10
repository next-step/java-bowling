package bowling.domain;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-10 16:25
 */
class NormalFrame {
    private static int AUTO_INCREASE = 0;
    private static int FRAME_MAX_NUMBER = 9;

    private NormalFrameScore normalFrameScore;
    private int index;

    private NormalFrame(int fallCount) {
        if (AUTO_INCREASE >= FRAME_MAX_NUMBER) {
            throw new IllegalArgumentException("경기 횟수가 넘었습니다.");
        }
        NormalFrameScore scores = new NormalFrameScore(fallCount);
        this.index = AUTO_INCREASE++;
        this.normalFrameScore = scores;
    }

    public static NormalFrame of(int fallCount) {
        return new NormalFrame(fallCount);
    }

    boolean bowl(int fallCount) {
        boolean isBowl = normalFrameScore.addScore(fallCount);
        if (isBowl) {
            return true;
        }
        return false;
    }

    int frameNumber() {
        return index;
    }

    NormalFrameScore nowFrameScores() {
        return new NormalFrameScore(normalFrameScore.getScores());
    }
}
