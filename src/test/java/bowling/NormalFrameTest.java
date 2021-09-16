package bowling;

import bowling.domain.NormalFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @Test
    @DisplayName("MISS일 경우 점수")
    void calculateMissScore() {
        frame.secondBall(1);
        assertThat(frame.calculateFrameScore()).isEqualTo(9);
    }

    @Test
    @DisplayName("spare일 경우 점수")
    void calculateSpareScore() {
        frame.secondBall(2);
        frame.nextFrame(10);
        assertThat(frame.calculateFrameScore()).isEqualTo(20);
    }

    @Test
    @DisplayName("strike일 경우 점수")
    void calculateStrikeScore() {
        frame.secondBall(2);
        NormalFrame nextFrame = (NormalFrame) this.frame.nextFrame(10);
        NormalFrame nextNextFrame = (NormalFrame) nextFrame.nextFrame(7);
        nextNextFrame.secondBall(3);
        assertThat(nextFrame.calculateFrameScore()).isEqualTo(20);
    }

    @Test
    @DisplayName("2연속 strike일 경우 점수")
    void calculateConsecutiveStrikeScore() {
        frame.secondBall(2);
        NormalFrame nextFrame = (NormalFrame) this.frame.nextFrame(10);
        NormalFrame nextNextFrame = (NormalFrame) nextFrame.nextFrame(10);
        NormalFrame nextNextNextFrame = (NormalFrame) nextNextFrame.nextFrame(10);
        assertThat(nextFrame.calculateFrameScore()).isEqualTo(30);
    }
}
