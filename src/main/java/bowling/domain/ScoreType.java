package bowling.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum ScoreType {
    STRIKE(true, true, Collections.singletonList(10), (a) -> Collections.emptyList()),
    SPARE(false, true, IntStream.range(1, 10).boxed().collect(Collectors.toList()), (a) -> Collections.singletonList(10 - a)),
    MISS_FIRST(true, false, IntStream.range(1, 10).boxed().collect(Collectors.toList()), (a) -> Collections.emptyList()),
    MISS_SECOND(false, false, IntStream.range(0, 10).boxed().collect(Collectors.toList()), (a) -> IntStream.range(1, 10 - a).boxed().collect(Collectors.toList())),
    GUTTER_FIRST(true, false, Collections.singletonList(0), (a) -> Collections.emptyList()),
    GUTTER_SECOND(false, false, IntStream.range(0, 10).boxed().collect(Collectors.toList()), (a) -> Collections.singletonList(0));

    private final boolean isFirst;
    private final boolean isCleared;
    private final List<Integer> firstRange;
    private final Function<Integer, List<Integer>> secondRange;

    ScoreType(boolean isFirst, boolean isCleared, List<Integer> firstRange, Function<Integer, List<Integer>> secondRange) {
        this.isFirst = isFirst;
        this.isCleared = isCleared;
        this.firstRange = firstRange;
        this.secondRange = secondRange;
    }

    boolean isFinished() {
        return isCleared || !isFirst;
    }

    boolean isCleared() {
        return isCleared;
    }

    static ScoreType of(Score firstScore) {
        return Arrays.stream(values())
                .filter(v -> v.firstContains(firstScore) &&
                        v.isFirst)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Not Matched Instance : firstScore=%s", firstScore)));
    }

    private boolean firstContains(Score firstScore) {
        return firstRange.contains(firstScore.score());
    }

    static ScoreType of(Score firstScore, Score secondScore) {
        return Arrays.stream(values())
                .filter(v -> v.firstContains(firstScore) &&
                        v.secondContains(firstScore, secondScore))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Not Matched Instance : firstScore=%s, secondScore=%s", firstScore, secondScore)));
    }

    private boolean secondContains(Score firstScore, Score secondScore) {
        return secondRange.apply(firstScore.score()).contains(secondScore.score());
    }
}
