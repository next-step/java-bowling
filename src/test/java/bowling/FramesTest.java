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
        frames = Frames.init();
    }

    @Test
    @DisplayName("프레임들 사이즈 체크")
    void checkSizeOfFrames() {
        // when
        int size = frames.size();
        // then
        assertThat(size).isEqualTo(10);
    }

    @Test
    @DisplayName("스코어를 포함한 프레임")
    void checkScoreOfFrames() {
        // give
        Frames frames = Frames.initiate();
        Frames reFrames = frames.next();
        Frames nextFrame = frames.next();

        // when
        boolean isSame = frames.equals(reFrames);
        int size = nextFrame.size();
        // then
        assertThat(isSame).isFalse();
        assertThat(size).isEqualTo(2);
    }
}
