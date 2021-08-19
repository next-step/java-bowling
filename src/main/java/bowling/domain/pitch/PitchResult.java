package bowling.domain.pitch;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum PitchResult {

    STRIKE("X", Collections.singletonList(10)),
    SPARE("/", Collections.emptyList()),
    MISS("", Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)),
    GUTTER("-", Collections.singletonList(0));

    private static final int SPARE_NUMBER = 10;

    private final String flag;
    private final List<Integer> numbers;

    PitchResult(final String flag, final List<Integer> numbers) {
        this.flag = flag;
        this.numbers = numbers;
    }

    public String getFlag() {
        return flag;
    }

    public static PitchResult findByPitch(final Pitch first) {
        return findByNumber(first);
    }

    public static PitchResult findByPitch(final Pitch first, final Pitch second) {
        if (first.getNumber() + second.getNumber() == SPARE_NUMBER) {
            return SPARE;
        }

        return findByNumber(second);
    }

    private static PitchResult findByNumber(final Pitch pitch) {
        return Arrays.stream(PitchResult.values())
            .filter(pitchResult -> {
                final List<Integer> numbers = pitchResult.numbers;
                return numbers.contains(pitch.getNumber());
            })
            .findFirst()
            .orElseGet(() -> MISS);
    }
}
