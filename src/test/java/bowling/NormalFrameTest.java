package bowling;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NormalFrameTest {

    @Test
    void 턴이_끝났는지_확인() {
        Frame first = NormalFrame.first();
        first.bowl(Pin.from(2));
        assertThat(first.isFinished()).isFalse();
    }

    @Test
    void 스트라이크를_치면_턴이_끝난다() {
        Frame first = NormalFrame.first();
        first.bowl(Pin.from(10));
        assertThat(first.isFinished()).isTrue();
    }

    @Test
    void 미스_점수계산() {
        Frame first = NormalFrame.first();
        first.bowl(Pin.from(2));
        first.bowl(Pin.from(5));
        assertThat(first.getScores().getScore()).isEqualTo(7);
    }

    @Test
    void 스트라이크_점수계산_불가() {
        Frame first = NormalFrame.first();
        first.bowl(Pin.from(10));
        assertThatThrownBy(() -> first.getScores().getScore())
            .isInstanceOf(IllegalStateException.class);
    }
}
