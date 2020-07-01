package bowling.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("Score 테스트")
public class ScoreTest {

    @DisplayName("Score 투구 생성 테스트")
    @Test
    public void generateFirstPitchingTest() {
        assertThatCode(() -> Score.of(5));
    }

    @DisplayName("Score 투구 생성 오류 테스트")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    public void generateFirstPitchingAbnormalTest(final int score) {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> Score.of(score));
    }
}