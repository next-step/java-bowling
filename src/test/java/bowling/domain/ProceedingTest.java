package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

}