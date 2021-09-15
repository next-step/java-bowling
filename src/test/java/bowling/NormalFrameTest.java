package bowling;

import bowling.domain.Frame;
import bowling.domain.NormalFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class NormalFrameTest {
    NormalFrame frame;

    @BeforeEach
    void init() {
        frame = new NormalFrame(8, 1);
    }

    @Test
    @DisplayName("첫번째 볼")
    void firstBall() {
        assertThat(frame.firstScore()).isEqualTo(8);
    }

    @Test
    @DisplayName("두번째 볼")
    void secondBall() {
        frame.secondBall(2);
        assertThat(frame.secondScore()).isEqualTo(2);
    }

    @Test
    @DisplayName("스트라이크 확인")
    void isStrike() {
        NormalFrame frame = new NormalFrame(10, 1);
        assertThat(frame.isStrike()).isTrue();
    }

    @Test
    @DisplayName("스페어 확인")
    void isSpare() {
        frame.secondBall(2);
        assertThat(frame.isSpare()).isTrue();
    }

    @Test
    @DisplayName("다음 프레임")
    void nextFrame() {
        frame.secondBall(1);
        NormalFrame nextFrame = (NormalFrame) frame.nextFrame(10);
        assertThat(nextFrame).isEqualTo(new NormalFrame(10, 2));
    }

    @Test
    @DisplayName("다음 프레임 에러")
    void nextFrameException() {
        assertThatThrownBy(() ->
                frame.nextFrame(3))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
