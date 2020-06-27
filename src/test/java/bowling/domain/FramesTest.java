package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatCode;

class FramesTest {

    @Test
    @DisplayName("생성 테스트")
    void init() {
        assertThatCode(() -> Frames.init()).doesNotThrowAnyException();
    }
}
