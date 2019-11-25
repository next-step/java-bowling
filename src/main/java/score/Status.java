package score;

import java.util.Arrays;

public enum Status {
    GUTTER(Status::isGutter, 0),
    STRIKE(Status::isStrike, 2),
    SPARE(Status::isSpare, 1),
    MISS(Status::isMiss, 0),
    NONE((score, before) -> false, 0);

    private static final int STRIKE_SCORE = 10;
    private static final int GUTTER_SCORE = 0;
    private static final int FIRST_BEFORE = -1;

    private StatusMatcher matcher;
    private int addCount;

    Status(StatusMatcher matcher, int addCount) {
        this.matcher = matcher;
        this.addCount = addCount;
    }

    public static Status findStatus(Integer score, Integer before) {
        return Arrays.stream(values())
                .filter(status -> status.match(score, before))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상태값 입니다."));
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

    public int getAddCount() {
        return addCount;
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

    private boolean match(Integer score, Integer before) {
        return matcher.match(score, before);
    }
}
