package bowling.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FrameTest {

    @Test
    @DisplayName("프레임 점수 입력")
    void createFrame() {
        assertThat(Frame.from(10)).isInstanceOf(Frame.class);
    }

    @Test
    @DisplayName("점수가 10점을 초과할 경우 예외 처리")
    void exceptScoreLimit() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> Frame.from(11));
    }

    @Test
    @DisplayName("점수가 0점 아래인 경우 예외 처리")
    void exceptScoreBelowZero() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> Frame.from(-1));
    }

}