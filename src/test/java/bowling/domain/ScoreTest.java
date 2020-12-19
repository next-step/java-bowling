package bowling.domain;

import org.junit.jupiter.api.Test;

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
        private final Integer first;
        private final Integer second;
        private final Integer third;

        public Score(Integer first, Integer second, Integer third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }

        public Integer toInt() {
            if (first == null) {
                return null;
            }

            if (first == 10) {
                return sumAll();
            }

            if (second == null) {
                return first;
            }

            if (first + second == 10) {
                return sumAll();
            }
            return first + second;

        }

        private Integer sumAll() {
            if (third == null) {
                return null;
            }
            return first + second + third;
        }
    }
}
