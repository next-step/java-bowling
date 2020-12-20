package bowling.domain.frame;

import bowling.domain.point.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    @Test
    @DisplayName("첫 프레임에서 게임을 진행했을때 잘 넘어가는지 확인을 한다.")
    void create() {
        Frames frames = Frames.init();
        frames.pitch(Point.valueOf(0));
        frames.pitch(Point.valueOf(0));

        assertThat(frames.getCurrentFramePosition()).isEqualTo(1);
    }

    @Test
    @DisplayName("스트라이크를 9번 쳤을때 마지막 게임인지 확인한다.")
    void pitchLastGame() {
        Frames frames = Frames.init();
        for (int i = 0; i < 9; i++) {
            frames.pitch(Point.valueOf(10));
        }

        assertThat(frames.getCurrentFramePosition()).isEqualTo(9);
    }

    @Test
    @DisplayName("스트라이크를 11번 쳤을때 게임은 종료를 하지 않는다 .")
    void pitchGameNotEnd() {
        Frames frames = Frames.init();
        for (int i = 0; i < 11; i++) {
            frames.pitch(Point.valueOf(10));
        }

        assertThat(frames.isFinished()).isFalse();
    }
    @Test
    @DisplayName("스트라이크를 12번 쳤을때 게임은 종료 한다.")
    void pitchGameEnd() {
        Frames frames = Frames.init();
        for (int i = 0; i < 12; i++) {
            frames.pitch(Point.valueOf(10));
        }

        assertThat(frames.isFinished()).isTrue();
    }
}