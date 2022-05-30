package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SubtotalTest {
    @Test
    void aggregateShouldSum() {
        Subtotal strike = new Subtotal(10, 2);
        strike = strike.aggregate(8);
        strike = strike.aggregate(2);
        assertThat(strike.score()).isEqualTo(20);
    }
}
