package domain.state;

import domain.Pins;

import static domain.Pins.ALL;

public class StateFactory {

    public static State firstState(Pins first) {
        if (isStrike(first)) {
            return new Strike(first);
        }
        return new Waiting();
    }

    public static State secondState(Pins first, Pins second) {
        if (isStrike(first)) {
            throw new IllegalArgumentException("두번째 기회에 맞지 않은 정보 입니다. : " + first + "," + second);
        }
        if (isSpares(first, second)) {
            return new Spares(first, second);
        }
        return new Open(first, second);
    }

    private static boolean isStrike(Pins first) {
        return ALL.equals(first);
    }

    private static boolean isSpares(Pins first, Pins second) {
        return ALL.equals(first.add(second));
    }
}
