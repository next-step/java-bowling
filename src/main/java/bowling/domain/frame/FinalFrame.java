package bowling.domain.frame;

import bowling.domain.Score;
import bowling.domain.pin.Pin;
import bowling.domain.state.*;

import java.util.ArrayList;
import java.util.List;

public class FinalFrame implements Frame {

    /**
     * POSSIBLE STATE SCENARIOS BY SIZE
     * ================================================
     * SIZE 1
     *
     * 1.1) Miss                         (8|1)
     * ================================================
     * SIZE 2
     *
     * 2.1) Spare  + ExtraBowl           (8|/|9), (8|/|X)
     * 2.2) Strike + Miss                (X|8|1)
     * 2.3) Strike + Spare               (X|8|/)
     * ================================================
     * SIZE 3
     *
     * 3.1) Strike + Strike + ExtraBowl  (X|X|9), (X|X|X)
     * ================================================
     */

    private final List<State> states = new ArrayList<>();

    public FinalFrame() {
        this.states.add(new Ready());
    }

    @Override
    public int number() {
        return 10;
    }

    @Override
    public Frame bowl(Pin no) {
        if (finished()) {
            throw new IllegalStateException();
        }

        State firstState = firstState();
        if (!firstState.finished()) {
            states.set(0, firstState.bowl(no));
            return this;
        }
        if (shouldBowlReady()) {
            states.add(new Ready().bowl(no));
            return this;
        }
        if (shouldBowlExtra()) {
            states.add(new ExtraBowl(no));
            return this;
        }
        states.set(1, secondState().bowl(no));
        return this;
    }

    private boolean shouldBowlReady() {
        return stateSizeEquals(1) && firstState() instanceof Strike;
    }

    private boolean shouldBowlExtra() {
        return stateSizeEquals(1) && firstState() instanceof Spare
                || stateSizeEquals(2) && secondState() instanceof Strike;
    }

    @Override
    public Score score() {
        Score score = firstState().score();
        for (int index = 1; index < states.size(); index++) {
            State currentState = states.get(index);
            score = currentState.additionalScore(score);
        }
        return score;
    }

    @Override
    public boolean finished() {
        return firstState() instanceof Miss ||
                stateSizeEquals(2) && secondState().finished() && !(secondState() instanceof Strike)||
                stateSizeEquals(3);
    }

    @Override
    public Score additionalScore(Score beforeScore) {
        Score afterScore = firstState().additionalScore(beforeScore);
        if (afterScore.canGetScore() || stateSizeEquals(1)) {
            return afterScore;
        }
        return secondState().additionalScore(afterScore);
    }

    @Override
    public String expression() {
        StringBuilder sb = new StringBuilder();
        for (State state : states) {
            sb.append(state.expression());
            sb.append("|");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private boolean stateSizeEquals(int size) {
        return states.size() == size;
    }

    private State firstState() {
        return states.get(0);
    }

    private State secondState() {
        return states.get(1);
    }
}
