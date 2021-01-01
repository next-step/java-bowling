package bowling.domain.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SpareTest {
    private Spare spare;

    @BeforeEach
    void setUp() {
        spare = Spare.of(7,3);
    }

    @Test
    void isCalculatedTest() {
        assertThat(spare.isCalculated()).isFalse();
    }

    @Test
    void isOverTest() {
        assertThat(spare.isOver()).isTrue();
    }

    @Test
    void bowlTest() {
        assertThat(spare.bowl(10)).isEqualTo(new Strike());
    }
}
