package bowling.domain.frame;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;
import bowling.domain.state.State;
import java.util.Objects;

public abstract class AbstractFrame implements Frame {

    public static final int FIRST_ROUND = 1;
    public static final int FINAL_ROUND = 10;

    private final int round;
    private State state;

    AbstractFrame(int round, State state) {
        this.round = round;
        this.state = state;
    }

    @Override
    public int round() {
        return round;
    }

    @Override
    public Frame lastFrame() {
        Frame resultFrame = this;
        return calculateLastFrame(resultFrame);
    }

    private Frame calculateLastFrame(Frame resultFrame) {
        while (resultFrame.round() < FINAL_ROUND && resultFrame.nextFrame().isPresent()) {
            resultFrame = resultFrame.nextFrame().get();
        }
        return resultFrame;
    }

    State stateBowling(Pin pin) {
        return state = state.bowl(pin);
    }

    Score createScore() {
        return state.createScore();
    }

    Score stateCalculateAdditionalScore(Score beforeScore) {
        return state.calculateAdditionalScore(beforeScore);
    }

    boolean stateIsFinished() {
        return state.isFinished();
    }

    String desc() {
        return state.desc();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractFrame that = (AbstractFrame) o;
        return round == that.round;
    }

    @Override
    public int hashCode() {
        return Objects.hash(round);
    }

}
