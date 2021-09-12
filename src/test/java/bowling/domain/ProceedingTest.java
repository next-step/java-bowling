package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class ProceedingTest {

    @DisplayName("두번째 투구에서 쓰러트린 핀의 수와 첫번째 투구에서 쓰러트린 핀의 수의 합이 10을 초과하는 경우 예외가 발생한다.")
    @Test
    public void invalidSecondBowlTest() {
        FrameState proceeding = new Ready().bowl(PinCount.EIGHT);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> proceeding.bowl(PinCount.THREE))
                .withMessageContainingAll("8", "3");
    }

    @DisplayName("두번째 투구에서 첫번째 투구에서 남은 핀을 모두 쓰러트린 경우, 프레임은 Spare 상태가 된다.")
    @Test
    public void spareTest() {
        FrameState proceeding = new Ready().bowl(PinCount.EIGHT);
        assertThat(proceeding.bowl(PinCount.TWO))
                .isExactlyInstanceOf(Spare.class);
    }

    @DisplayName("두번째 투구에서 첫번째 투구에서 남은 핀을 모두 쓰러트리지 못한 경우, 프레임은 Miss 상태가 된다.")
    @Test
    public void missTest() {
        FrameState proceeding = new Ready().bowl(PinCount.EIGHT);
        assertThat(proceeding.bowl(PinCount.ONE))
                .isExactlyInstanceOf(Miss.class);
    }

}