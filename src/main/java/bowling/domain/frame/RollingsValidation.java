package bowling.domain.frame;

import java.util.Arrays;

public enum RollingsValidation {
    POST_STRIKE_ROLLING(Constants.STRIKE_MESSAGE, (first, second) -> first == 10 && second > 0),
    EXCEED_LIMIT_SUM(Constants.EXCEED_SUM_MESSAGE, (first, second) -> (first + second) > 10),
    NONE(Constants.NONE_MESSAGE, (first, second) -> false);

    private final String message;
    private final RollingsOperator rollingsOperator;

    RollingsValidation(String message, RollingsOperator rollingsOperator) {
        this.message = message;
        this.rollingsOperator = rollingsOperator;
    }

    public static RollingsValidation of(int first, int second) {
        return Arrays.stream(RollingsValidation.values())
                .filter(validation -> validation.rollingsOperator.invalid(first, second))
                .findFirst()
                .orElse(NONE);
    }

    public static RollingsValidation of(Rolling first, Rolling second) {
        if (second == null) {
            return of(first.fallenPin(), 0);
        }
        return of(first.fallenPin(), second.fallenPin());
    }

    public String message() {
        return message;
    }

    private static class Constants {
        public static final String EXCEED_SUM_MESSAGE = "두 번의 투구동안 쓰러트린 핀의 수가 10보다 클 수 없습니다.";
        public static final String STRIKE_MESSAGE = "초구 스트라이크 이후 동일 프레임내 투구할 수 없습니다.";
        public static final String NONE_MESSAGE = "정상 투구입니다.";
    }
}
