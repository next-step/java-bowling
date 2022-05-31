package bowling.domain;

import bowling.domain.state.State;
import java.util.Optional;
import java.util.OptionalInt;

public class Frame {

    private Frame nextFrame;
    private Frame beforeFrame;
    protected State state = State.ofReady();

    public Frame() {
        this(null, null);
    }

    protected Frame(Frame beforeFrame, Frame nextFrame) {
        this.beforeFrame = beforeFrame;
        this.nextFrame = nextFrame;
    }

    public OptionalInt scoreCalculated() {
        if (!state.canCalculateScore()) {
            return OptionalInt.empty();
        }

        Score score = state.score();

        if (score.isAddedAllBonus()) {
            return score.getAsOptionalInt();
        }

        try {
            score = nextFrame.bonusScore(score);
        } catch (IllegalStateException e) {
            return OptionalInt.empty();
        }

        return score.getAsOptionalInt();
    }

    private Score bonusScore(Score previousScore) {
        Score score = state.addBonus(previousScore);

        if (score.isAddedAllBonus()) {
            return score;
        }

        return nextFrame.bonusScore(score);
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
        state = state.bowl(hitCount);
    }

    public boolean isDone() {
        return state.isDone();
    }

    public Optional<Score> getFirstScoreAsOptional() {
        return Optional.empty();
//        return Optional.ofNullable(state.firstScore());
    }

    public Optional<Score> getSecondScoreAsOptional() {
        return Optional.empty();
//        return Optional.ofNullable(scores.second());
    }

    public Frame next() {
        return this.nextFrame;
    }

    public Frame before() {
        return this.beforeFrame;
    }

    protected State state() {
        return state;
    }

}
