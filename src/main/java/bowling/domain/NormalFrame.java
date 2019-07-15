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
 * create date  : 2019-07-13 17:44
 */
public class NormalFrame extends Frame {
    private static final int NORMAL_FRAME_MAX_NUMBER = 9;
    private static final String NEXT_LINE_DELIMITER = "\n";
    private static final String FRAME_SCORE_DELIMITER = "|";

    private NormalFrameScore currentFrameScore;
    private NormalFrame next;

    NormalFrame() {
        this.currentFrameScore = new NormalFrameScore();
    }

    @Override
    Frame bowl(int downCount) {
        if (currentFrameScore.addBowlScore(downCount)) {
            return this;
        }
        return next(downCount);
    }

    @Override
    boolean isGameOver() {
        return nowFrameNumber() == NORMAL_FRAME_MAX_NUMBER
                && (currentFrameScore.invalidBowlCount()
                    || currentFrameScore.isStrike());
    }

    @Override
    String convertScoreToBowl() {
        return currentFrameScore.stream()
                .map(pin -> Bowl.check(isSpareConvertToSpareScore(pin.downCount()), currentFrameScore.isStrike()))
                .map(bowl -> bowl.getScoreDisplay())
                .collect(Collectors.joining(FRAME_SCORE_DELIMITER));
    }

    String convertAllFrameScoreToBowl(StringBuilder combineBowlDisplay) {
        combineBowlDisplay.append(this.convertScoreToBowl()+ NEXT_LINE_DELIMITER);
        if (next != null) {
            next.convertAllFrameScoreToBowl(combineBowlDisplay);
        }
        return combineBowlDisplay.toString();
    }

    private NormalFrame next(int downCount) {
        next = new NormalFrame();
        next.bowl(downCount);
        return next;
    }

    private int isSpareConvertToSpareScore(int downCount) {
        if (currentFrameScore.isSpare()
                && currentFrameScore.checkBowlPositionFromDownCount(downCount)) {
            return NormalFrameScore.FRAME_MAX_SCORE;
        }
        return downCount;
    }
}
