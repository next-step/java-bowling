package bowling.refactor;

import bowling.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("스코어 테스트")
public class ScoreTests {

    @DisplayName("스코어 생성 테스트")
    @Test
    public void generateTest() {
        assertThatCode(() -> Score.of(5));
    }

    @DisplayName("스코어 생성 오류 테스트")
    @Test
    public void generateAbnormalTest() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Score.of(-2));
    }
}
