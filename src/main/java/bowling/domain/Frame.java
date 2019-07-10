package bowling.domain;

import java.util.List;

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
class Frame {
    private static int AUTO_INCREASE = 0;
    private static int FRAME_MAX_NUMBER = 10;
    private FrameScore frameScore;
    private int index;

    public Frame() {
        if (AUTO_INCREASE > FRAME_MAX_NUMBER) {
            throw new IllegalArgumentException("10번의 경기가 종료되었습니다.");
        }
        this.index = AUTO_INCREASE++;
        this.frameScore = new FrameScore();
    }

    private Frame(int fallCount) {
        if (AUTO_INCREASE >= FRAME_MAX_NUMBER) {
            throw new IllegalArgumentException("10번의 경기가 종료되었습니다.");
        }
        FrameScore scores = new FrameScore(fallCount);
        this.index = AUTO_INCREASE++;
        this.frameScore = scores;
    }

    public static Frame of(int fallCount) {
        return new Frame(fallCount);
    }

    boolean bowl(int fallCount) {
        boolean isBowl = frameScore.addScore(fallCount);
        if (isBowl) {
            return true;
        }
        return false;
    }
    int frameNumber() {
        return index;
    }

    int getRecentScore() {
        return frameScore.searchRecentScore();
    }

    boolean isStrike() {
        return frameScore.isStrike();
    }

    FrameScore nowFrameScores() {
        return new FrameScore(frameScore.getScores());
    }

    @Override
    public String toString() {
        return "Frame{" + frameScore + '}';
    }
}
