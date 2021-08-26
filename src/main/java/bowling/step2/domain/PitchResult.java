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
        results.add(PitchResult.findByCount(normalFrame.countOfFirst(), 0));
        results.add(PitchResult.findByCount(normalFrame.countOfFirst() + normalFrame.countOfSecond(), normalFrame.countOfFirst() == 10 ? 0 : 1));
        return results;
    }

    private static List<PitchResult> findResultOfLast(LastFrame lastFrame) {
        List<PitchResult> results = new ArrayList<>();
        results.add(PitchResult.findByCount(lastFrame.countOfFirst(), 0));
        results.add(PitchResult.findByCount(lastFrame.countOfFirst() + lastFrame.countOfSecond(), lastFrame.countOfFirst() == 10 ? 0 : 1));
        results.add(PitchResult.findByCount(lastFrame.countOfAdditional(), 0));
        return results;
    }

    private static PitchResult findByCount(int count, int skip) {
        return Arrays.stream(values())
                .skip(skip)
                .filter(pitchResult -> pitchResult.totalCount.contains(count))
                .findFirst()
                .orElse(PitchResult.MISS);
    }
}
