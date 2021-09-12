package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class ProceedingTest {

    @DisplayName("두번째 투구에서 쓰러트린 핀의 수와 첫번째 투구에서 쓰러트린 핀의 수의 합이 10을 초과하는 경우 예외가 발생한다.")
    @Test
    public void invalidSecondBowlTest() {
        FrameState state = new Ready();
        FrameState proceeding = state.bowl(new FallenPinCount(8));
        assertThatIllegalArgumentException()
                .isThrownBy(() -> proceeding.bowl(new FallenPinCount(3)))
                .withMessageContainingAll("8", "3");
    }

    @DisplayName("두번째 투구에서 첫번째 투구에서 남은 핀을 모두 쓰러트린 경우, 프레임은 Spare 상태가 된다.")
    @Test
    public void spareTest() {
        FrameState state = new Ready();
        FrameState proceeding = state.bowl(new FallenPinCount(8));
        assertThat(proceeding.bowl(new FallenPinCount(2)))
                .isExactlyInstanceOf(Spare.class);
    }

    @DisplayName("두번째 투구에서 첫번째 투구에서 남은 핀을 모두 쓰러트리지 못한 경우, 프레임은 Miss 상태가 된다.")
    @Test
    public void missTest() {
        FrameState state = new Ready();
        FrameState proceeding = state.bowl(new FallenPinCount(8));
        assertThat(proceeding.bowl(new FallenPinCount(1)))
                .isExactlyInstanceOf(Miss.class);
    }

}