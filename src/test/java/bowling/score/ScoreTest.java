package bowling.score;

import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

public class ScoreTest {

    @Test
    @DisplayName("MISS 스코어 생성 테스트")
    void osMissTest() {
        assertThatCode(
                () -> Score.ofMiss(5)
        ).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("SPARE 스코어 생성 테스트")
    void ofSpareTest() {
        assertThatCode(
                () -> Score.ofSpare()
        ).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("STRIKE 스코어 생성 테스트")
    void ofStrikeTest() {
        assertThatCode(
                () -> Score.ofStrike()
        ).doesNotThrowAnyException();
    }
}
