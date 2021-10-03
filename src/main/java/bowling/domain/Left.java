package bowling.domain;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Left {
    private static final int MIN = 0;

    private static final int MAX = 2;

    private static final Map<Integer, Left> leftMap =
            Collections.unmodifiableMap(IntStream.rangeClosed(MIN, MAX)
            .boxed()
            .collect(Collectors.toMap(Function.identity(), Left::new)));

    public static final Left ZERO = Left.of(MIN);
    public static final Left ONE = Left.of(1);
    public static final Left TWO = Left.of(MAX);

    private final int left;

    public static Left of(final int inputLeft) {
        Left left = leftMap.get(inputLeft);
        if (Objects.isNull(left)) {
            throw new IllegalArgumentException("left 범위를 벗어났습니다. left : " + inputLeft);
        }
        return left;
    }

    private Left(int left) {
        this.left = left;
    }

    public Left play() {
        return Left.of(left - 1);
    }

    public boolean isFinished() {
        return Objects.equals(this.left, MIN);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Left left1 = (Left) o;
        return left == left1.left;
    }

    @Override
    public int hashCode() {
        return Objects.hash(left);
    }
}