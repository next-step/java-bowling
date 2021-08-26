package bowling.step2.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum PitchResult {
    STRIKE(Arrays.asList(10, 20)),
    SPARE(Collections.singletonList(10)),
    MISS(Collections.emptyList()),
    GUTTER(Collections.singletonList(0));

    private final List<Integer> totalCount;


    PitchResult(List<Integer> totalCount) {
        this.totalCount = totalCount;
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
        results.add(PitchResult.findByCount(frame.countOfFirst(), 0));
    }

    private static void addSecondResult(List<PitchResult> results, Frame frame) {
        if (frame instanceof NormalFrame) {
            results.add(PitchResult.findByCount(frame.countOfFirst() + frame.countOfSecond(), frame.countOfFirst() == 10 ? 0 : 1));
            return;
        }

        if (frame.countOfFirst() == 10) {
            results.add(PitchResult.findByCount(frame.countOfSecond(), 0));
            return;
        }

        results.add(PitchResult.findByCount(frame.countOfFirst() + frame.countOfSecond(), 1));
    }

    private static void addAdditionalResult(List<PitchResult> results, LastFrame frame) {
        results.add(PitchResult.findByCount(frame.countOfAdditional(), 0));
    }

    private static PitchResult findByCount(int count, int skip) {
        return Arrays.stream(values())
                .skip(skip)
                .filter(pitchResult -> pitchResult.totalCount.contains(count))
                .findFirst()
                .orElse(PitchResult.MISS);
    }
}
