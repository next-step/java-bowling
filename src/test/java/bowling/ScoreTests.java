package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("Score 테스트")
public class ScoreTests {

    @DisplayName("Score 생성 테스트")
    @Test
    public void generateScoreTest() {
        assertThatCode(() -> Score.of(5));
    }

    @DisplayName("Score 생성 오류 테스트")
    @Test
    public void generateScoreAbnormalTest() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Score.of(-5))
                .withMessageContaining("Score must be greater than zero");
    }
}
