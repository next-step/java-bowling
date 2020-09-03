package bowling.domain.core;

final class SpareAfterBonusBowl implements RolledResult {
    private final Pins bonusFallenPins;

    SpareAfterBonusBowl(RolledResult rolledResult) {
        bonusFallenPins = Pins.of(rolledResult.countOfFallenPinsByRolls(0));
    }

    @Override
    public String description() {
        return String.format("%s",gutterOrFallenPinCount());
    }

    @Override
    public int countOfFallenPinsByRolls(int rollingTryCount) {
        return bonusFallenPins.getFallenPins();
    }

    private String gutterOrFallenPinCount() {
        if (bonusFallenPins.isGutter()){
            return "-";
        }
        return String.valueOf(bonusFallenPins.getFallenPins());
    }
}
