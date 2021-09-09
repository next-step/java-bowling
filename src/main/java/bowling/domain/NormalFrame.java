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

    @Override
    public Score getScore() {
        if(pitch.getState() == State.GUTTER || pitch.getState() == State.NORMAL || pitch.getState() == State.MISS){
            return pitch.score;
        }
        if(Objects.isNull(next)){
            return Score.of(UN_SCORE, 0);
        }


        return next.additionalScore(pitch.score);
    }

    public Score additionalScore(Score score) {
        score = score.add(getFirstPin());
        if(score.canCalculate()){
            return score;
        }

        if(!isSecondPitchDone()){
            return Score.of(UN_SCORE, 0);
        }

        return score.add(getSecondPin());
    }
}
