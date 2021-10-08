package bowling.domain.frame;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;
import bowling.domain.state.State;
import bowling.domain.state.running.Ready;
import bowling.exception.frame.NextFrameNotFoundException;
import java.util.Objects;
import java.util.Optional;

public class NormalFrame implements Frame {

    public static final int FIRST_ROUND = 1;
    public static final int FINAL_ROUND = 10;

    private final int round;
    private Frame nextFrame;
    private State state;

    private NormalFrame(int round, Frame nextFrame, State state) {
        this.round = round;
        this.nextFrame = nextFrame;
        this.state = state;
    }

    static Frame from(int round, Frame nextFrame, State state) {
        return new NormalFrame(round, nextFrame, state);
    }

    public static Frame createFirstFrame() {
        return new NormalFrame(1, null, new Ready());
    }

    @Override
    public Frame bowling(Pin pin) {
        state = state.bowl(pin);
        if (state.isFinished()) {
            return createNextFrame();
        }
        return this;
    }

    @Override
    public Frame createNextFrame() {
        if (round + 1 == FINAL_ROUND) {
            return FinalFrame.createFinalFrame();
        }
        return new NormalFrame(round + 1, null, new Ready());
    }

    @Override
    public FrameResult createFrameResult() {
        return FrameResult.of(score().score());
    }

    public Score score() {
        Score score = state.createScore();
        if (score.canCalculateScore()) {
            return score;
        }
        return nextFrame()
            .map(frame -> frame.calculateAdditionalScore(score))
            .orElseThrow(NextFrameNotFoundException::new);
    }

    private Optional<Frame> nextFrame() {
        return Optional.ofNullable(nextFrame);
    }

    public Score calculateAdditionalScore(Score beforeScore) {
        Score score = state.calculateAdditionalScore(beforeScore);
        if (score.canCalculateScore()) {
            return score;
        }
        return nextFrame()
            .map(frame -> frame.calculateAdditionalScore(score))
            .orElseThrow(NextFrameNotFoundException::new);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NormalFrame frame = (NormalFrame) o;
        return round == frame.round && Objects.equals(nextFrame, frame.nextFrame)
            && Objects.equals(state.getClass(), frame.state.getClass());
    }

    @Override
    public int hashCode() {
        return Objects.hash(round, nextFrame, state.getClass());
    }

}
