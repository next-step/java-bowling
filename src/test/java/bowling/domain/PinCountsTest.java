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
}
