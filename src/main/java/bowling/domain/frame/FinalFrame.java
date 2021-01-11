package bowling.domain.frame;

import bowling.domain.PitchResults;
import bowling.domain.Score;
import bowling.domain.state.Ready;
import bowling.domain.state.Spare;
import bowling.domain.state.State;
import bowling.domain.state.Strike;

import java.util.LinkedList;

import java.util.stream.Collectors;

public class FinalFrame extends Frame {

    private static final String DELIMITER = "|";
    private LinkedList<State> states = new LinkedList<>();

    private FinalFrame(int index){
        super(index);
        this.states.add(new Ready());
    }

    @Override
    public void setScore(int previousScore) {
        this.score = createScore(previousScore);
    }

    private Score createScore(int previousScore) {
        return Score.of(previousScore + states.stream()
                .mapToInt(State::sumUpCurrentResult)
                .sum());
    }


    public static FinalFrame from(int index){
        return new FinalFrame(index);
    }

    @Override
    public void start(int knockedDownPins) {
        validateKnockedDownPins(knockedDownPins);
        addState();
        pitchState(knockedDownPins);
    }

    private void pitchState(int knockedDownPins) {
        State lastState = states.getLast();
        states.removeLast();
        states.add(lastState.pitch(knockedDownPins));
    }

    private void addState() {
        if (states.getLast().isFinish()) {
            states.add(new Ready());
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

    private int sumCurrentPitchResults() {
        return states.stream().mapToInt(State::sumUpCurrentResult).sum();
    }


    @Override
    public boolean isEnd() {
        if (!hasBonusPitch() && isEndPitch(MIN_PITCH_COUNT)) {
            return true;
        }

        return isEndPitch(MAX_PITCH_COUNT);
    }

    private boolean isEndPitch(int pitchTryCount) {
        return countPitchTries() == pitchTryCount;
    }

    private int countPitchTries() {
        return states.stream()
                .mapToInt(State::getPitchTryCount)
                .sum();
    }

    public boolean hasBonusPitch(){
        return states.stream().anyMatch(this::isStrikeOrSpare);
    }

    private boolean isStrikeOrSpare(State state) {
        return state instanceof Strike || state instanceof Spare;
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

    @Override
    public PitchResults getPitchResults() {
        return states.getLast().getPitchResults();
    }

    @Override
    public String expressState() {
        return states.stream()
                .map(State::toString)
                .collect(Collectors.joining(DELIMITER));
    }


}
