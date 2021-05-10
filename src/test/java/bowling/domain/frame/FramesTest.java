package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FramesTest {

    @DisplayName("Frames 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        Frames frames = Frames.initialize();

        assertThat(frames).isNotNull();
    }
}