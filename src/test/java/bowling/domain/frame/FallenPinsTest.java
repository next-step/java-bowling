package bowling.domain.frame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FallenPinsTest {

    private FallenPins fallenPins;

    @BeforeEach
    void setUp() {
        fallenPins = new FallenPins();
        fallenPins.add(1);
        fallenPins.add(2);
        fallenPins.add(3);
    }

    @Test
    void size() {
        assertThat(fallenPins.size()).isEqualTo(3);
    }

    @Test
    void first() {
        assertThat(fallenPins.first()).isEqualTo(1);
    }

    @Test
    void add() {
        fallenPins.add(4);
        assertThat(fallenPins.size()).isEqualTo(4);
    }

    @Test
    void computeSum() {
        assertThat(fallenPins.computeSum()).isEqualTo(6);
    }
}
