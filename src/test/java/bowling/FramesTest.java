package bowling;

import bowling.domain.IllegalBallThrownException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FramesTest {
    @Test
    void illegalPlayerName() {
        Assertions.assertThatThrownBy(() -> new Frames("ABCD"))
                .isInstanceOf(IllegalPlayerNameException.class);
    }

    @Test
    void isFinish() {
        Frames frames = new Frames("ABC");
        frames.start();
        for (int i = 1; i <= 12; i++) {
            assertThat(frames.isFinish()).isFalse();
            frames.throwBall(10);
        }

        assertThat(frames.isFinish()).isTrue();
    }

    @DisplayName("게임이 끝났는데 공을 던지면 예외가 발생한다")
    @Test
    void isOverThrow() {
        Frames frames = new Frames("ABC");
        frames.start();
        for (int i = 1; i <= 12; i++) {
            frames.throwBall(10);
        }

        assertThatThrownBy(() -> frames.throwBall(10))
                .isInstanceOf(IllegalBallThrownException.class);
    }

    @DisplayName("프레임이 끝나지 않으면 같은 프레임을 리턴한다")
    @Test
    void getCurrentFrameNumber() {
        Frames frames = new Frames("ABC");
        frames.start();
        assertThat(frames.getCurrentFrameNumber()).isEqualTo(1);
        frames.throwBall(1);
        assertThat(frames.getCurrentFrameNumber()).isEqualTo(1);
    }

    @DisplayName("프레임이 끝나면 다음 프레임을 리턴한다")
    @Test
    void throwBallAndNextFrame() {
        Frames frames = new Frames("ABC");
        frames.start();
        assertThat(frames.getCurrentFrameNumber()).isEqualTo(1);
        frames.throwBall(10);
        assertThat(frames.getCurrentFrameNumber()).isEqualTo(2);
    }
}
