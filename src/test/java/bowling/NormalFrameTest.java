package bowling;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class NormalFrameTest {

    @Test
    void 턴이_끝났는지_확인() {
        NormalFrame normalFrame = new NormalFrame(1, Pin.from(1));
        normalFrame.bowl(Pin.from(2));
        Assertions.assertThat(normalFrame.isFinished()).isTrue();
    }

    @Test
    void 스트라이크를_치면_턴이_끝난다() {
        NormalFrame normalFrame = new NormalFrame(1, Pin.from(10));
        Assertions.assertThat(normalFrame.isFinished()).isTrue();
    }
}
