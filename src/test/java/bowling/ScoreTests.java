package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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
                .isThrownBy(() -> Score.of(score));
    }

    @DisplayName("Score 두번째 투구 생성 테스트")
    @ParameterizedTest
    @CsvSource(value = {"5,5,false", "10,5,true", "3,7,true"})
    public void generateSecondPitchingTest(final int firstPitching, final int secondPitching, final boolean isLastFrame) {
        Score score = Score.of(firstPitching);
        assertThatCode(() -> score.secondPitching(secondPitching, isLastFrame));
    }

    @DisplayName("Score 두번째 투구 생성 오류 테스트")
    @ParameterizedTest
    @CsvSource(value = {"5,-1,false", "5,6,false", "5,11,false", "10,0,false", "10,11,true"})
    public void generateSecondPitchingAbnormalTest(final int firstPitching, final int secondPitching, final boolean isLastFrame) {
        Score firstScore = Score.of(firstPitching);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> firstScore.secondPitching(secondPitching, isLastFrame));
    }
}
