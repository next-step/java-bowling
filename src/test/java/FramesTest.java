import game.Frame;
import game.Frames;
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
        first = Frame.of(10);
        frames.addFrame(first);

        second = Frame.of(5);
        frames.addFrame(second);
        second.addScore(5);

        third = Frame.of(10);
        frames.addFrame(third);

        last = Frame.finalOf(5);
        frames.addFrame(last);
        last.addScore(4);
    }

    @Test
    @DisplayName("프레임 연결")
    void chainFrames() {
        assertThat(first.getNextFrame()).isEqualTo(second);
        assertThat(second.getNextFrame()).isEqualTo(third);
    }

    @Test
    @DisplayName("마지막 프레임의 next추가시 에러")
    void nextFrameWithFinalFrameException() {
        Frames frames = new Frames();
        Frame frame = Frame.finalOf(10);
        frames.addFrame(frame);
        assertThrows(IllegalArgumentException.class, () -> frames.addFrame(Frame.of(1)));
    }

    @Test
    @DisplayName("게임 점수 계산")
    void calculateFrameScore() {
        assertThat(first.getScoreSum()).isEqualTo(20);
        assertThat(second.getScoreSum()).isEqualTo(20);
        assertThat(third.getScoreSum()).isEqualTo(19);
        assertThat(last.getScoreSum()).isEqualTo(9);

        first = Frame.of(10);
        second = Frame.of(5);
        second.addScore(5);
        third = Frame.of(8);
        third.addScore(1);
        last = Frame.finalOf(10);
        last.addBonus(10);
        last.addBonus(10);

        frames = new Frames();
        frames.addFrame(first);
        frames.addFrame(second);
        frames.addFrame(third);
        frames.addFrame(last);

        assertThat(first.getScoreSum()).isEqualTo(20);
        assertThat(second.getScoreSum()).isEqualTo(18);
        assertThat(third.getScoreSum()).isEqualTo(9);
        assertThat(last.getScoreSum()).isEqualTo(30);

        last = Frame.finalOf(10);
        last.addBonus(9);

        assertThat(first.getScoreSum()).isEqualTo(20);
        assertThat(second.getScoreSum()).isEqualTo(18);
        assertThat(third.getScoreSum()).isEqualTo(9);
        assertThat(last.getScoreSum()).isEqualTo(19);
    }
}
