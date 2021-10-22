package bowling.domain.frame;

import static bowling.domain.frame.NormalFrame.FINAL_ROUND;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;
import bowling.domain.state.State;
import bowling.domain.state.running.Ready;
import bowling.exception.frame.FinalFrameBowlingException;
import bowling.exception.score.CannotCalculateException;
import bowling.exception.state.RunningCreateScoreException;
import bowling.exception.state.StateCannotCalculateScoreException;
import java.util.LinkedList;

public class FinalFrame implements Frame {

    private final int round;
    private final LinkedList<State> states;

    private FinalFrame(int round, LinkedList<State> states) {
        this.round = round;
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
    public Frame bowling(Pin pin) {
        checkIsFinished();

        State state = states.getLast();
        if (state.isFinished()) {
            return createFrameByNextStateBowl(pin);
        }
        state.bowl(pin);
        return new FinalFrame(FINAL_ROUND, new LinkedList<>(states));
    }

    private Frame createFrameByNextStateBowl(Pin pin) {
        State nextState = new Ready();
        states.add(nextState.bowl(pin));
        return new FinalFrame(FINAL_ROUND, new LinkedList<>(states));
    }

    private void checkIsFinished() {
        if (score().canCalculateScore()) {
            throw new FinalFrameBowlingException();
        }
    }

    @Override
    public FrameResult createFrameResult() {
        try {
            return FrameResult.of(score().score());
        }
        catch (CannotCalculateException | StateCannotCalculateScoreException | RunningCreateScoreException e) {
            return FrameResult.createEmptyScoreFrameResult();
        }
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
        return score;
    }

}
