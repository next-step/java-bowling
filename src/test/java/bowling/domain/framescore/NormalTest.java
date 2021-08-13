package bowling.domain.framescore;

import bowling.domain.score.TurnScores;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class NormalTest {
    @ValueSource(booleans = {
            true,
            false
    })
    @DisplayName("Normal의 isCompleted는 출력 여부와 같다.")
    @ParameterizedTest
    void isNotShowScoreValueTest(boolean completed) {
        assertThat(
                Normal.of(TurnScores.empty(), completed).isShowScoreValue()
        ).isEqualTo(completed);
    }

}