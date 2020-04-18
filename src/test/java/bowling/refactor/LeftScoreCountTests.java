package bowling.refactor;

import bowling.LeftScoreCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("남은 스코어 카운트 테스트")
public class LeftScoreCountTests {

    @DisplayName("생성 테스트")
    @Test
    public void generateTest() {
        assertThatCode(() -> LeftScoreCount.of(2));
    }

    @DisplayName("생성 오류 테스트")
    @Test
    public void generateAbnormalTest() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> LeftScoreCount.of(-2));
    }

    @DisplayName("비교 테스트")
    @Test
    public void generateCompareTest() {
        LeftScoreCount leftScoreCount = LeftScoreCount.of(0);
        assertTrue(leftScoreCount.isEqualTo(0));
    }
}
