package bowling.domain.frame;

import bowling.domain.HitNumber;
import bowling.domain.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {

    private static Frames frames;

    @BeforeEach
    void setUp() {
        frames = Frames.of();
    }

    @Test
    void 프레임목록생성() {
        assertThat(Frames.of()).isEqualTo(Frames.of());
        assertThat(frames.getLastIndex()).isEqualTo(1);
    }

    @Test
    void 프레임이종료되었을때_다음프레임생성() {
        frames.play(HitNumber.of(10));
        assertThat(frames.getLastIndex()).isEqualTo(2);
        System.out.println(frames);
    }

    @Test
    void 프레임이아직종료되지않았을때_현재프레임() {
        frames.play(HitNumber.of(3));
        assertThat(frames.getLastIndex()).isEqualTo(1);
    }

    @Test
    void 마지막프레임까지_올스트라이크() {
        Frames lastFrames = lastFrames();
        assertThat(lastFrames.isFinished()).isTrue();
        List<Score> scores = lastFrames.totalScores();
        assertThat(scores.get(scores.size() - 1)).isEqualTo(Score.of(300));
        System.out.println(frames);
    }

    public static Frames lastFrames() {
        Frames frames = Frames.of();
        for (int i = 0; i < 12; i++) {
            frames.play(HitNumber.of(10));
        }
        return frames;
    }
}
