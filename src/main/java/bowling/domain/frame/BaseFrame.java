package bowling.domain.frame;

import bowling.domain.score.Score;

import java.util.List;

public abstract class BaseFrame implements Frame {

    protected static int FIRST_TRIAL = 1;

    protected static int SECOND_TRIAL = 2;

    private int trial;

    protected Score score;

    private final Frame prevFrame;

    protected BaseFrame(int trial, Score score, Frame prevFrame) {
        this.trial = trial;
        this.score = score;
        this.prevFrame = prevFrame;
    }

    public boolean isNowFirstTry() {
        return trial == FIRST_TRIAL;
    }

    public boolean isNowSecondTry() {
        return trial == SECOND_TRIAL;
    }

    public int getTotalScore() {
        return score.getTotal();
    }

    public int calculateScore() {
        return score.calculateWith(this);
    }

    @Override
    public Frame prev() {
        return prevFrame;
    }


    @Override
    public List<Integer> getAllScores() {
        return score.getAll();
    }

    protected void increaseTrial() {
        this.trial++;
    }

}
