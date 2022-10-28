package bowling.domain.frame;

import bowling.domain.pin.FallenPin;
import bowling.domain.state.Ready;
import bowling.domain.state.Spare;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameTest {
    @DisplayName("최종프레임 초기화는 준비 상태로 생성된다")
    @Test
    void init() {
        assertThat(FinalFrame.init()).isEqualTo(new FinalFrame(List.of(new Ready())));
    }

    @DisplayName("투구하고 그 결과를 반환한다")
    @Test
    void bowl() {
        Frame result = finalFrame().bowl(FallenPin.of(2));

        FinalFrame expected = new FinalFrame(List.of(new Spare(FallenPin.of(8), FallenPin.of(2))));
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("첫번째 상태가 스트라이크고, 보너스 시도를 마쳤으면 최종프레임이 끝난다")
    @Test
    void finished_whenFirstStateIsStrikeAndBonusBowlingFinished() {
        Frame result = FinalFrame.init()
                .bowl(FallenPin.of(10))
                .bowl(FallenPin.of(9))
                .bowl(FallenPin.of(0));
        assertThat(result.isFinished()).isTrue();
    }

    @DisplayName("첫번째 상태가 스페어고, 보너스 시도를 마쳤으면 최종프레임이 끝난다")
    @Test
    void finished_whenFirstStateIsSpareAndBonusBowlingFinished() {
        Frame result = FinalFrame.init()
                .bowl(FallenPin.of(9))
                .bowl(FallenPin.of(1))
                .bowl(FallenPin.of(0));
        assertThat(result.isFinished()).isTrue();
    }

    @DisplayName("첫번째 상태가 스트라이크 또는 스페어가 아니면서 끝나면 최종프레임이 끝난다")
    @Test
    void finished_whenFirstStateIsFinishedBeingNotStrikeOrSpare() {
        Frame result = FinalFrame.init()
                .bowl(FallenPin.of(7))
                .bowl(FallenPin.of(2));
        assertThat(result.isFinished()).isTrue();
    }

    @DisplayName("첫번째 상태가 스트라이크이면 최종프레임은 끝나지 않는다.")
    @Test
    void notFinished_whenFirstStateIsStrike() {
        Frame result = FinalFrame.init()
                .bowl(FallenPin.of(10));
        assertThat(result.isFinished()).isFalse();
    }

    @DisplayName("첫번째 상태가 스트라이크이고 한 번더 시도했을 때 최종프레임은 끝나지 않는다.")
    @Test
    void notFinished_whenFirstStateIsStrikeAndTriesOneMore() {
        Frame result = FinalFrame.init()
                .bowl(FallenPin.of(10))
                .bowl(FallenPin.of(10));
        assertThat(result.isFinished()).isFalse();
    }

    @DisplayName("첫번째 상태가 스페어이면 최종프레임이 끝나지 않는다.")
    @Test
    void notFinished_whenFirstStateIsSpare() {
        Frame result = FinalFrame.init()
                .bowl(FallenPin.of(9))
                .bowl(FallenPin.of(1));
        assertThat(result.isFinished()).isFalse();
    }

    private Frame finalFrame() {
        return FinalFrame.init()
                .bowl(FallenPin.of(8));
    }
}
