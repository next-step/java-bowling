package bowling.domain.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NotBowledTest {
    private NotBowled notBowled;

    @BeforeEach
    void setUp() {
        notBowled = NotBowled.init();
    }

    @Test
    void isCalculatedTest() {
        assertThat(notBowled.isCalculated()).isFalse();
    }

    @Test
    void isOverTest() {
        assertThat(notBowled.isOver()).isFalse();
    }

    @Test
    void bowlTest() {
        assertThat(notBowled.bowl(5)).isEqualTo(BowledOnce.of(5));
    }
}
