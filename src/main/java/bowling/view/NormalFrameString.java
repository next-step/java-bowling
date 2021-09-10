package bowling.view;

import bowling.domain.Shot;
import bowling.util.BowlingUtils;

import java.util.Arrays;
import java.util.List;

public enum  NormalFrameString {
    ONE(1) {
        @Override
        public String result(List<Shot> shots) {
            if (isStrike(shots)) {
                return STRIKE;
            }
            return shots.get(FIRST).toString();
        }
    }, TWO(2) {
        @Override
        public String result(List<Shot> shots) {
            String first = shots.get(FIRST).toString();
            String second = shots.get(SECOND).toString();

            if (isSpare(shots)) {
                return first + SEPARATOR + SPARE;
            }

            return first + SEPARATOR + second;
        }
    };
    private int size;

    private static final int ALL_PIN_COUNT = 10;
    private static final int FIRST = 0;
    private static final int SECOND = 1;
    private static final String STRIKE = "X";
    private static final String SPARE = "/";
    private static final String SEPARATOR = "|";

    NormalFrameString(int size) {
        this.size = size;
    }

    public abstract String result(List<Shot> shots);

    boolean isSpare(List<Shot> shots) {
        int sum = BowlingUtils.sum(shots);
        return shots.size() == 2 && sum == ALL_PIN_COUNT;
    }

    boolean isStrike(List<Shot> shots) {
        int sum = BowlingUtils.sum(shots);
        return shots.size() == 1 && sum == ALL_PIN_COUNT;
    }

    public static String getString(List<Shot> shots) {
        return Arrays.stream(NormalFrameString.values())
                .filter((frameString -> frameString.size == shots.size()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("shot list size is not matched Any state"))
                .result(shots);
    }
}
