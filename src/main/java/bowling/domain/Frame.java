package bowling.domain;

import static bowling.domain.HitState.NORMAL;
import static bowling.domain.HitState.SPARE;
import static bowling.domain.HitState.STRIKE;

import java.util.Objects;
import java.util.Optional;

public class Frame {

    private Frame nextFrame;
    private Frame beforeFrame;
    protected Pins pins = new Pins();
    protected Scores scores = new Scores();
    private Score accumulatedScore;

    public Frame() {
        this(null, null);
    }

    protected Frame(Frame beforeFrame, Frame nextFrame) {
        this.beforeFrame = beforeFrame;
        this.nextFrame = nextFrame;
    }

    public void calculateScore() {
        if (scores.isStrike()) {
            STRIKE.scoreOf(this)
                .ifPresent(integer -> accumulatedScore = new Score(integer));
            return;
        }

        if (scores.isSpare()) {
            SPARE.scoreOf(this)
                .ifPresent(integer -> accumulatedScore = new Score(integer));
            return;
        }

        if (scores.isPlayTwice()) {
            NORMAL.scoreOf(this)
                .ifPresent(integer -> accumulatedScore = new Score(integer));
        }
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

    public boolean isDone() {
        return pins.isHitAll() || scores.isPlayTwice();
    }

    public int remainedPins() {
        return pins.standing();
    }

    public Optional<Score> getFirstScoreAsOptional() {
        return Optional.ofNullable(scores.first());
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Frame frame = (Frame) o;
        return Objects.equals(nextFrame, frame.nextFrame) && Objects.equals(beforeFrame, frame.beforeFrame) && Objects.equals(pins, frame.pins) && Objects.equals(
            scores, frame.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nextFrame, beforeFrame, pins, scores);
    }

    public Optional<Score> getFrameScoreAsOptional() {
        return Optional.ofNullable(accumulatedScore);
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
}
