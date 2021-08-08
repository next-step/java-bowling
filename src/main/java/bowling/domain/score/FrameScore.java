package bowling.domain.score;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FrameScore extends Score {
    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 10;
    private static final Map<Integer, FrameScore> CACHED =
            IntStream.rangeClosed(MIN_VALUE, MAX_VALUE)
                    .boxed()
                    .collect(
                            Collectors.toMap(
                                    iValue -> iValue,
                                    FrameScore::new)
                    );

    private FrameScore(final int score) {
        super(score);
    }

    public static FrameScore max() {
        return of(MAX_VALUE);
    }

    public static FrameScore of(int score) {
        if (!CACHED.containsKey(score)) {
            throw new IllegalArgumentException("유효하지 않은 스코어 입니다.");
        }
        return CACHED.get(score);
    }
}
