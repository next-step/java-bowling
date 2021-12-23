package bowling;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalFrameTest {
    @Test
    void 마지막프레임이_맞는지_확인() {
        assertThat(new FinalFrame().isFinalFrame()).isTrue();
        assertThat(new FinalFrame().isBeforeFinalFrame()).isFalse();
    }
}
