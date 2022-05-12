package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FramesTest {

    @DisplayName("Frames 크기가 최대(10)일 때 append 호출하면 예외")
    @Test
    void addNormalFrame_WhenCalledAtMaxSize_ThrowsIllegalStateException() {
        Frames frames = createFullNormalFrames();

        assertThatThrownBy(() -> frames.addNormalFrame(0, 0))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("Frames의 NormalFrame을 다 안 채우고 FinalFrame을 추가하면 예외")
    @Test
    void addFinalFrame_WhenNormalFrameNotFull_ThrowsIllegalStateException() {
        assertThatThrownBy(() -> new Frames().addFinalFrame(0, 0))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void addFinalFrame() {
        Frames frames = createFullNormalFrames();

        assertThatNoException().isThrownBy(() -> frames.addFinalFrame(0, 0));
    }

    @DisplayName("Frames의 FinalFrame을 추가 안하고 addExtra 호출하면 예외")
    @Test
    void addExtra_WhenFinalFrameIsNull_ThrowsIllegalStateException() {
        assertThatThrownBy(() -> new Frames().addExtra(0))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("Franes의 FinalFrame이 스트라이크/스페어 아닐 때 addExtra 호출하면 예외")
    @Test
    void addExtra_WhenFinalFrameNeitherStrikeNorSpare_ThrowsIllegalStateException() {
        Frames frames = createFullNormalFrames();

        frames.addFinalFrame(5, 4);

        assertThatThrownBy(() -> frames.addExtra(5))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void addExtra() {
        Frames frames = createFullNormalFrames();

        assertThatNoException().isThrownBy(() -> {
            frames.addFinalFrame(10, 0);
            frames.addExtra(0);
        });
    }

    private Frames createFullNormalFrames() {
        Frames frames = new Frames();
        for (int i = 0; i < 9; i++) {
            frames.addNormalFrame(0, 0);
        }
        return frames;
    }
}
