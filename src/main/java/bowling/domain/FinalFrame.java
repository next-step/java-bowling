package bowling.domain;

import java.util.stream.Collectors;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-14 22:56
 */
public class FinalFrame extends Frame {

    private FinalFrameScore finalFrameScore;

    FinalFrame() {
        this.finalFrameScore = new FinalFrameScore();
    }

    @Override
    Frame bowl(int downCount) {
        finalFrameScore.addBowlScore(downCount);
        return this;
    }

    @Override
    boolean isGameOver() {
        return finalFrameScore.invalidBowlCount();
    }

    @Override
    String convertScoreToBowl() {
        return finalFrameScore.stream()
                .map(pin -> Bowl.check(isSpareConvertToSpareScore(pin.downCount()), finalFrameScore.isStrike(pin.downCount())))
                .map(bowl -> bowl.getScoreDisplay())
                .collect(Collectors.joining("|"));
    }

    private int isSpareConvertToSpareScore(int downCount) {
        if (finalFrameScore.isSpare()
                && finalFrameScore.checkBowlPositionFromDownCount(downCount)) {
            return FinalFrameScore.SPARE_SCORE;
        }
        return downCount;
    }
}
