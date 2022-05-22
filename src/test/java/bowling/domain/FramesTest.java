package bowling.domain;

import static bowling.domain.Frames.BOWLING_FRAMES_TOTAL;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FramesTest {

    @Test
    void createTest() {
        Frames frames = new Frames();

        assertThat(frames).isNotNull();
        assertThat(frames.get()).isEmpty();
    }

    @DisplayName("프레임들 객체에서 볼링게임 프레임 12개를 생성한다.")
    @Test
    void createTest2() {
        Frames frames = new Frames();

        frames.create();
        assertThat(frames.get()).hasSize(BOWLING_FRAMES_TOTAL);
    }

    @DisplayName("프레임들 생성 후 현재프레임은 가장 앞 프레임이다.")
    @Test
    void createTest3() {
        Frames frames = new Frames();

        frames.create();
        assertThat(frames.getCurrent()).isSameAs(frames.get().get(0));
        assertThat(frames.getCurrent()).isNotSameAs(frames.get().get(BOWLING_FRAMES_TOTAL-1));
    }

}
