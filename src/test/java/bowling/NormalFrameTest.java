package bowling;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    @Test
    void 프레임을_생성한다() {
        NormalFrame firstFrame = NormalFrame.first();
        assertThat(firstFrame).isNotNull();
    }

}