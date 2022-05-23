package bowling.domain;

import bowling.domain.state.Gutter;
import bowling.domain.state.Miss;
import bowling.domain.state.Spare;
import bowling.domain.state.Strike;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NormalFrameTest {
    @Test
    void 프레임_상태_테스트_스트라이크() {
        Frame normalFrame = new NormalFrame(1);
        assertThat(normalFrame.bowl(10).getScore())
                .isEqualTo(new Strike().getScore());
    }

    @Test
    void 프레임_상태_테스트_스페어() {
        Frame normalFrame = new NormalFrame(1);
        assertThat(normalFrame.bowl(3).bowl(7).getScore())
                .isEqualTo(new Spare(3,7).getScore());
    }

    @Test
    void 프레임_상태_테스트_미스() {
        Frame normalFrame = new NormalFrame(1);
        assertThat(normalFrame.bowl(3).bowl(3).getScore())
                .isEqualTo(new Miss(3,3).getScore());
    }

    @Test
    void 프레임_상태_테스트_거터() {
        Frame normalFrame = new NormalFrame(1);
        assertThat(normalFrame.bowl(0).bowl(0).getScore())
                .isEqualTo(new Gutter().getScore());
    }

}