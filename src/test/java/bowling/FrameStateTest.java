package bowling;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FrameStateTest {

    @Test
    @DisplayName("첫 번째 투구에서 모든 핀을 쓰러트리면 스트라이크다.")
    void strike() {
        String strike = FrameState.eachState(new PinNumber(10));
        Assertions.assertThat(strike).isEqualTo(FrameState.STRIKE.getFrameState());
    }

    @Test
    @DisplayName("핀을 하나도 쓰러트리지 못하며 거터다.")
    void gutter() {
        String gutter = FrameState.eachState(new PinNumber(0));
        Assertions.assertThat(gutter).isEqualTo(FrameState.GUTTER.getFrameState());
    }

    @Test
    @DisplayName("첫 번째와 두 번째 투구를 합쳐서 핀을 모두 쓰러트리면 스페어다.")
    void spare() {
        String spare = FrameState.pairState(new PinNumber(7), new PinNumber(3));
        Assertions.assertThat(spare).isEqualTo(FrameState.SPARE.getFrameState());
    }
}
