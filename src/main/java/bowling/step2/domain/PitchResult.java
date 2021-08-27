package bowling.step2.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum PitchResult {
    STRIKE(Arrays.asList(10, 20), "X"),
    SPARE(Collections.singletonList(10), "/"),
    ONE(Collections.singletonList(1), "1"),
    TWO(Collections.singletonList(2), "2"),
    THREE(Collections.singletonList(3), "3"),
    FOUR(Collections.singletonList(4), "4"),
    FIVE(Collections.singletonList(5), "5"),
    SIX(Collections.singletonList(6), "6"),
    SEVEN(Collections.singletonList(7), "7"),
    EIGHT(Collections.singletonList(8), "8"),
    NINE(Collections.singletonList(9), "9"),
    GUTTER(Collections.singletonList(0), "-"),
    NONE(Collections.singletonList(-1), " ");

    private final List<Integer> totalCount;
    private final String shape;

    PitchResult(List<Integer> totalCount, String shape) {
        this.totalCount = totalCount;
        this.shape = shape;
    }

    public static List<PitchResult> findResultOf(Frame frame) {
        if (frame instanceof NormalFrame) {
            return findResultOfNormal((NormalFrame) frame);
        }

        return findResultOfLast((LastFrame) frame);
    }

    private static List<PitchResult> findResultOfNormal(NormalFrame normalFrame) {
        List<PitchResult> results = new ArrayList<>();
        addFirstResult(results, normalFrame);
        addSecondResult(results, normalFrame);
        return results;
    }

    private static List<PitchResult> findResultOfLast(LastFrame lastFrame) {
        List<PitchResult> results = new ArrayList<>();
        addFirstResult(results, lastFrame);
        addSecondResult(results, lastFrame);
        addAdditionalResult(results, lastFrame);
        return results;
    }

    private static void addFirstResult(List<PitchResult> results, Frame frame) {
        results.add(PitchResult.findByCount(frame.countValueOfFirst(), 0));
    }

    private static void addSecondResult(List<PitchResult> results, Frame frame) {
        if (frame instanceof NormalFrame) {
            results.add(PitchResult.findByCount(frame.countValueOfFirst() + frame.countValueOfSecond(), frame.countValueOfFirst() == 10 ? 0 : 1));
            return;
        }

        if (frame.countValueOfFirst() == 10) {
            results.add(PitchResult.findByCount(frame.countValueOfSecond(), 0));
            return;
        }

        results.add(PitchResult.findByCount(frame.countValueOfFirst() + frame.countValueOfSecond(), 1));
    }

    private static void addAdditionalResult(List<PitchResult> results, LastFrame frame) {
        results.add(PitchResult.findByCount(frame.countOfAdditional(), 0));
    }

    private static PitchResult findByCount(int count, int skip) {
        return Arrays.stream(values())
                .skip(skip)
                .filter(pitchResult -> pitchResult.totalCount.contains(count))
                .findFirst()
                .orElse(PitchResult.NONE);
    }
}
