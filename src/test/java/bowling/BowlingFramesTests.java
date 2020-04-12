package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("Bowling Frames Tests")
public class BowlingFramesTests {

    @DisplayName("생성 테스트")
    @Test
    public void generateTest() {
        assertThatCode(BowlingFrames::new).doesNotThrowAnyException();
    }
}
