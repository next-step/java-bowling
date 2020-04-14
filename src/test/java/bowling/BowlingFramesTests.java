package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Bowling Frames Tests")
public class BowlingFramesTests {

    @DisplayName("생성 테스트")
    @Test
    public void generateTest() {
        assertThatCode(BowlingFrames::new).doesNotThrowAnyException();
    }

    @DisplayName("투구 테스트")
    @Test
    public void bowlTest() {
        BowlingFrames bowlingFrames = new BowlingFrames();
        assertThatCode(() -> bowlingFrames.bowl(10)).doesNotThrowAnyException();
    }

    @DisplayName("게임 종료 테스트")
    @Test
    public void overTest() {
        BowlingFrames bowlingFrames = new BowlingFrames();
        IntStream.range(0, 11)
                .forEach(i -> bowlingFrames.bowl(10));

        assertFalse(bowlingFrames.isAllFrameOver());

        bowlingFrames.bowl(10);
        assertTrue(bowlingFrames.isAllFrameOver());
    }
}
