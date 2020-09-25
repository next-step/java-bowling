package bowling.domain.core.state;

import bowling.domain.core.FallenPins;
import bowling.domain.core.RolledResult;
import bowling.domain.frame.TerminateFrame;

import static bowling.domain.core.FallenPins.zero;
import static bowling.domain.core.state.ImmutableTwoFallenPins.of;

final class SpareAfterBonusBowl implements RolledResult{

    private final FallenPins firstFallenFallenPins;

    SpareAfterBonusBowl(RolledResult rolledResult) {
        this.firstFallenFallenPins = firstFallenPins(rolledResult);
    }

    private static FallenPins firstFallenPins(RolledResult rolledResult) {
        return rolledResult.twoFallenPins()
                           .firstFallenPins();
    }

    @Override
    public RolledResult nextRolledResult(int fallenPinsValue) {
        return this;
    }

    @Override
    public int tryCountByTerminateFrame() { return TerminateFrame.MAX_TRY_COUNT_SIZE; }

    @Override
    public ImmutableTwoFallenPins twoFallenPins() {
        return of(firstFallenFallenPins, zero());
    }

    @Override
    public String description() {
        return String.format("%s",gutterOrFallenPinValue(0));
    }

    @Override
    public Score getScore() {
        return new Score(twoFallenPins().totalScore(),0);
    }

    @Override
    public Score calculateScore(Score score) {
        score.sum(firstFallenFallenPins.getPrimitive());
        return score;
    }
}
