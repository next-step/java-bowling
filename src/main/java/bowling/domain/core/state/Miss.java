package bowling.domain.core.state;

import bowling.domain.core.FallenPins;
import bowling.domain.core.RolledResult;
import bowling.domain.frame.TerminateFrame;

import static bowling.domain.core.state.Gutter.gutter;

final class Miss implements RolledResult {
    private TwoFallenPins twoFallenPins;

    Miss(){
        this.twoFallenPins = new TwoFallenPins();
    }

    Miss(FallenPins fallenPins) {
        this();
        twoFallenPins.collect(fallenPins);
    }

    @Override
    public int tryCountByTerminateFrame() { return isCompleteState() ? TerminateFrame.MAX_TRY_COUNT_SIZE : 0; }

    @Override
    public boolean isCompleteState() {
        return twoFallenPins.isComplete();
    }

    @Override
    public String description() {
        return String.format("%s|%s", gutterOrFallenPinValue(0), gutterOrFallenPinValue(1));
    }

    @Override
    public ImmutableTwoFallenPins twoFallenPins() {
        return twoFallenPins.immutable();
    }

    @Override
    public Score getScore(){
        return new Score(twoFallenPins().totalScore(), 0);
    }

    @Override
    public Score calculateScore(Score score) {
        final ImmutableTwoFallenPins immutableTwoFallenPins = twoFallenPins();
        score = score.sum(immutableTwoFallenPins.firstFallenPinsValue());

        if (score.hasNotAttemptLeft()){
            return score;
        }

        return score.sum(immutableTwoFallenPins.secondFallenPinsValue());
    }

    @Override
    public RolledResult nextRolledResult(int fallenPinsValue) {
        twoFallenPins.collect(FallenPins.of(fallenPinsValue));
        if (twoFallenPins.isMiss()){
            return this;
        }
        if (twoFallenPins.isSpare()) {
            return new Spare(twoFallenPins());
        }
        return gutter;
    }

    @Override
    public String toString() {
        return "Miss{" + twoFallenPins.isComplete() + "= " +  twoFallenPins.getFallenPins(0) + ", " + twoFallenPins.getFallenPins(1) +
        '}';
    }
}
