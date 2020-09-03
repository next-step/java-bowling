package bowling.domain.core;

public final class RolledStateFactory {
    static final String ERROR_MESSAGE_SECOND_BOWL = "두번째 투구에서 핀남은 핀보다 많을 쓰러뜨릴수 없습니다.";

    public static RolledResult firstBowl(int fallenPins){
        Pins pins = Pins.of(fallenPins);
        if (pins.isStrike()){
            return new Strike();
        }
        return new IncompleteState(pins);
    }

    public static RolledResult secondBowl(RolledResult rolledResult, int fallenPins){
        verifySecondBowlFallenPins(rolledResult, fallenPins);
        if (rolledResult.isNotCompleteState()){
            Pins first = Pins.of(rolledResult.countOfFallenPinsByRolls(0));
            Pins second = Pins.of(fallenPins);
            return spendRemainingPins(first, second);
        }
        return rolledResult;
    }

    private static RolledResult spendRemainingPins(Pins first, Pins second) {
        if (first.isSpare(second)) {
            return new Spare(first, second);
        }
        if (first.isMiss(second)) {
            return new Miss(first, second);
        }
        return new Gutter();
    }

    private static void verifySecondBowlFallenPins(RolledResult rolledResult, int fallenPins) {
        if (rolledResult.canNotSpendRemainingPins(fallenPins)){
            throw new IllegalArgumentException(ERROR_MESSAGE_SECOND_BOWL);
        }
    }
}
