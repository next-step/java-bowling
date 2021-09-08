package bowling;

import bowling.domain.Frame;
import bowling.domain.Status;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class FrameTest {
    Frame frame;

    @BeforeEach
    void init() {
        frame = new Frame(8);
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
        Frame frame = new Frame(10);
        assertThat(frame.isStrike()).isTrue();
    }

    @Test
    @DisplayName("스페어 확인")
    void isSpare() {
        frame.secondBall(2);
        assertThat(frame.isSpare()).isTrue();
    }
}
