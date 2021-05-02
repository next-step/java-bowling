package bowling;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalFrameTest {
    public static final FinalFrame FRAME = FinalFrame.of();

    @Test
    void 프레임생성() {
        assertThat(FRAME).isEqualTo(FinalFrame.of());
    }

    @Test
    void 다음프레임생성() {
        assertThat(FRAME.next(5)).isEqualTo(FRAME);
        assertThat(FRAME.next(10)).isEqualTo(FRAME);
    }


}
