package bowling.domain.pitch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class ReadyTest {
    private Ready ready;

    @BeforeEach
    void setUp() {
        ready = new Ready();
    }

    @DisplayName("볼을 리턴한다")
    @Test
    void returnBowl() {
        assertThat(ready.bowl(Pin.valueOf(1)))
                .isExactlyInstanceOf(FirstBowl.class);
    }

    @DisplayName("스트라이크를 리턴한다")
    @Test
    void returnStrike() {
        assertThat(ready.bowl(Pin.valueOf(10)))
                .isExactlyInstanceOf(Strike.class);
    }
}
