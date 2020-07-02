package bowling.domain.frame;

import bowling.domain.dto.StateDtos;
import bowling.domain.pin.PinCount;
import bowling.domain.score.Score;
import bowling.domain.state.FinalFrameStates;
import bowling.domain.state.State;
import bowling.domain.state.running.Ready;

import java.util.List;

public class FinalFrame extends Frame {

    private static final int MAX_COUNT = 3;

    private final FinalFrameStates states;
    private int playCount;

    private FinalFrame() {
        this.playCount = 0;
        this.states = FinalFrameStates.newInstance();
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
        return this.states.getLastState();
    }

    private void updateLastState(final State state) {
        this.states.updateLastState(state);
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
    public void addFrame(Frames frames) {
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
    public boolean isTurnOver() {
        return this.isInitializedFinalFrame() || this.getLastState().isFinish();
    }

    private boolean isInitializedFinalFrame() {
        return states.size() == 1 && this.getLastState() == Ready.getInstance();
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
        return this.states.toList();
    }

    @Override
    public Score getScore() {
        Score score = getFirstScore();
        for (int i = 1; i < states.size(); i++) {
            State state = states.indexOf(i);
            score = state.calculateBonusScore(score);
        }

        return score;
    }

    private Score getFirstScore() {
        return states.getFirstState().getScore();
    }

    @Override
    public Score addBonusScore(final Score beforeScore) {
        Score score = beforeScore;
        for (State state : states.getStates()) {
            score = state.calculateBonusScore(score);
        }

        return score;
    }
}

