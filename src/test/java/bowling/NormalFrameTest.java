package bowling;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class NormalFrameTest {

    @Test
    void 프레임_생성() {
        assertThat(NormalFrame.of(1, 5).getScore()).isEqualTo(5);
    }

    @Test
    void 프레임은_1번부터_9번() {
        assertThatThrownBy(() -> NormalFrame.of(11, 0))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 현재_프레임에_턴이_끝났는지_확인() {
        assertThat(NormalFrame.of(1, 5).isFinished()).isFalse();
        assertThat(NormalFrame.of(1,5).bowl(3).isFinished()).isFalse();
    }
}
