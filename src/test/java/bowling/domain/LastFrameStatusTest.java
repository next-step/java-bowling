package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LastFrameStatusTest {
    /**
     * 10 / 10 / 10
     * 10 / - / 10
     * 10 / 10 / -
     * 10 / 1 / 9
     * 9 / 1 / 10
     * 9 / 1 / 9
     * 9 / 1 / -
     * 1 / 1
     */
    @Test
    void doubleThrow() {
        assertThat(new LastFrameStatus(10, 10, 10).toString()).isEqualTo("X|X|X");
        assertThat(new LastFrameStatus(10, 10, 9).toString()).isEqualTo("X|X|9");
        assertThat(new LastFrameStatus(10, 10, 0).toString()).isEqualTo("X|X|-");
    }

    @Test
    void strikeAndSpare() {
        assertThat(new LastFrameStatus(10, 9, 1).toString()).isEqualTo("X|9|/");
    }

    @Test
    void strikeAndMiss() {
        assertThat(new LastFrameStatus(10, 9, 0).toString()).isEqualTo("X|9|-");
    }

    @Test
    void spareAndStrike() {
        assertThat(new LastFrameStatus(9, 1, 10).toString()).isEqualTo("9|/|X");
        assertThat(new LastFrameStatus(9, 1, 9).toString()).isEqualTo("9|/|9");
        assertThat(new LastFrameStatus(9, 1, 0).toString()).isEqualTo("9|/|-");
    }

    @Test
    void miss() {
        assertThat(new LastFrameStatus(8, 1, null).toString()).isEqualTo("8|1");
    }

    @Test
    void playing() {
        assertThat(new LastFrameStatus(null, null, null).toString()).isEqualTo("");
        assertThat(new LastFrameStatus(10, null, null).toString()).isEqualTo("X");
        assertThat(new LastFrameStatus(8, 2, null).toString()).isEqualTo("8|/");
        assertThat(new LastFrameStatus(8, null, null).toString()).isEqualTo("8");
        assertThat(new LastFrameStatus(8, 0, null).toString()).isEqualTo("8|-");
        assertThat(new LastFrameStatus(10, 10, null).toString()).isEqualTo("X|X");
        assertThat(new LastFrameStatus(10, 8, null).toString()).isEqualTo("X|8");
        assertThat(new LastFrameStatus(10, 0, null).toString()).isEqualTo("X|-");
    }

    private static class LastFrameStatus {
        private final Integer first;
        private final Integer second;
        private final Integer third;

        public LastFrameStatus(Integer first, Integer second, Integer third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }

        @Override
        public String toString() {
            if (first == null) {
                return "";
            }

            StringBuilder sb = new StringBuilder();
            sb.append(toNumberOrGutter(first));
            if (first != 10) {
                appendNext(sb, getNumberOrSign(first, second));
                return appendNext(sb, third).toString();
            }

            if (second == null) {
                return sb.toString();
            }

            appendNext(sb, second);

            if (second != 10) {
                return appendNext(sb, getNumberOrSign(second, third)).toString();
            }

            return appendNext(sb, third).toString();
        }

        private String getNumberOrSign(Integer aNumber, Integer nextNumber) {
            if (nextNumber != null && aNumber + nextNumber == 10) {
                return "/";
            }
            if (nextNumber == null) {
                return null;
            }
            return toNumberOrGutter(nextNumber);
        }

        private StringBuilder appendNext(StringBuilder sb, Integer next) {
            if (next != null) {
                appendNext(sb, toNumberOrGutter(next));
            }
            return sb;
        }
        private StringBuilder appendNext(StringBuilder sb, String next) {
            if (next != null) {
                sb.append("|");
                sb.append(next);
            }
            return sb;
        }

        private String toNumberOrGutter(Integer fallingPins) {
            if (fallingPins == null) {
                return "";
            }
            if (fallingPins == 0)
                return "-";
            if (fallingPins == 10)
                return "X";
            return String.valueOf(fallingPins);
        }
    }
}
