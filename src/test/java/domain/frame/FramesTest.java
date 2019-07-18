package domain.frame;

import domain.Pins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    private Frames frames;

    @BeforeEach
    void setUp() {
        frames = FrameFactory.createFrames();
    }

    @Test
    @DisplayName("스트라이크시 다음 프레임 이동")
    void moveFrame() {
        FrameIndex currentFrameIndex = frames.getCurrentFrameIndex();
        assertThat(currentFrameIndex.value()).isEqualTo(0);

        Frames result = frames.bowl(Pins.ALL);

        FrameIndex updatedCurrentFrameIndex = result.getCurrentFrameIndex();
        assertThat(updatedCurrentFrameIndex.value()).isEqualTo(1);
    }

    @Test
    @DisplayName("끝내지 못했을시 제자리")
    void notMoveFrame() {
        FrameIndex currentFrameIndex = frames.getCurrentFrameIndex();
        assertThat(currentFrameIndex.value()).isEqualTo(0);

        Frames result = frames.bowl(Pins.of(5));

        FrameIndex updatedCurrentFrameIndex = result.getCurrentFrameIndex();
        assertThat(updatedCurrentFrameIndex.value()).isEqualTo(0);
    }

    @Test
    @DisplayName("스페어 처리 이동")
    void spareFrame() {
        FrameIndex currentFrameIndex = frames.getCurrentFrameIndex();
        assertThat(currentFrameIndex.value()).isEqualTo(0);

        frames.bowl(Pins.of(5));
        frames.bowl(Pins.of(5));

        FrameIndex updatedCurrentFrameIndex = frames.getCurrentFrameIndex();
        assertThat(updatedCurrentFrameIndex.value()).isEqualTo(1);
    }
}