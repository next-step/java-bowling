package bowling.domain;

public class FinalFrame extends Frame {
    private static final int FINAL_FRAME_NUMBER = 10;

    public FinalFrame() {
        super(new FinalPitch(), States.newInstance());
    }

    @Override
    public int getFrameNumber() {
        return FINAL_FRAME_NUMBER;
    }

    @Override
    public Frame next() {
        return this;
    }

    @Override
    public Score getScore() {
        return pitch.getScore();
    }
    public Score additionalScore(Score score){
        score = score.additionalScore(getFirstPin());
        if(score.canCalculate()) {
            return score;
        }

        if(!isSecondPitchDone()){
            return Score.of(NormalFrame.UN_SCORE, 0);
        }

        return score.additionalScore(getSecondPin());
    }
}
