package bowling.domain;

import static bowling.domain.HitState.NORMAL;
import static bowling.domain.HitState.SPARE;
import static bowling.domain.HitState.STRIKE;

import bowling.domain.state.State;
import java.util.Optional;
import java.util.OptionalInt;

public class Frame {

    private Frame nextFrame;
    private Frame beforeFrame;
    protected Pins pins = new Pins();
    protected Scores scores = new Scores();
    private State state = State.ofReady();

    public Frame() {
        this(null, null);
    }

    protected Frame(Frame beforeFrame, Frame nextFrame) {
        this.beforeFrame = beforeFrame;
        this.nextFrame = nextFrame;
    }

    public OptionalInt scoreCalculated() {
        if (scores.isStrike()) {
            return STRIKE.scoreOf(this);
        }

        if (scores.isSpare()) {
            return SPARE.scoreOf(this);
        }

        if (scores.isPlayTwice()) {
            return NORMAL.scoreOf(this);
        }

        return OptionalInt.empty();
    }

    public Frame createNext() {
        this.nextFrame = new Frame(this, null);
        return nextFrame;
    }

    public void createFinal() {
        this.nextFrame = new FinalFrame(this, null);
    }

    public Frame createBefore() {
        this.beforeFrame = new Frame(null, this);
        return beforeFrame;
    }

    public void shot(int hitCount) {
        pins.hit(hitCount);
        scores.hit(hitCount);
    }

    public void shot2(int hitCount) {
        state = state.bowl(hitCount);
    }

    public boolean isDone() {
        return pins.isHitAll() || scores.isPlayTwice();
    }

    public boolean isDone2() {
        return state.isDone();
    }

    public int remainedPins() {
        return pins.standing();
    }

    public Optional<Score> getFirstScoreAsOptional() {
        return Optional.ofNullable(scores.first());
    }

    public boolean isNotThrowFirstYet() {
        return !scores.isPlayFirst();
    }

    public boolean isThrowFirst() {
        return scores.isPlayFirst();
    }

    public boolean isThrowSecond() {
        return scores.isPlaySecond();
    }

    public Optional<Score> getSecondScoreAsOptional() {
        return Optional.ofNullable(scores.second());
    }

    public Frame next() {
        return this.nextFrame;
    }

    public Frame before() {
        return this.beforeFrame;
    }

    public boolean hasNext() {
        return this.nextFrame != null;
    }

    public int firstScore() {
        return getFirstScoreAsOptional()
            .orElseThrow(() -> new IllegalStateException("아직 첫번째 투구를 하지 않았음"))
            .get();
    }

    public int secondScore() {
        return getSecondScoreAsOptional()
            .orElseThrow(() -> new IllegalStateException("아직 두번째 투구를 하지 않았음"))
            .get();
    }

    protected State getState() {
        return state;
    }
}
