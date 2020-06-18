package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class FrameTests {
    @DisplayName("ThrowResults를 전달받아서 객체를 생성할 수 있다.")
    @Test
    void createTest() {
        Frame frame = new Frame(new ThrowResults(Arrays.asList(new ThrowResult(2), new ThrowResult(3))));
        assertThat(frame)
                .isEqualTo(new Frame(new ThrowResults(Arrays.asList(new ThrowResult(2), new ThrowResult(3))), null));
    }
}
