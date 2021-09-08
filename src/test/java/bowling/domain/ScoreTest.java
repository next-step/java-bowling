package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {
    @Test
    @DisplayName("스페어 확인")
    void strike() {
        assertTrue(Score.isStrike(Score.TEN));
    }

    @Test
    @DisplayName("스페어 확인")
    void spare() {
        assertTrue(Score.isSpare(Score.valueOf(5), Score.valueOf(5)));
        assertFalse(Score.isSpare(Score.valueOf(2), Score.valueOf(5)));
    }

    @Test
    @DisplayName("핀 개수로 찾기")
    void plus() {
        int plus = Score.valueOf(5).plus(Score.valueOf(5));
        assertEquals(plus, 10);
    }

    @Test
    @DisplayName("핀 개수로 찾기")
    void findByPins_exception() {
        assertThrows(IllegalArgumentException.class, () -> Score.valueOf(100));
    }

}