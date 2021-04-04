package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PinCountsTest {

    @Test
    void add() {
        int pinCountInInt = 5;
        PinCounts pinCounts = new PinCounts();
        pinCounts.add(pinCountInInt);

        assertThat(pinCounts.counts()).containsExactly(new PinCount(pinCountInInt));
    }

    @Test
    void total_count() {
        int pinCountInIntAtFirstTry = 7;
        int pinCountInIntAtSecondTry = 2;
        PinCounts pinCounts = new PinCounts();
        pinCounts.add(pinCountInIntAtFirstTry);
        pinCounts.add(pinCountInIntAtSecondTry);

        assertThat(pinCounts.totalCount()).isEqualTo(pinCountInIntAtFirstTry + pinCountInIntAtSecondTry);
    }

    @Test
    void size() {
        int pinCountInInt = 4;
        PinCounts pinCounts = new PinCounts();
        pinCounts.add(pinCountInInt);

        assertThat(pinCounts.size()).isEqualTo(1);
    }

}
