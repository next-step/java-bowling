package bowling.domain.frame;

import bowling.domain.PitchResults;
import bowling.domain.Score;
import bowling.domain.state.Ready;
import bowling.domain.state.State;

public class NormalFrame extends Frame {

    private State state;

    private NormalFrame(int index){
        super(index);
        this.state = new Ready();
    }

    @Override
    public void setScore(int previousScore) {
        this.score = createScore(previousScore);
    }

    public Score createScore(int previousScore) {
        return state.createScore(previousScore);
    }

    public int sumUpCurrentResult(){
        return state.sumUpCurrentResult();
    }

    public static NormalFrame from(int index){
        return new NormalFrame(index);
    }

    @Override
    public void start(int knockedDownPins) {
        if (!isEnd()) {
            validateKnockedDownPins(knockedDownPins);
            this.state = state.pitch(knockedDownPins);
        }
    }

    private void validateKnockedDownPins(int knockedDownPins) {
        if (countLeftOverPins() < knockedDownPins) {
            throw new IllegalArgumentException(ILLEGAL_KNOCK_DOWN_PINS);
        }
    }

   private int countLeftOverPins() {
        int currentPoint = sumUpCurrentResult();
        return BOWLING_PIN_COUNT - currentPoint;
    }

    @Override
    public boolean isEnd() {
        return this.state.isFinish();
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

    @Override
    public PitchResults getPitchResults() {
        return state.getPitchResults();
    }

    @Override
    public String expressState() {
        return state.toString();
    }

}
