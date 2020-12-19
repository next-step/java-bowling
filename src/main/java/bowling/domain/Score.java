package bowling.domain;

import bowling.util.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

class Score {
    private final List<Integer> values;

    public Score(Integer... fallingPins) {
        values = Arrays.stream(fallingPins)
                .filter(Objects::nonNull)
                .collect(toList());
    }

    public Integer toInt() {
        if (first() == null) {
            return null;
        }

        if (first() == 10) {
            return sumAll();
        }

        if (second() == null) {
            return first();
        }

        if (first() + second() == 10) {
            return sumAll();
        }
        return first() + second();

    }

    private Integer first() {
        return Lists.getOrNull(values, 0);
    }

    private Integer second() {
        return Lists.getOrNull(values, 1);
    }

    private Integer third() {
        return Lists.getOrNull(values, 2);
    }

    private Integer sumAll() {
        if (third() == null) {
            return null;
        }
        return values.stream().mapToInt(value -> value).sum();
    }
}
