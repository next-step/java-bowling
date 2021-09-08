package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ScoresTest {

    @Test
    @DisplayName("생성")
    void create() {
        assertDoesNotThrow(Scores::new);
    }

    @Test
    @DisplayName("볼링공 굴리기")
    void roll() {
        Scores scores = new Scores();
        assertDoesNotThrow(() -> scores.roll(Score.ONE));
    }
}
