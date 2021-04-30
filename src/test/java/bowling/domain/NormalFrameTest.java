package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NormalFrameTest {
    @Test
    @DisplayName("1 프레임에 점수 입력은 2번만 가능하다.")
    void inputScoreLimitTest() {
        NormalFrame normalFrame = new NormalFrame();

        normalFrame.inputScore("3");
        normalFrame.inputScore("4");

        assertThatThrownBy(() -> normalFrame.inputScore("3"))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("남아있는 볼링핀이 없는 경우, 점수 입력이 불가능하다.")
    void cannotInputScoreIfNoPins() {
        NormalFrame normalFrame = new NormalFrame();

        normalFrame.inputScore("10");

        assertThatThrownBy(() -> normalFrame.inputScore("3"))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("0을 입력한 경우, 거터,미스인 '-'를 반환한다.")
    void inputStrikeValueTest() {
        NormalFrame normalFrame = new NormalFrame();

        assertThat(normalFrame.inputScore("0")).isEqualTo("-");
    }

    @Test
    @DisplayName("점수를 2번 입력 후, 그대로 출력되는지 확인한다.")
    void inputTwoTimes() {
        NormalFrame normalFrame = new NormalFrame();

        assertThat(normalFrame.inputScore("7")).isEqualTo("7");
        assertThat(normalFrame.inputScore("2")).isEqualTo("2");
    }

    @Test
    @DisplayName("점수를 2번 입력 후, 스페어면 /가 출력되는지 확인한다.")
    void inputSpareTest() {
        NormalFrame normalFrame = new NormalFrame();

        normalFrame.inputScore("8");

        assertThat(normalFrame.inputScore("2")).isEqualTo("/");
    }
}
