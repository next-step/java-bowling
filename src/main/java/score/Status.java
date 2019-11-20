package score;

import java.util.Arrays;

public enum Status {
    GUTTER(Status::isGutter),
    STRIKE(Status::isStrike),
    SPARE(Status::isSpare),
    MISS(Status::isMiss),
    NONE((score, before) -> false);

    private static final int STRIKE_SCORE = 10;
    private static final int GUTTER_SCORE = 0;
    private static final int FIRST_BEFORE = -1;

    private StatusMatcher matcher;

    Status(StatusMatcher matcher) {
        this.matcher = matcher;
    }

    public static Status findStatus(Integer score, Integer before) {
        return Arrays.stream(values())
                .filter(status -> status.match(score, before))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상태값 입니다."));
    }

    private boolean match(Integer score, Integer before) {
        return matcher.match(score, before);
    }

    private static boolean isStrike(Integer score, Integer before) {
        if (isNotFirstTry(before)) {
            return false;
        }
        return score == STRIKE_SCORE;
    }

    private static boolean isSpare(Integer score, Integer before) {
        if (isNotFirstTry(before)) {
            return score + before == STRIKE_SCORE;
        }
        return false;
    }

    private static boolean isGutter(Integer score, Integer before) {
        return score == GUTTER_SCORE;
    }

    private static boolean isMiss(Integer score, Integer before) {
        return true;
    }

    private static boolean isNotFirstTry(Integer before) {
        return before != FIRST_BEFORE;
    }
}
