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

    private final LinkedList<State> states;

    private FinalFrame(int round, LinkedList<State> states) {
        super(round);
        this.states = states;
    }

    static Frame from(int round, LinkedList<State> states) {
        return new FinalFrame(round, states);
    }

    public static Frame createFinalFrame() {
        LinkedList<State> states = new LinkedList<>();
        states.add(new Ready());
        return new FinalFrame(FINAL_ROUND, states);
    }

    @Override
    public Optional<Frame> nextFrame() {
        throw new FinalFrameNextFrameException();
    }

    @Override
    public Frame bowling(Pin pin) {
        checkIsFinished();

        State state = states.getLast();
        if (state.isFinished()) {
            return createFrameByNextStateBowl(pin, states);
        }

        states.removeLast();
        states.add(state.bowl(pin));
        return this;
    }

    private Frame createFrameByNextStateBowl(Pin pin, LinkedList<State> states) {
        State nextState = new Ready();
        states.add(nextState.bowl(pin));
        return new FinalFrame(FINAL_ROUND, states);
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
        }
        catch (CannotCalculateException | StateCannotCalculateScoreException | RunningCreateScoreException e) {
            return FrameResult.createFrameResultByNoCaculatedScore(createDescByStates());
        }
    }

    private String createDescByStates() {
        return states.stream()
            .map(State::desc)
            .collect(Collectors.joining(DESC_DELIMITER));
    }

    Score score() {
        Score score = states.get(0).createScore();
        for (int i=1; i<states.size(); i++){
            score = states.get(i).calculateAdditionalScore(score);
        }
        return score;
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        for (State state : states) {
            score = state.calculateAdditionalScore(score);
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

}
