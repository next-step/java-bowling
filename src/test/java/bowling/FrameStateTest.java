package bowling;

import bowling.domain.FrameResult;
import bowling.domain.PinNumber;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FrameStateTest {

    @Test
    @DisplayName("첫 번째 투구에서 모든 핀을 쓰러트리면 스트라이크다.")
    void strike() {
        String strike = FrameResult.eachResult(new PinNumber(10));
        Assertions.assertThat(strike).isEqualTo(FrameResult.STRIKE.frameResult());
    }

    @Test
    @DisplayName("핀을 하나도 쓰러트리지 못하며 거터다.")
    void gutter() {
        String gutter = FrameResult.eachResult(new PinNumber(0));
        Assertions.assertThat(gutter).isEqualTo(FrameResult.GUTTER.frameResult());
    }

    @Test
    @DisplayName("첫 번째와 두 번째 투구를 합쳐서 핀을 모두 쓰러트리면 스페어다.")
    void spare() {
        String spare = FrameResult.pairResult(new PinNumber(7), new PinNumber(3));
        Assertions.assertThat(spare).isEqualTo(FrameResult.SPARE.frameResult());
    }
}
