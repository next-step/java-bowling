package bowling.domain.frame;

import bowling.domain.Score;

public class NormalFrame extends Frame {

    private static final int MAX_PITCH_COUNT = 2;

    private NormalFrame(int index){
        super(index);
    }

    @Override
    public void setScore(int previousScore) {
        this.score = createScore(previousScore);
    }

    public Score createScore(int previousScore) {
        int sumUp = previousScore + sumUpCurrentResult();

        if(pitchResults.isStrike()){
            return Score.ofStrike(sumUp);
        }

        if(pitchResults.isSpare()){
            return Score.ofSpare(sumUp);
        }

        return Score.of(sumUp);
    }

    public int sumUpCurrentResult(){
        return pitchResults.sumUpCurrentResult();
    }


    public static NormalFrame from(int index){
        return new NormalFrame(index);
    }

    @Override
    public void start(int knockedDownPins) {
        if (!isEnd()) {
            validateKnockedDownPins(knockedDownPins);
            pitchResults.addNewResult(knockedDownPins);
        }
    }

    private void validateKnockedDownPins(int knockedDownPins) {
        if (countLeftOverPins() < knockedDownPins) {
            throw new IllegalArgumentException(ILLEGAL_KNOCK_DOWN_PINS);
        }
    }

    private int countLeftOverPins() {
        int currentPoint = sumCurrentPitchResults();
        return BOWLING_PIN_COUNT - currentPoint;
    }

    @Override
    public boolean isEnd() {
        return (countLeftOverPins() == 0) || (pitchResults.size() == MAX_PITCH_COUNT);
    }

    @Override
    public Frame makeNextFrame(int currentFrameIndex) {
        if (currentFrameIndex == MAX_FRAME_COUNT - 1) {
            return FinalFrame.from(MAX_FRAME_COUNT);
        }

        return NormalFrame.from(currentFrameIndex + 1);
    }

    @Override
    public void renewScore(int knockedDownPins) {
        int currentScore = this.score.getScore();

        if (this.score.getLeftBonusCount() > 0) {
            this.score.renewScore(knockedDownPins + currentScore);
        }
    }

}
