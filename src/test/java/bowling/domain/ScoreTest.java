package bowling.domain;

import bowling.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {
    @Test
    void miss() {
        assertThat(new Score(0, null, null).toInt()).isEqualTo(0);
        assertThat(new Score(8, 1, null).toInt()).isEqualTo(9);
    }

    @Test
    void strike() {
        assertThat(new Score(10, null, null).toInt()).isNull();
        assertThat(new Score(10, 8, null).toInt()).isNull();
        assertThat(new Score(10, 8, 1).toInt()).isEqualTo(19);
    }

    @Test
    void spare() {
        assertThat(new Score(8, 2, null).toInt()).isNull();
        assertThat(new Score(8, 2, 1).toInt()).isEqualTo(11);
    }

    private static class Score {
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
}
