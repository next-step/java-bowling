package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("스코어 테스트")
public class ScoreTests {

    @DisplayName("스코어 생성 테스트")
    @Test
    public void generateTest() {
        assertThatCode(() -> Score.of(5));
        assertThatCode(Score::ofAllPins);
        assertThatCode(Score::ofZeroPins);
        assertThatCode(() -> Score.of(Score.of(5), Score.of(6)));
    }

    @DisplayName("스코어 생성 오류 테스트")
    @Test
    public void generateAbnormalTest() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Score.of(-2));
    }

    @DisplayName("스코어 생성 오류 테스트")
    @Test
    public void addTest() {
        assertThat(Score.of(6).add(Score.ofAllPins())).isEqualTo(Score.of(16));
        assertThat(Score.of(6).add(4)).isEqualTo(Score.of(10));
    }
}
