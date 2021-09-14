package bowling.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public enum BowlingResult {
    STRIKE(Arrays.asList(10), Arrays.asList(1,2,3)),
    SPARE(Arrays.asList(10), Arrays.asList(2,3)),
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

    public static BowlingResult findBowlingResult(Point point, int tryCount, BowlingResult beforeResult) {
        System.out.println(beforeResult);
        List<BowlingResult> bowlingResults = Arrays.stream(BowlingResult.values())
                .filter(bowlingResult -> isMatchCount(point, bowlingResult))
                .filter(bowlingResult -> isMatchTry(tryCount, bowlingResult))
                .collect(Collectors.toList());

        return checkDuplicate(bowlingResults, beforeResult);
    }

    private static BowlingResult checkDuplicate(List<BowlingResult> bowlingResults, BowlingResult beforeResult) {
        if (bowlingResults.size() == 0) {
            return EMPTY;
        }

        if (bowlingResults.size() > 1) {
            return isStrikeOrSpare(beforeResult);
        }

        return bowlingResults.get(0);
    }

    private static BowlingResult isStrikeOrSpare(BowlingResult beforeResult) {
        if (beforeResult == SPARE || beforeResult == STRIKE) {
            return STRIKE;
        }

        return SPARE;
    }


    private static boolean isMatchCount(Point point, BowlingResult bowlingResult) {
        return bowlingResult.getPinCount()
                .stream()
                .anyMatch(count -> new Point(count).equals(point));
    }

    private static boolean isMatchTry(int tryCount, BowlingResult bowlingResult) {
        return bowlingResult.getTryCount()
                .stream()
                .anyMatch(count -> count == tryCount);
    }


}
