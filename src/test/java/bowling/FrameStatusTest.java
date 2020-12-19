package bowling;

import org.junit.jupiter.api.Test;

public class FrameStatusTest {
    @Test
    void strike() {
        assertThat(new FrameStatus(10).toString()).isEqualTo("X");
    }
}
