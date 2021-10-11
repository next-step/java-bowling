package bowling.domain;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Left {
    ZERO(0),
    ONE(1),
    TWO(2);

    private static final int MIN = 0;

    private static final Map<Integer, Left> leftMap =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(left -> left.left, Function.identity())));

    private final int left;

    Left(int left) {
        this.left = left;
    }

    public Left play() {
        Left left = leftMap.get(this.left - 1);
        if (Objects.isNull(left)) {
            throw new IllegalArgumentException("left 범위를 벗어났습니다. left : " + (this.left - 1));
        }
        return left;
    }

    public boolean isFinished() {
        return Objects.equals(this.left, MIN);
    }
}
