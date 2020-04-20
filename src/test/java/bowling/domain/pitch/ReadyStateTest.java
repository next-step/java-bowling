package bowling.domain.pitch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ReadyStateTest {
    private ReadyState readyState;

    @BeforeEach
    void setUp() {
        readyState = new ReadyState();
    }

    @DisplayName("볼을 리턴한다")
    @Test
    void returnBowl() {
        assertThat(readyState.bowl(Pin.valueOf(1)))
                .isExactlyInstanceOf(FirstBowlState.class);
    }

    @DisplayName("스트라이크를 리턴한다")
    @Test
    void returnStrike() {
        assertThat(readyState.bowl(Pin.valueOf(10)))
                .isExactlyInstanceOf(StrikeState.class);
    }
}
