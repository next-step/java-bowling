package bowling.domain.frame;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

public class FramesTest {
    @Test
    void 생성_테스트() {
        // given
        Frames frames = Frames.init();
        List<Frame> frameList = frames.getFrames();
        // when & then
        assertThat(frameList.size()).isEqualTo(1);
        assertThat(frameList.get(0)).isInstanceOf(NormalFrame.class);
    }

    @Test
    void 다음_프레임_생성_테스트() {
        // given
        Frames frames = Frames.init();
        Frames frames2 = Frames.init();
        Frames frames3 = Frames.init();
        // when
        frames.bowl(3);
        frames.bowl(3);
        frames.bowl(3);

        frames2.bowl(3);
        frames2.bowl(7);
        frames2.bowl(3);

        frames3.bowl(10);
        frames3.bowl(3);

        assertThat(frames.getFrames().size()).isEqualTo(2);
        assertThat(frames2.getFrames().size()).isEqualTo(2);
        assertThat(frames3.getFrames().size()).isEqualTo(2);
    }

    @Test
    void 전체_게임_종료_테스트() {
        // given
        Frames frames = Frames.init();
        // when
        IntStream.range(0, 9).forEach(i -> {
            frames.bowl(10);
        });
        frames.bowl(3);
        frames.bowl(3);
        // then
        assertThat(frames.isAllDone()).isTrue();
    }

    @Test
    void 보너스_게임_종료_테스트_1() {
        // given
        Frames frames = Frames.init();
        // when
        IntStream.range(0, 9).forEach(i -> {
            frames.bowl(10);
        });
        frames.bowl(3);
        frames.bowl(7);
        frames.bowl(5);
        // then
        assertThat(frames.isAllDone()).isTrue();
    }

    @Test
    void 보너스_게임_종료_테스트_2() {
        // given
        Frames frames = Frames.init();
        // when
        IntStream.range(0, 10).forEach(i -> {
            frames.bowl(10);
        });
        frames.bowl(3);
        frames.bowl(7);
        // then
        assertThat(frames.isAllDone()).isTrue();
    }

    @Test
    void 보너스_게임_종료_테스트_3() {
        // given
        Frames frames = Frames.init();
        // when
        IntStream.range(0, 12).forEach(i -> {
            frames.bowl(10);
        });
        // then
        assertThat(frames.isAllDone()).isTrue();
    }

}
