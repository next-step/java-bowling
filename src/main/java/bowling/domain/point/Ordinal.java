package bowling.domain.point;

import java.util.Arrays;
import java.util.function.Function;

import static bowling.Messages.WARNING_ORDINAL_NOT_FOUND_MATCHED_ORDINAL;

public enum Ordinal {
    FIRST(points -> points.getFirstPoint()),
    SECOND(points -> points.getSecondPoint()),
    THIRD(points -> points.getThirdPoint()),
    FOURTH(points -> points.getFourthPoint());

    private Function<Points, Integer> expression;

    Ordinal(Function<Points, Integer> expression) {
        this.expression = expression;
    }

    public static int getPoint(Ordinal ordinal, Points points) {
        return match(ordinal)
                .expression
                .apply(points);
    }

    private static Ordinal match(Ordinal ordinal) {
        return Arrays.stream(Ordinal.values())
                .filter(it -> it.equals(ordinal))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(WARNING_ORDINAL_NOT_FOUND_MATCHED_ORDINAL));
    }
}