package bowling;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum BowlingResult {
    STRIKE(Arrays.asList(10), Arrays.asList(1,3)),
    SPARE(Arrays.asList(10), Arrays.asList(2)),
    MISS(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9), Arrays.asList(2,3)),
    GUTTER(Arrays.asList(0), Arrays.asList(1,2,3)),
    EMPTY(Collections.EMPTY_LIST, Collections.EMPTY_LIST);

    private final List<Integer> pinCount;
    private final List<Integer> tryCount;

    BowlingResult(List<Integer> pinCount, List<Integer> tryCount) {
        this.pinCount = pinCount;
        this.tryCount = tryCount;
    }

    public List<Integer> getPinCount() {
        return pinCount;
    }

    public List<Integer> getTryCount() {
        return tryCount;
    }

    public static BowlingResult findBowlingResult(bowling.model.Point point, int tryCount) {
        return Arrays.stream(BowlingResult.values())
                .filter(bowlingResult -> isMatchCount(point, bowlingResult))
                .filter(bowlingResult -> isMatchTry(tryCount, bowlingResult))
                .findAny()
                .orElse(EMPTY);
    }


    private static boolean isMatchCount(bowling.model.Point point, BowlingResult bowlingResult) {
        return bowlingResult.getPinCount()
                .stream()
                .anyMatch(count -> new bowling.model.Point(count).equals(point));
    }

    private static boolean isMatchTry(int tryCount, BowlingResult bowlingResult) {
        return bowlingResult.getTryCount()
                .stream()
                .anyMatch(count -> count == tryCount);
    }


}
