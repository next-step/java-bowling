package bowling.domain.frame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FramesTest {
    private static final int MAX_FRAME = 10;
    private static final int STRIKE = 10;

    private Frames frames;

    @BeforeEach
    void setUp() {
        frames = new Frames();
    }

    @Test
    void testFrame() {
        assertThat(frames.getFrames().size()).isEqualTo(10);
    }

    @Test
    @DisplayName("다음 프레임 가져오기 테스트")
    void testNextFrame() {
        rolledThird();

        assertThat(frames.getNextFrame().getIndex()).isEqualTo(4);
    }

    private void rolledThird() {
        frames.getNextFrame().roll(STRIKE);
        frames.getNextFrame().roll(STRIKE);
        frames.getNextFrame().roll(STRIKE);
    }

}
