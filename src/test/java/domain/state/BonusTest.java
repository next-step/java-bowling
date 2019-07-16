package domain.state;

import domain.Pins;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BonusTest {

    private Bonus bonus;

    @BeforeEach
    void setUp() {
        bonus = new Bonus();
    }

    @Test
    @DisplayName("strike strike strike")
    void bowl() {
        State result = bonus.bowl(Pins.ALL).bowl(Pins.ALL).bowl(Pins.ALL);
        assertThat(result.isClosed()).isTrue();
        System.out.println(result.toSymbol());
    }

    @Test
    @DisplayName("초반 스타이크시 3번")
    void firstStrike() {
        State result = bonus.bowl(Pins.ALL).bowl(Pins.of(5)).bowl(Pins.of(3));
        assertThat(result.isClosed()).isTrue();
    }

    @Test
    @DisplayName("스페어 처리")
    void spareAndStrike() {
        State result = bonus.bowl(Pins.of(4)).bowl(Pins.of(6)).bowl(Pins.ALL);
        assertThat(result.isClosed()).isTrue();
    }

    @Test
    @DisplayName("Strike or spare 처리 못함")
    void normal() {
        State result = bonus.bowl(Pins.of(4)).bowl(Pins.of(3));
        assertThat(result.isClosed()).isTrue();
    }

    @Test
    @DisplayName("4번째 bowl 예외처리")
    void bowl4TimeException() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            bonus.bowl(Pins.ALL).bowl(Pins.ALL).bowl(Pins.ALL).bowl(Pins.ALL);
        });
    }

    @Test
    @DisplayName("no strike spare Exception")
    void exception() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            bonus.bowl(Pins.of(3)).bowl(Pins.of(4)).bowl(Pins.ALL);
        });
    }
}