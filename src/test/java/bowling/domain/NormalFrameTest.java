package bowling.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class NormalFrameTest {
    @Test
    void isEnd() {
        NormalFrame normalFrame = NormalFrame.first(10);
        assertThat(normalFrame.isEnd()).isFalse();
    }
}
