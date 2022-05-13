package bowling.model.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class FrameFactoryTest {

    private final FrameFactory frameFactory = new FrameFactory();

    @Test
    @DisplayName("프레임들의 타입과 사이즈를 확인한다")
    void checkNormalFrame() {
        List<Frame> frames = frameFactory.create();
        assertAll(
                () -> assertThat(frames).element(0).isInstanceOf(NormalFrame.class),
                () -> assertThat(frames).element(1).isInstanceOf(NormalFrame.class),
                () -> assertThat(frames).element(2).isInstanceOf(NormalFrame.class),
                () -> assertThat(frames).element(3).isInstanceOf(NormalFrame.class),
                () -> assertThat(frames).element(4).isInstanceOf(NormalFrame.class),
                () -> assertThat(frames).element(5).isInstanceOf(NormalFrame.class),
                () -> assertThat(frames).element(6).isInstanceOf(NormalFrame.class),
                () -> assertThat(frames).element(7).isInstanceOf(NormalFrame.class),
                () -> assertThat(frames).element(8).isInstanceOf(NormalFrame.class),
                () -> assertThat(frames).element(9).isInstanceOf(LastFrame.class),
                () -> assertThat(frames).hasSize(10)
        );
    }

}