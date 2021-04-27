package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {

    @DisplayName("프레임을 생성한다")
    @Test
    void initTest() {
        Frames frames = Frames.init();
        assertThat(frames.size()).isEqualTo(10);
    }
}
