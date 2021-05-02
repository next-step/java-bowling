package bowling.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NormalFrameTest {
    @Test
    @DisplayName("1 프레임에 점수 입력은 2번만 가능하다.")
    void addScoreLimitTest() {
        NormalFrame normalFrame = new NormalFrame();

        normalFrame.addScore(3);
        normalFrame.addScore(4);

        assertThatThrownBy(() -> normalFrame.addScore(3))
                .isInstanceOf(IllegalStateException.class);
    }
}
