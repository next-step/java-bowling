package domain.state;

import domain.Pins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OngoingTest {

    private Ongoing ongoing;

    @BeforeEach
    void setUp() {
        ongoing = new Ongoing(Pins.of(5));
    }

    @Test
    @DisplayName("to Open")
    void open() {
        State bowl = ongoing.bowl(Pins.of(4));
        assertThat(bowl instanceof Open).isTrue();
    }

    @Test
    @DisplayName("to Spares")
    void spares() {
        State bowl = ongoing.bowl(Pins.of(5));
        assertThat(bowl instanceof Spares).isTrue();
    }
}