package domain.state;

import domain.Pins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReadyTest {

    private Ready ready;

    @BeforeEach
    void setUp() {
        ready = new Ready();
    }

    @Test
    @DisplayName("Strike")
    void bowl() {
        State bowl = ready.bowl(Pins.of(10));
        assertThat(bowl instanceof Strike).isTrue();
    }

    @Test
    @DisplayName("Ongoing")
    void returnThis() {
        State bowl = ready.bowl(Pins.of(9));
        assertThat(bowl instanceof Ongoing).isTrue();
    }
}