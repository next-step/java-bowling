package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    @Test
    void 생성() {
        Frames frames = new Frames();

        assertThat(frames.frames()).hasSize(1);
        assertThat(frames.frames().get(0)).isInstanceOf(NormalFrame.class);
    }

    @Test
    void 현재_프레임_번호_얻기() {
        Frames frames = new Frames();

        assertThat(frames.currentFrameNumber()).isEqualTo(1);
    }

    @Test
    void 게임진행_현재프레임_종료시() {
        Frames frames = new Frames();
        frames.bowl(new Pin(10));

        assertThat(frames.frames()).hasSize(2);
    }

    @Test
    void 게임진행_현재프레임_계속진행시() {
        Frames frames = new Frames();
        frames.bowl(new Pin(5));

        assertThat(frames.frames()).hasSize(1);
    }
}