package bowling.domain.core;

final class IncompleteState implements RolledResult {
    private final Pins firstFallenPins;

    IncompleteState(Pins firstFallenPins) {
        this.firstFallenPins = firstFallenPins;
    }

    @Override
    public boolean isCompleteState(){
        return false;
    }

    @Override
    public boolean canNotSpendRemainingPins(int fallenPins) {
        int remainingPins = Pins.MAX_FALLEN_PIN_COUNT - countOfFallenPinsByRolls(0);
        return (0 != remainingPins) && (remainingPins < fallenPins);
    }

    @Override
    public String description() {
        return String.format("%s|?", gutterOrFallenPinCount());
    }

    @Override
    public int countOfFallenPinsByRolls(int rollingTryCount) {
        return firstFallenPins.getFallenPins();
    }

    String gutterOrFallenPinCount() {
        if (firstFallenPins.isGutter()){
            return "-";
        }
        return String.valueOf(firstFallenPins.getFallenPins());
    }
}
