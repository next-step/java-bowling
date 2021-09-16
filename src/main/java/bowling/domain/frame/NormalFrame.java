package bowling.domain.frame;

import bowling.domain.score.FinalScore;
import bowling.domain.score.NormalScore;
import bowling.domain.score.Pin;
import bowling.domain.score.Score;

public class NormalFrame extends Frame {

    private static final int FIRST_FRAME_ROUND = 1;
    private static final int FINAL_FRAME_ROUND = 10;

    private NormalFrame(int round, Score score, Frame nextFrame) {
        super(round, score, nextFrame);
    }

    public static Frame createFirstFrame() {
        return new NormalFrame(FIRST_FRAME_ROUND, NormalScore.empty(), null);
    }

    @Override
    public Frame createNextFrame() {
        int nextRound = round + 1;
        if (nextRound < FINAL_FRAME_ROUND) {
            return this.nextFrame = new NormalFrame(round + 1, NormalScore.empty(), null);
        }
        return this.nextFrame = new FinalFrame(FINAL_FRAME_ROUND, FinalScore.empty(), null);
    }

    @Override
    public void updateScoreByPin(Pin pin) {
        score.nextPin(pin);
    }

}
