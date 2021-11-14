package bowling.domain.frame;

import static bowling.domain.score.Pin.DESC_DELIMITER;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;
import bowling.domain.state.State;
import bowling.domain.state.running.Ready;
import bowling.exception.frame.FinalFrameBowlingException;
import bowling.exception.frame.FinalFrameNextFrameException;
import bowling.exception.score.CannotCalculateException;
import bowling.exception.state.RunningCreateScoreException;
import bowling.exception.state.StateCannotCalculateScoreException;
import java.util.LinkedList;
import java.util.Optional;
import java.util.stream.Collectors;

public class FinalFrame extends AbstractFrame {

    private final LinkedList<State> bonusStates;

    private FinalFrame(int round, State state, LinkedList<State> bonusStates) {
        super(round, state);
        this.bonusStates = bonusStates;
    }

    static Frame from(int round, State state, LinkedList<State> bonusStates) {
        return new FinalFrame(round, state, bonusStates);
    }

    public static Frame createFinalFrame() {
        return new FinalFrame(FINAL_ROUND, new Ready(), new LinkedList<>());
    }

    @Override
    public Optional<Frame> nextFrame() {
        throw new FinalFrameNextFrameException();
    }

    @Override
    public Frame bowling(Pin pin) {
        checkIsFinished();

        if (!stateIsFinished()) {
            stateBowling(pin);
            return this;
        }

        if (bonusStates.isEmpty() || bonusStates.getLast().isFinished()) {
            State nextState = new Ready();
            bonusStates.add(nextState.bowl(pin));
            return this;
        }

        State state = bonusStates.getLast();
        bonusStates.removeLast();
        bonusStates.add(state.bowl(pin));
        return this;
    }

    private void checkIsFinished() {
        if (isFinished()) {
            throw new FinalFrameBowlingException();
        }
    }

    @Override
    public FrameResult createFrameResult() {
        try {
            return FrameResult.ofScoreAndDesc(score().score(), createDescByStates());
        } catch (CannotCalculateException | StateCannotCalculateScoreException | RunningCreateScoreException e) {
            return FrameResult.createFrameResultByNoCaculatedScore(createDescByStates());
        }
    }

    private String createDescByStates() {
        if (bonusStates.isEmpty()) {
            return desc();
        }
        return desc() + "|" + bonusStates.stream()
            .map(State::desc)
            .collect(Collectors.joining(DESC_DELIMITER));
    }

    Score score() {
        Score score = createScore();
        for (State bonusState : bonusStates) {
            score = bonusState.calculateAdditionalScore(score);
        }
        return score;
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        score = stateCalculateAdditionalScore(score);
        if (score.canCalculateScore()) {
            return score;
        }

        for (State bonusState : bonusStates) {
            score = bonusState.calculateAdditionalScore(score);
            if (score.canCalculateScore()) {
                return score;
            }
        }
        throw new StateCannotCalculateScoreException();
    }

    @Override
    public boolean isFinished() {
        try {
            return score().canCalculateScore();
        } catch (RunningCreateScoreException e) {
            return false;
        }
    }

    @Override
    public boolean frameIsFinished() {
        return isFinished();
    }

}
