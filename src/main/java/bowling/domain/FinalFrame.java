package bowling.domain;

public class FinalFrame extends Frame{

    private static final int MAX_PITCH_COUNT = 3;

    private FinalFrame(int index){
        super(index);
    }

    @Override
    public void setScore(int score) {
        this.score = Score.of(pitchResults.sumUpCurrentResult());
    }

    public static FinalFrame from(int index){
        return new FinalFrame(index);
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

        if (currentPoint % BOWLING_PIN_COUNT == 0) {
            return BOWLING_PIN_COUNT;
        }

        if (currentPoint > BOWLING_PIN_COUNT) {
            currentPoint = currentPoint - BOWLING_PIN_COUNT;
        }

        return BOWLING_PIN_COUNT - currentPoint;
    }


    @Override
    public boolean isEnd() {
        return (pitchResults.size() == 2 && sumCurrentPitchResults() == 0) ||
                (pitchResults.size() == 2 && countLeftOverPins() > 0 && !hasBonusPitch()) ||
                (pitchResults.size() == MAX_PITCH_COUNT);
    }

    public boolean hasBonusPitch(){
        return pitchResults.isStrike() || pitchResults.isSpare();
    }

    @Override
    public Frame makeNextFrame(int currentFrameIndex) {
        throw new RuntimeException("마지막 프레임입니다.");
    }

    @Override
    public void renewScore(int knockedDownPins) {
        int currentScore = this.score.getScore();
        if (this.score.getLeftBonusCount() > 0) {
            this.score.renewScore(knockedDownPins + currentScore);
        }
    }

}
