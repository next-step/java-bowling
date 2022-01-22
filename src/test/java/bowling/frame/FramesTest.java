package bowling.frame;

import bowling.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    @Test
    void 스트라이크_다음_프레임_생성() {
        Frames frames = Frames.first();
        frames.bowl(new Pins(10));

        assertThat(frames.recentFrameNo()).isEqualTo(2);
        assertThat(frames.size()).isEqualTo(2);
    }

    @Test
    void 스페어_다음_프레임_생성() {
        Frames frames = Frames.first();
        frames.bowl(new Pins(1));
        frames.bowl(new Pins(9));

        assertThat(frames.recentFrameNo()).isEqualTo(2);
        assertThat(frames.size()).isEqualTo(2);
    }

    @Test
    void 투구_중_상태_현재_프레임_유지() {
        Frames frames = Frames.first();
        frames.bowl(new Pins(1));

        assertThat(frames.recentFrameNo()).isEqualTo(1);
        assertThat(frames.size()).isEqualTo(1);
    }

    @DisplayName("언제까지 볼링게임을 진행할 것인가")
    @Test
    void 게임이_끝나지_않은지_확인() {
        Frames frames = Frames.first();
        frames.bowl(new Pins(10));

        assertThat(frames.hasNextBowl()).isTrue();
    }

    @DisplayName("언제까지 볼링게임을 진행할 것인가")
    @Test
    void 게임이_끝난지_확인() {
        Frames frames = Frames.first();
        frames.bowl(new Pins(10));
        frames.bowl(new Pins(10));
        frames.bowl(new Pins(10));
        frames.bowl(new Pins(10));
        frames.bowl(new Pins(10));
        frames.bowl(new Pins(10));
        frames.bowl(new Pins(10));
        frames.bowl(new Pins(10));
        frames.bowl(new Pins(10));
        // 10 프레임
        frames.bowl(new Pins(10));
        frames.bowl(new Pins(10));
        frames.bowl(new Pins(10));

        assertThat(frames.hasNextBowl()).isFalse();
    }
}