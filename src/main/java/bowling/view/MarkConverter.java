package bowling.view;

import bowling.domain.pin.FallenPins;

public class MarkConverter {

    public static String getSecondMark(FallenPins firstResult, FallenPins secondResult) {
        if (firstResult != null && firstResult.isAllPinFallen()) {
            return getResultMark(secondResult, null);
        }
        return getResultMark(secondResult, firstResult);
    }

    public static String getResultMark(FallenPins fallenPins, FallenPins previousFallenPins) {
        if (isEmpty(fallenPins)) {
            return ResultMark.EMPTY.getMark();
        }

        if (isGutter(fallenPins)) {
            return ResultMark.GUTTER.getMark();
        }

        if (isSpare(fallenPins, previousFallenPins)) {
            return ResultMark.SPARE.getMark();
        }

        if (isStrike(fallenPins, previousFallenPins)) {
            return ResultMark.STRIKE.getMark();
        }

        return String.valueOf(fallenPins.getCountOfPin());
    }

    private static boolean isStrike(FallenPins fallenPins, FallenPins previousFallenPins) {
        return isEmpty(previousFallenPins)
                && fallenPins.getCountOfPin() == FallenPins.MAX_COUNT_OF_PIN;
    }

    private static boolean isGutter(FallenPins fallenPins) {
        return fallenPins.getCountOfPin() == 0;
    }

    private static boolean isEmpty(FallenPins fallenPins) {
        return fallenPins == null;
    }

    private static boolean isSpare(FallenPins fallenPins, FallenPins previousFallenPins) {
        return previousFallenPins != null &&
                fallenPins.getCountOfPin() + previousFallenPins.getCountOfPin()
                        == FallenPins.MAX_COUNT_OF_PIN;
    }

}
