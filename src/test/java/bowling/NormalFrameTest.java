package bowling;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {
    public static final NormalFrame FRAME = NormalFrame.of();

    @Test
    void 프레임생성() {
        assertThat(FRAME).isEqualTo(NormalFrame.of());
    }

    @Test
    void 다음프레임확인() {
        assertThat(FRAME.next(5)).isInstanceOf(NormalFrame.class);
        assertThat(FRAME.next(10)).isInstanceOf(FinalFrame.class);
    }
}
