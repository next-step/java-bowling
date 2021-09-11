package bowling.domain;

import java.util.Objects;

public class NormalFrame extends Frame {
    private static final int FINAL_NORMAL_FRAME_NUMBER = 9;
    public static final int UN_SCORE = -1;

    private final int frameNumber;
    private Frame next;

    public NormalFrame(int frameNumber) {
        super(new NormalPitch(), States.newInstance());
        this.frameNumber = frameNumber;
    }

    @Override
    public int getFrameNumber() {
        return frameNumber;
    }

    @Override
    public Frame next() {
        if (isFinish()) {
            return getFrame();
        }
        return this;
    }

    private Frame getFrame() {
        if (frameNumber == FINAL_NORMAL_FRAME_NUMBER) {
            next = new FinalFrame();
            return next;
        }
        next = new NormalFrame(frameNumber + 1);
        return next;
    }

    public boolean hasNext() {
        return Objects.nonNull(next);
    }


    @Override
    public Score getScore() {
        if (pitch.getState() == State.GUTTER || pitch.getState() == State.NORMAL || pitch.getState() == State.MISS) {
            return pitch.getScore();
        }

        if (!hasNext()) {
            return Score.unScore();
        }

        if (!next.isFirstPitchDone()) {
            return Score.unScore();
        }

        return next.additionalScore(pitch.getScore());
    }

    public Score additionalScore(Score score) {
        score = score.additionalScore(getFirstPin());

        if (score.canCalculate()) {
            return score;
        }

        if (hasNext() && next.isFirstPitchDone()) {
            return next.additionalScore(score);
        }

        if (hasNext() && !next.isFirstPitchDone()) {
            return Score.unScore();
        }

        if (!hasNext() && !isSecondPitchDone()) {
            return Score.unScore();
        }

        return score.additionalScore(getSecondPin());
    }

    @Override
    public boolean isEnd(){
        return false;
    }

}
