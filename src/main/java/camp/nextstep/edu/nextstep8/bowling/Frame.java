package camp.nextstep.edu.nextstep8.bowling;

import static camp.nextstep.edu.nextstep8.bowling.constant.BowlingRule.MAX_SCORE;

public class Frame {
    private final int score;
    private final int spare;
    private static String STRIKE = "X";

    public Frame(int score, int spare) {
        this.score = score;
        this.spare = spare;
    }

    public int getScore() {
        return this.score;
    }

    public String getFrameResultSymbol() {
        return checkStatus().makeSymbol(score, spare);
    }

    private FrameStatus checkStatus() {
        if(MAX_SCORE == score && 0 == spare) {
            return FrameStatus.STRIKE;
        }

        if(MAX_SCORE == score + spare) {
            return FrameStatus.SPARE;
        }

        if(0 == score + spare) {
            return FrameStatus.GUTTER;
        }
        return FrameStatus.MISS;
    }
}
