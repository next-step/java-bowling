package domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FrameFactoryTest {

    @Test
    @DisplayName("생성된 개수 확인")
    void size() {
        Frames frames = FrameFactory.createFrames();
        List<Frame> framesList = frames.getFrames();
        assertThat(framesList.size()).isEqualTo(10);
    }

}