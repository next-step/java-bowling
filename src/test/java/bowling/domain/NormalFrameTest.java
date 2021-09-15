package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NormalFrameTest {

    @DisplayName("종료된 프레임의 bowl 메서드 호출 시 예외가 발생한다.")
    @Test
    public void cannotBowlOfFinishedState() {
        Frame frame = new NormalFrame();
        frame.bowl(PinCount.TWO);
        frame.bowl(PinCount.THREE);
        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> frame.bowl(PinCount.FIVE));
    }

    @DisplayName("두번째 투구에서 쓰러트린 핀의 수와 첫번째 투구에서 쓰러트린 핀의 수의 합이 10을 초과하는 경우 예외가 발생한다.")
    @Test
    public void invalidSecondBowlTest() {
        Frame frame = new NormalFrame();
        frame.bowl(PinCount.EIGHT);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> frame.bowl(PinCount.THREE))
                .withMessageContainingAll("8", "3");
    }

    @DisplayName("스트라이크인 경우 프레임은 종료된다.")
    @Test
    public void normalFrameStrikeFinishTest() {
        Frame frame = new NormalFrame();
        assertThat(frame.isFinished()).isFalse();
        frame.bowl(PinCount.TEN);
        assertThat(frame.isFinished()).isTrue();
    }

    @DisplayName("첫번째와 두번째 투구가 모두 끝난 경우 프레임은 종료된다.")
    @Test
    public void normalFrameFinishTest() {
        Frame frame = new NormalFrame();
        assertThat(frame.isFinished()).isFalse();
        frame.bowl(PinCount.THREE);
        assertThat(frame.isFinished()).isFalse();
        frame.bowl(PinCount.FOUR);
        assertThat(frame.isFinished()).isTrue();
    }

}