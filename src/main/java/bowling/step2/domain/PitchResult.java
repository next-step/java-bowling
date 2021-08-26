package bowling.step2.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum PitchResult {
    STRIKE(Collections.singletonList(10)),
    SPARE(Collections.singletonList(10)),
    MISS(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)),
    GUTTER(Collections.singletonList(0)),
    NONE(Collections.emptyList());

    private final List<Integer> totalCount;

    PitchResult(List<Integer> totalCount) {
        this.totalCount = totalCount;
    }

    public static PitchResult findResultOf(PitchResult preResult, int count) {
        if (preResult == NONE) {
            return Arrays.stream(values())
                    .filter(pitchResult -> pitchResult.totalCount.contains(count))
                    .findFirst()
                    .orElse(PitchResult.MISS);
        }

        return Arrays.stream(values())
                .skip(1)
                .filter(pitchResult -> pitchResult.totalCount.contains(count))
                .findFirst()
                .orElse(PitchResult.MISS);
    }
}
