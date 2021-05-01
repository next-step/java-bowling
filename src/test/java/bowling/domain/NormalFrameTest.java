package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
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

    @Test
    @DisplayName("점수를 2번 입력 후, 스페어면 /가 출력되는지 확인한다.")
    void inputSpareTest() {
        NormalFrame normalFrame = new NormalFrame();

        normalFrame.addScore(8);

        assertThat(normalFrame.addScore(2)).isEqualTo("/");
    }

    @Test
    @DisplayName("0을 입력한 경우, 거터,미스인 '-'를 반환한다.")
    void inputStrikeValueTest() {
        NormalFrame normalFrame = new NormalFrame();

        assertThat(normalFrame.addScore(0)).isEqualTo("-");
    }
}
