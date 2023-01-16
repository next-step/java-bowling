package bowling.refactoring.domain.frame;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.SoftAssertions.*;

class FramesTest {

    @Test
    void 프레임생성테스트() {
        Frames frames = new Frames();
        frames.bowl(10);
        frames.bowl(10);
        frames.bowl(10);
        frames.bowl(10);
        frames.bowl(10);
        frames.bowl(10);
        frames.bowl(10);
        frames.bowl(10);
        frames.bowl(10);
        frames.bowl(10);
        frames.bowl(10);
        frames.bowl(10);

        assertSoftly(softly -> {
            assertThat(frames.frameList().size()).isEqualTo(10);
            assertThat(frames.frameList().get(8)).isInstanceOf(NormalFrame.class);
            assertThat(frames.frameList().get(9)).isInstanceOf(FinalFrame.class);
        });
    }
}
