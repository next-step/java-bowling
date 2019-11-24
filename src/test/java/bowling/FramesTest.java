package bowling;

import bowling.domain.Frame;
import bowling.domain.Frames;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {

    private Frames frames;

    @BeforeEach
    void setUp() {
        // give
        frames = new Frames();
    }

    @Test
    @DisplayName("프레임들 사이즈 체크")
    void checkSizeOfFrames() {
        // when
        int size = frames.size();
        // then
        assertThat(size).isEqualTo(10);
    }
}
