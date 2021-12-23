package bowling;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {
    @Test
    void 첫번째_프레임_생성() {
        assertThat(NormalFrame.ofFirst()).isInstanceOf(NormalFrame.class);
    }

    @Test
    void 다음_프레임_생성() {
        assertThat(NormalFrame.ofFirst().next()).isInstanceOf(NormalFrame.class);
    }

    @Test
    void 마지막_프레임_생성() {
        assertThat(NormalFrame.ofFinal()).isInstanceOf(FinalFrame.class);
    }
}
