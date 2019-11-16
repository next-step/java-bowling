package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {
    @Test
    @DisplayName("index로 해당 Frame찾아 오는지 테스트")
    void frameByIndex() {
        Frames frames = new Frames();
        Frame frame = frames.frameByIndex(0);

        assertThat(frame.getFrameNumber()).isEqualTo(1);
    }
}
