package bowling.domain.frame;

import bowling.domain.pin.FallenPin;
import bowling.domain.state.Ready;
import bowling.domain.state.Spare;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class NormalFrameTest {
    @DisplayName("일반프레임 초기화는 준비 상태로 생성된다")
    @Test
    void init() {
        assertThat(NormalFrame.init()).isEqualTo(new NormalFrame(new Ready()));
    }

    @DisplayName("투구하고 그 결과를 반환한다")
    @Test
    void bowl() {
        Frame result = normalFrame().bowl(FallenPin.of(2));

        NormalFrame expected = new NormalFrame(new Spare(FallenPin.of(8), FallenPin.of(2)));
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("투구가 끝나면 일반프레임이 끝난다")
    @Test
    void finished_whenBowlingFinished() {
        Frame normalFrameWithMiss = normalFrame().bowl(FallenPin.of(1));
        Frame normalFrameWithSpare = normalFrame().bowl(FallenPin.of(2));
        Frame normalFrameWithStrike = NormalFrame.init().bowl(FallenPin.of(10));

        assertAll(
                () -> assertThat(normalFrameWithMiss.isFinished()).isTrue(),
                () -> assertThat(normalFrameWithSpare.isFinished()).isTrue(),
                () -> assertThat(normalFrameWithStrike.isFinished()).isTrue());
    }

    @DisplayName("투구가 끝나지 않으면 일반프레임이 끝나지 않는다.")
    @Test
    void notFinished_whenBowlingNotFinished() {
        assertThat(normalFrame().isFinished()).isFalse();
    }

    private Frame normalFrame() {
        return NormalFrame.init()
                .bowl(FallenPin.of(8));
    }
}
