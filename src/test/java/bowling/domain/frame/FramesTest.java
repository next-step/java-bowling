package bowling.domain.frame;

import bowling.domain.pin.BowlCount;
import bowling.domain.pin.Pins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {
    private Frames frames;

    @BeforeEach
    void setUp() {
        frames = Frames.create();
    }

    @DisplayName("첫 Frame은 NormalFrame 임을 확인할 수 있다.")
    @Test
    void createNormalFrame() {
        assertThat(frames.getFrames().get(0)).isInstanceOf(NormalFrame.class);
    }

    @DisplayName("마지막 Frame은 FinalFrame 임을 확인할 수 있다.")
    @Test
    void createFinalFrame() {
        BowlCount bowlCount = new BowlCount(10);
        Pins strikePins = Pins.of().knockOver(bowlCount);

        for (int count = 0; count < 9; count++) {
            frames.bowl(strikePins);
        }

        assertThat(frames.getFrames().get(9)).isInstanceOf(FinalFrame.class);
    }
}