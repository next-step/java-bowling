package domain.bowling;

import domain.Pins;
import domain.state.State;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LastFrameSetTest {

    private LastFrameSet lastFrameSet;

    @BeforeEach
    void setUp() {
        lastFrameSet = new LastFrameSet();
    }

    @Test
    @DisplayName("strike strike strike")
    void bowl() {
        lastFrameSet.bowl(Pins.ALL).bowl(Pins.ALL).bowl(Pins.ALL);
        State result = lastFrameSet.getFrameState();
        assertThat(result.isClosed()).isTrue();
    }

    @Test
    @DisplayName("초반 스타이크시 3번")
    void firstStrike() {
        lastFrameSet.bowl(Pins.ALL).bowl(Pins.of(5)).bowl(Pins.of(3));
        State result = lastFrameSet.getFrameState();
        assertThat(result.isClosed()).isTrue();
    }

    @Test
    @DisplayName("스페어 처리")
    void spareAndStrike() {
        lastFrameSet.bowl(Pins.of(4)).bowl(Pins.of(6)).bowl(Pins.ALL);
        State result = lastFrameSet.getFrameState();
        assertThat(result.isClosed()).isTrue();
    }

    @Test
    @DisplayName("Strike or spare 처리 못함")
    void normal() {
        lastFrameSet.bowl(Pins.of(4)).bowl(Pins.of(3));
        State result = lastFrameSet.getFrameState();
        assertThat(result.isClosed()).isTrue();
    }

    @Test
    @DisplayName("4번째 bowl 예외처리")
    void bowl4TimeException() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            lastFrameSet.bowl(Pins.ALL).bowl(Pins.ALL).bowl(Pins.ALL).bowl(Pins.ALL);
        });
    }

    @Test
    @DisplayName("no strike spare Exception")
    void exception() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            lastFrameSet.bowl(Pins.of(3)).bowl(Pins.of(4)).bowl(Pins.ALL);
        });
    }
}