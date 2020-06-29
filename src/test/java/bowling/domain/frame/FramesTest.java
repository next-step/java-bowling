package bowling.domain.frame;

import bowling.domain.frame.Frames;
import bowling.domain.frame.NormalFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class FramesTest {

    @Test
    @DisplayName("생성 테스트")
    void init() {
        assertThatCode(() -> Frames.init()).doesNotThrowAnyException();
        assertThat(Frames.init().getFrame(0) instanceof NormalFrame).isTrue();
    }

    @Test
    @DisplayName("투구 테스트 - 한 프레임이 끝나면 다음 프레임을 생성한다.")
    void bowl() {
        Frames frames = Frames.init();
        frames.bowl(2);
        frames.bowl(3);
        assertThat(frames.size()).isEqualTo(1);

        frames.bowl(4);
        assertThat(frames.size()).isEqualTo(2);
    }


    @Test
    @DisplayName("현재 프레임에서 마지막 투구인지 확인한다.")
    void isLastTryAtFrame() {
        Frames frames = Frames.init();
        frames.bowl(2);
        assertThat(frames.isLastTryAtFrame()).isFalse();

        frames.bowl(3);
        assertThat(frames.isLastTryAtFrame()).isTrue();
    }

}
