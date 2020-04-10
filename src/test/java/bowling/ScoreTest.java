package bowling;

import bowling.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;

public class ScoreTest {
    @DisplayName("스코어는 0 이상 10 이하의 숫자로만 생성")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 5, 9, 10})
    void createTest(int score) {
        assertThatCode(() -> {
            new Score(score);
        }).doesNotThrowAnyException();
    }
}