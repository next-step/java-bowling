package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Score 테스트")
public class ScoreTests {

    @DisplayName("Score 생성 테스트")
    @Test
    public void generateTest() {
        assertThatCode(() -> Score.of(5));
    }

    @DisplayName("Score 투구 생성 오류 테스트")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    public void generateAbnormalTest(final int score) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Score.of(score));
    }

    @DisplayName("Score 단순 합계 테스트")
    @Test
    public void sumTest() {
        assertThat(Score.sum(Arrays.asList(Score.of(6), Score.of(4)))).isEqualTo(10);
    }

    @DisplayName("Score 비교 테스트")
    @Test
    public void compareTest() {
        Score score = Score.of(7);
        assertTrue(score.isEqualsTo(7));
    }

    @DisplayName("Score 변환 테스트")
    @Test
    public void convertTest() {
        Score score = Score.of(7);
        assertThat(score.toTotalScore()).isEqualTo(TotalScore.of(7));
    }
}
