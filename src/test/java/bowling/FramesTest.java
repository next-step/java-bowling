package bowling;

import bowling.frame.Frame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FramesTest {
    private Frames frames = new Frames();
    private Frame first, second, third, last;

    @BeforeEach
    void setUp() {
        first = frames.roll(10);
        second = frames.roll(5);
        second = frames.roll(5);
        third = frames.roll(10);
        frames.roll(5);
        frames.roll(4);
        frames.roll(10);
        frames.roll(10);
        frames.roll(10);
        frames.roll(10);
        frames.roll(10);
        last = frames.roll(5);
        last = frames.roll(4);
    }

    @Test
    @DisplayName("프레임 연결")
    void chainFrames() {
        assertThat(second.getPrevFrame()).isEqualTo(first);
        assertThat(third.getPrevFrame()).isEqualTo(second);
    }

    @Test
    @DisplayName("마지막 프레임의 next추가시 에러")
    void nextFrameWithFinalFrameException() {
        frames = new Frames();
        frames.roll(10);
        frames.roll(10);
        frames.roll(10);
        frames.roll(10);
        frames.roll(10);
        frames.roll(10);
        frames.roll(10);
        frames.roll(10);
        frames.roll(10);
        last = frames.roll(10);
        last = frames.roll(10);
        last = frames.roll(10);
        assertThrows(IllegalArgumentException.class, () -> frames.roll(1));
    }

    @Test
    @DisplayName("게임 점수 계산")
    void calculateFrameScore() {
        assertThat(first.getScoreSum()).isEqualTo(20);
        assertThat(second.getScoreSum()).isEqualTo(20);
        assertThat(third.getScoreSum()).isEqualTo(19);
        assertThat(last.getScoreSum()).isEqualTo(9);

        frames = new Frames();
        first = frames.roll(10);
        second = frames.roll(5);
        second = frames.roll(5);
        third = frames.roll(8);
        third = frames.roll(1);
        frames.roll(10);
        frames.roll(10);
        frames.roll(10);
        frames.roll(10);
        frames.roll(10);
        frames.roll(10);
        last = frames.roll(10);
        last = frames.roll(10);
        last = frames.roll(10);

        assertThat(first.getScoreSum()).isEqualTo(20);
        assertThat(second.getScoreSum()).isEqualTo(18);
        assertThat(third.getScoreSum()).isEqualTo(9);
        assertThat(last.getScoreSum()).isEqualTo(30);

        frames = new Frames();
        first = frames.roll(10);
        second = frames.roll(5);
        second = frames.roll(5);
        third = frames.roll(8);
        third = frames.roll(1);
        frames.roll(10);
        frames.roll(10);
        frames.roll(10);
        frames.roll(10);
        frames.roll(10);
        frames.roll(10);
        last = frames.roll(10);
        last = frames.roll(9);

        assertThat(first.getScoreSum()).isEqualTo(20);
        assertThat(second.getScoreSum()).isEqualTo(18);
        assertThat(third.getScoreSum()).isEqualTo(9);
        assertThat(last.getScoreSum()).isEqualTo(19);
    }

    @Test
    @DisplayName("Frame이 종료되었는지 확인")
    void isEndOfFrame() {
        frames = new Frames();
        first = frames.roll(10);
        assertThat(frames.lastOfFrames()).isEqualTo(2);
        second = frames.roll(9);
        assertThat(frames.lastOfFrames()).isEqualTo(2);
        second = frames.roll(1);
        assertThat(frames.lastOfFrames()).isEqualTo(3);

        frames.roll(10);
        frames.roll(10);
        frames.roll(10);
        frames.roll(10);
        frames.roll(10);
        frames.roll(10);
        frames.roll(10);

        last = frames.roll(10);
        assertThat(frames.lastOfFrames()).isEqualTo(10);
        last = frames.roll(10);
        assertThat(frames.lastOfFrames()).isEqualTo(10);
        last = frames.roll(5);
        assertThat(frames.lastOfFrames()).isEqualTo(11);
    }
}