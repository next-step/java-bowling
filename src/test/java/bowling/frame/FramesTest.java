package bowling.frame;

import bowling.HitNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {

    private Frames frames;

    @BeforeEach
    void setUp() {
        frames = Frames.of();
    }

    @Test
    void 프레임목록생성() {
        assertThat(Frames.of()).isEqualTo(Frames.of());
        assertThat(frames.getLastIndex()).isEqualTo(0);
    }

    @Test
    void 프레임이종료되었을때_다음프레임생성() {
        frames.play(HitNumber.of(10));
        assertThat(frames.getLastIndex()).isEqualTo(1);
    }

    @Test
    void 프레임이아직종료되지않았을때_현재프레임() {
        frames.play(HitNumber.of(3));
        assertThat(frames.getLastIndex()).isEqualTo(0);
    }

}
