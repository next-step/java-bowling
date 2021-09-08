package bowling.domain;

import bowling.domain.exception.IncorrectNumberOfPinsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ScoresTest {


    @Test
    @DisplayName("생성")
    void create() {
        assertDoesNotThrow(() -> new Scores());
    }

    @Test
    @DisplayName("볼링공 굴리기")
    void roll() {
        Scores scores = new Scores();
        assertDoesNotThrow(() -> scores.roll(Score.ONE));
    }

    @Test
    @DisplayName("볼링공을 굴려서 핀을 10개이상 굴릴 수 없다.")
    void rollNumberOfPins_exception() {
        Scores scores = new Scores();
        scores.roll(Score.TEN);
        assertThrows(IncorrectNumberOfPinsException.class, () -> scores.roll(Score.ONE));
    }
}
