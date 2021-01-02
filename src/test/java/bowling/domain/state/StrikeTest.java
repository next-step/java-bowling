package bowling.domain.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StrikeTest {
    private Strike strike;

    @BeforeEach
    void setUp() {
        strike = new Strike();
    }

    @Test
    void isCalculatedTest() {
        assertThat(strike.isCalculated()).isFalse();
    }

    @Test
    void isOverTest() {
        assertThat(strike.isOver()).isTrue();
    }

    @Test
    void bowlTest() {
        assertThat(strike).isEqualTo(new Strike());
    }
}
