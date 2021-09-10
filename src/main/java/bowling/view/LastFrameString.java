package bowling.view;

import bowling.domain.Shot;
import bowling.util.BowlingUtils;

import java.util.Arrays;
import java.util.List;

public enum  LastFrameString {
    ONE(1) {
        @Override
        public String result(List<Shot> shots) {
            Shot firstShot = shots.get(FIRST);

            if (isFirstStrike(shots)) {
                return STRIKE;
            }

            return firstShot.toString();
        }
    }
    , TWO(2) {
        @Override
        public String result(List<Shot> shots) {
            Shot firstShot = shots.get(FIRST);

            String first = firstShot.toString();
            if (isFirstStrike(shots)) {
                first = STRIKE;
            }

            Shot secondShot = shots.get(SECOND);
            String second = secondShot.toString();

            int sum = BowlingUtils.sum(shots);
            if (isDoubleStrike(shots)) {
                second = STRIKE;
            }

            if (isSpare(firstShot, secondShot)) {
                second = SPARE;
            }

            return first + SEPARATOR + second;
        }
    }
    , THREE(3) {
        @Override
        public String result(List<Shot> shots) {
            Shot thirdShot = shots.get(THIRD);

            String third = thirdShot.toString();
            if (thirdShot.getDownCount() == STRIKE_COUNT) {
                third = STRIKE;
            }

            if (isDoubleStrike(shots)) {
                return STRIKE + SEPARATOR + STRIKE + SEPARATOR + third;
            }

            Shot firstShot = shots.get(FIRST);
            Shot secondShot = shots.get(SECOND);
            if (isSpare(firstShot, secondShot)) {
                return firstShot + SEPARATOR + SPARE + SEPARATOR + third;

            }

            if (isSpare(secondShot, thirdShot)) {
                third = SPARE;
            }

            return STRIKE + SEPARATOR + secondShot + SEPARATOR + third;
        }
    };

    private static final int FIRST = 0;
    private static final int SECOND = 1;
    private static final int THIRD = 2;
    private static final int SPARE_COUNT = 10;
    private static final int STRIKE_COUNT = 10;
    private static final int DOUBLE_COUNT = 20;
    private static final String STRIKE = "X";
    private static final String SPARE = "/";
    private static final String SEPARATOR = "|";

    private int size;

    LastFrameString(int size) {
        this.size = size;
    }

    abstract public String result(List<Shot> shots);

    boolean isFirstStrike(List<Shot> shots) {
        if (shots.size() == 0) {
            throw new IllegalArgumentException("this list empty or null");
        }

        Shot first = shots.get(FIRST);
        return first.getDownCount() == STRIKE_COUNT;
    }

    boolean isDoubleStrike(List<Shot> shots) {
        if (shots.size() < 2) {
            throw new IllegalArgumentException("this list can not check double");
        }

        Shot firstShot = shots.get(FIRST);
        Shot secondShot = shots.get(SECOND);
        return firstShot.getDownCount() + secondShot.getDownCount() == DOUBLE_COUNT;
    }

    boolean isSpare(Shot firstShot, Shot secondShot) {
        if (firstShot.getDownCount() == STRIKE_COUNT) {
            return false;
        }

        return firstShot.getDownCount() + secondShot.getDownCount() == SPARE_COUNT;
    }

    public static String getString(List<Shot> shots) {
        return Arrays.stream(LastFrameString.values())
                .filter((lastFrameString -> lastFrameString.size == shots.size()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("shot list size is not matched Any state"))
                .result(shots);
    }
}
