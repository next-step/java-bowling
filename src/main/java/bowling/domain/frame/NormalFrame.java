package bowling.domain.frame;

import bowling.domain.score.Score;

public class NormalFrame extends Frame {

    private NormalFrame(int round, Score score, Frame nextFrame) {
        super(round, score, nextFrame);
    }

    public static Frame createFirstFrame() {
        return new NormalFrame(1, null, null);
    }
}
