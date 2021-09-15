package bowling.domain.frame;

import bowling.domain.score.Score;

public class NormalFrame extends Frame {

    private static final int FIRST_FRAME_ROUND = 1;
    private static final int FINAL_FRAME_ROUND = 10;

    private NormalFrame(int round, Score score, Frame nextFrame) {
        super(round, score, nextFrame);
    }

    public static Frame createFirstFrame() {
        return new NormalFrame(FIRST_FRAME_ROUND, null, null);
    }

    @Override
    public Frame createNextFrame() {
        int nextRound = round + 1;
        if (nextRound < FINAL_FRAME_ROUND) {
            return this.nextFrame = new NormalFrame(round + 1, null, null);
        }
        return this.nextFrame = new FinalFrame(FINAL_FRAME_ROUND, null, null);
    }

}
