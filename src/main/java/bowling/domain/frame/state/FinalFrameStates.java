package bowling.domain.frame.state;

import bowling.domain.score.Calculator;
import bowling.domain.score.Score;
import bowling.domain.score.ScoreCalculator;
import bowling.exception.BowlingException;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FinalFrameStates implements State {

    private static final int FINAL_STATE_SIZE = 2;
    private static final String FINAL_STATE_SIZE_ERR_MESSAGE = "마지막 프레임은 최대 3번 투구 가능";

    private final LinkedList<State> states;

    public FinalFrameStates(final LinkedList<State> states) {
        validateStateSize(states);
        this.states = new LinkedList<>(states);
    }

    public static FinalFrameStates of() {
        LinkedList<State> states = new LinkedList<>();
        states.add(new Ready());
        return new FinalFrameStates(states);
    }

    private void validateStateSize(final List<State> states) {
        if (states.size() > FINAL_STATE_SIZE) {
            throw new BowlingException(FINAL_STATE_SIZE_ERR_MESSAGE);
        }
    }

    public FinalFrameStates addState(final State state) {
        LinkedList<State> merge = new LinkedList<>(states);
        merge.add(state);
        return new FinalFrameStates(merge);
    }

    private boolean isThrowAbleBonus() {
        if (isNormalTurnStrikeOrSpare() && !isHaveBonus()) {
            return true;
        }
        return false;
    }

    private boolean isNormalTurnStrikeOrSpare() {
        if (states.getFirst() instanceof Strike
                || states.getFirst() instanceof Spare) {
            return true;
        }

        return false;
    }

    private boolean isNormalTurnFirstBowl() {
        if (isHaveBonus()) {
            return false;
        }

        if (states.getFirst() instanceof FirstBowl) {
            return true;
        }

        return false;
    }

    private boolean isNormalTurnMissOrGutter() {
        if (states.getLast() instanceof Miss || states.getLast() instanceof Gutter) {
            return true;
        }

        return false;
    }

    public boolean isHaveBonus() {
        if (states.size() == FINAL_STATE_SIZE && !(states.getLast() instanceof Ready)) {
            return true;
        }
        return false;
    }

    @Override
    public FinalFrameStates bowl(final int pinCount) {
        if (isFinish()) {
            throw new BowlingException(State.CANT_THROW_BALL);
        }

        if (isNormalTurnStrikeOrSpare()) {
            State third = new Ready();
            third = third.bowl(pinCount);
            return addState(third);
        }

        State bowl = states.getFirst().bowl(pinCount);
        LinkedList<State> states = new LinkedList<>(Arrays.asList(bowl));
        return new FinalFrameStates(states);
    }

    @Override
    public boolean isFinish() {
        if (states.getFirst() instanceof Ready) {
            return false;
        }

        if (isHaveBonus()) {
            return true;
        }

        if (isThrowAbleBonus()) {
            return false;
        }

        if (isNormalTurnFirstBowl()) {
            return false;
        }

        if (isNormalTurnMissOrGutter()) {
            return true;
        }

        if (states.size() == FINAL_STATE_SIZE) {
            return false;
        }

        return true;
    }

    @Override
    public String getCurrentPinsState() {
        StringBuffer buffer = new StringBuffer();

        String firstResult = states.getFirst().getCurrentPinsState();

        if (isHaveBonus()) {
            buffer.append(firstResult.trim());
            buffer.append("|").append(states.getLast().getCurrentPinsState().trim());
            return String.format("%5s ", buffer.toString());
        }

        buffer.append(firstResult);
        return String.format("%3s ", buffer.toString());
    }

    @Override
    public Score getCurrentScore() {
        Score firstStateScore = states.getFirst().getCurrentScore();

        if (isHaveBonus()) {
            Score secondStateScore = states.getLast().getCurrentScore();
            return new Score(firstStateScore.getScore() + secondStateScore.getScore(), 0);
        }

        return new Score(firstStateScore.getScore(), 0);
    }

    @Override
    public Calculator getCurrentCalculator() {
        Score firstStateScore = states.getFirst().getCurrentCalculator().getScore();

        if (isHaveBonus()) {
            Score secondStateScore = states.getLast().getCurrentCalculator().getScore();
            return new ScoreCalculator(firstStateScore.addScore(secondStateScore), 0);
        }

        return new ScoreCalculator(firstStateScore, 0);
    }

    @Override
    public Score getCalculateScore(Score before) {
        before = states.getFirst().getCalculateScore(before);

        if (before.canAddNextScore()) {
            return states.getLast().getCalculateScore(before);
        }

        return before;
    }

    @Override
    public Calculator getScoreCalculate(Calculator before) {
        before = states.getFirst().getScoreCalculate(before);

        if (before.canAddNextScore()) {
            return states.getLast().getScoreCalculate(before);
        }

        return before;
    }
}
