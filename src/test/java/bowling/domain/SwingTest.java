package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SwingTest {

    @DisplayName("잘못된 점수 범위 입력")
    @ValueSource(ints = {-1, 11})
    @ParameterizedTest
    void addInvalidScore(int score) {
        Swing swing = new Swing();
        assertThatIllegalArgumentException()
                .isThrownBy(() -> swing.addScore(score));
    }

    @DisplayName("strike 테스트")
    @Test
    void strikeTest() {
        Swing swing = new Swing();
        swing.addScore(10);
        assertTrue(swing.isStrike());
    }

    @DisplayName("spare 테스트")
    @CsvSource({"0,10", "1,9", "2,8"})
    @ParameterizedTest
    void spareTest(int score1, int score2) {
        Swing swing = new Swing();
        swing.addScore(score1);
        swing.addScore(score2);
        assertTrue(swing.isSpare());
    }
}
