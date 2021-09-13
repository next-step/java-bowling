package bowling.view;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

public enum PitchSign {
    GUTTER("-", (pitchValues, index) -> pitchValues.get(index) == 0),
    SPARE("/", (pitchValues, index) -> index != 0
            && pitchValues.get(index - 1) + pitchValues.get(index) == 10),
    STRIKE("X", (pitchValues, index) -> pitchValues.get(index) == 10);

    private final String sign;
    private final BiPredicate<List<Integer>, Integer> isSign;

    PitchSign(final String sign, final BiPredicate<List<Integer>, Integer> isSign) {
        this.sign = sign;
        this.isSign = isSign;
    }

    public static String convertToSign(final List<Integer> pitchValues, final int index) {
        return Arrays.stream(values())
                .filter(sign -> sign.isSign.test(pitchValues, index))
                .findAny()
                .map(PitchSign::getSign)
                .orElseGet(() -> String.valueOf(pitchValues.get(index)));
    }

    private String getSign() {
        return sign;
    }
}
