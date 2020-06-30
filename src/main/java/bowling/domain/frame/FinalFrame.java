package bowling.domain.frame;

import bowling.domain.dto.StateDtos;
import bowling.domain.pin.PinCount;
import bowling.domain.score.Score;
import bowling.domain.state.State;
import bowling.domain.state.running.Ready;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class FinalFrame extends Frame {

    private static final int MAX_COUNT = 3;

    private final Stack<State> states = new Stack<>();
    private int playCount;

    private FinalFrame() {
        this.playCount = 0;
        this.states.push(Ready.getInstance());
    }

    public static FinalFrame newInstance() {
        return new FinalFrame();
    }

    @Override
    public void bowl(final PinCount hitCount) {
        increasePlayCount();

        updateLastState(getLastState().bowl(hitCount));
        giveBonusBowl();
    }

    private void increasePlayCount() {
        this.playCount++;
    }

    private State getLastState() {
        return this.states.peek();
    }

    private void updateLastState(final State state) {
        this.states.pop();
        this.states.push(state);
    }

    private void giveBonusBowl() {
        if (getLastState().isCleanState() && !isEndedBonusBowl()) {
            states.push(Ready.getInstance());
        }
    }

    @Override
    public Frame initNextFrame() {
        return this;
    }

    @Override
    void addFrame(Frames frames) {
        // do nothing
    }

    @Override
    public boolean isGameOver() {
        return isEndedBonusBowl() || getLastState().isMiss();
    }

    private boolean isEndedBonusBowl() {
        return this.playCount == MAX_COUNT;
    }

    @Override
    public int getFrameNo() {
        return FrameNumber.MAX_NUMBER;
    }

    @Override
    public StateDtos getFrameResult() {
        return StateDtos.of(getStates());
    }

    private List<State> getStates() {
        return new ArrayList<>(this.states);
    }

    @Override
    public Score getScore() {
        Score score = getFirstScore();
        for (int i = 1; i < states.size(); i++) {
            State state = states.get(i);
            score = state.calculateBonusScore(score);
        }

        return score;
    }

    private Score getFirstScore() {
        return states.get(0).getScore();
    }

    @Override
    public Score addBonusScore(final Score beforeScore) {
        Score score = beforeScore;
        for (State state : states) {
            score = state.calculateBonusScore(score);
        }

        return score;
    }
}

