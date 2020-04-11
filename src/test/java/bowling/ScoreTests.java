package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("Score 테스트")
public class ScoreTests {

    @DisplayName("Score 첫번째 투구 생성 테스트")
    @Test
    public void generateFirstPitchingTest() {
        assertThatCode(() -> Score.of(5));
    }

    @DisplayName("Score 첫번째 투구 생성 오류 테스트")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    public void generateFirstPitchingAbnormalTest(final int score) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Score.of(score))
                .withMessageContaining("Score must be greater than zero and lower than 10.");
    }

    @DisplayName("Score 두번째 투구 생성 테스트")
    @Test
    public void generateSecondPitchingTest() {
        Score score = Score.of(5);
        assertThatCode(() -> score.secondPitching(5));
    }

    @DisplayName("Score 두번째 투구 생성 오류 테스트")
    @ParameterizedTest
    @ValueSource(ints = {-1, 6, 11})
    public void generateSecondPitchingAbnormalTest(final int score) {
        Score firstScore = Score.of(5);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> firstScore.secondPitching(score))
                .withMessageContaining("must be greater than zero and lower than 10.");
    }
}
