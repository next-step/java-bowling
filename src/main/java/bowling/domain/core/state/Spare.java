package bowling.domain.core.state;

import bowling.domain.core.FallenPins;
import bowling.domain.core.RolledResult;

public final class Spare implements RolledResult {
    private ImmutableTwoFallenPins immutableTwoFallenPins;

    Spare(ImmutableTwoFallenPins twoFallenPins) {
        this.immutableTwoFallenPins = twoFallenPins;
    }

    public static RolledResult expectSpareAfterBonusBowl(RolledResult rolledResult){
        if (rolledResult instanceof Spare){
            return new SpareAfterBonusBowl(rolledResult);
        }
        return rolledResult;
    }

    @Override
    public int tryCountByTerminateFrame() {
        return 2;
    }

    @Override
    public Score getScore() {
        return new Score(FallenPins.MAX_FALLEN_PIN_COUNT, 1);
    }

    @Override
    public Score calculateScore(Score score) {
        final ImmutableTwoFallenPins immutableTwoFallenPins = twoFallenPins();
        score = score.sum(immutableTwoFallenPins.firstFallenPinsValue());
        System.out.println("spare : " + score);

        if (score.hasNotAttemptLeft()){
            return score;
        }

        return score.sum(immutableTwoFallenPins.secondFallenPinsValue());
    }

    @Override
    public ImmutableTwoFallenPins twoFallenPins() {
        return immutableTwoFallenPins;
    }

    @Override
    public String description() {
        return String.format("%s|/", gutterOrFallenPinValue(0));
    }

    @Override
    public String toString() {
        return "Spare{" + immutableTwoFallenPins.getFallenPins(0) + ", " + immutableTwoFallenPins.getFallenPins(1) +
            '}';
    }
}
