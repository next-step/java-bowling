package bowling;

import bowling.domain.Pinfall;
import bowling.domain.state.FrameState;
import bowling.domain.state.FrameStateSpare;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class FrameStateSpareTest {

    @Test
    void When_Roll_Then_Exception() {
        FrameState state = new FrameStateSpare();
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> state.roll(new Pinfall(9)))
                .withMessage("공을 굴릴 수 없습니다");
    }

    @Test
    void When_isRollable_Then_False() {
        FrameState state = new FrameStateSpare();
        assertThat(state.isRollable()).isFalse();
    }
}
