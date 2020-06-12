package bowling.step2.domain.frame;

import bowling.step2.domain.scores.FinalScores;
import bowling.step2.domain.scores.NormalScores;
import bowling.step2.domain.scores.Scores;

public class NormalFrame implements Frame {

    private final int frame;
    private final NormalScores scores;
    private Frame nextFrame;

    private NormalFrame(int frame, NormalScores scores) {
        this.frame = frame;
        this.scores = scores;
    }

    public static NormalFrame of (int frame, NormalScores scores) {
        return new NormalFrame(frame, scores);
    }


    @Override
    public NormalScores getScores () {
        return scores;
    }

    @Override
    public Frame createNextFrame () {
        int nextFrame = frame + 1;
        if (nextFrame < Frame.LAST_FRAME) {
            this.nextFrame = of(nextFrame, NormalScores.init());
            return this.nextFrame;
        }
        this.nextFrame = FinalFrame.of(nextFrame, FinalScores.init());
        return this.nextFrame;
    }

    @Override
    public Frame getNextFrame () {
        return this.nextFrame;
    }


    @Override
    public boolean hasNext () {
        return this.nextFrame != null;
    }
}