package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class FinalFrameTest {

    @Test
    @DisplayName("초구를 던지지 않았을 경우, 초구를 던진다.")
    public void throwBowl_firstBowl() throws Exception {
        Frame frame = new FinalFrame().throwBowl(1);
        PinCounts pinCounts = frame.pinCounts();

        assertThat(pinCounts.pinCounts().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("초구를 던졌을 경우, 2구를 던진다.")
    public void throwBowl_secondBall() throws Exception {
        Frame firstThrown = new FinalFrame().throwBowl(1);
        Frame secondThrown = firstThrown.throwBowl("2");
        PinCounts pinCounts = secondThrown.pinCounts();

        assertThat(pinCounts.pinCounts().size()).isEqualTo(2);
    }

    @ParameterizedTest
    @CsvSource(value = {"10, 1, 1", "5, 5, 1", "0, 10 ,10"})
    @DisplayName("초구 스트라이크 또는 2구 스페어일 경우, 3구를 던진다.")
    public void throwBowl_thirdBowl(int firstPinCount, String secondPinCount, String thirdPinCount) throws Exception {
        Frame firstThrown = new FinalFrame().throwBowl(firstPinCount);
        Frame secondThrown = firstThrown.throwBowl(secondPinCount);
        Frame thirdThrown = secondThrown.throwBowl(thirdPinCount);
        PinCounts pinCounts = thirdThrown.pinCounts();

        assertThat(pinCounts.pinCounts().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("초구 스트라이크 또는 2구 스페어가 아닌 상황에서 3구를 던지려고 할 경우 예외가 발생한다.")
    public void throwBowl_thirdBowl() throws Exception {
        Frame firstThrown = new FinalFrame().throwBowl(5);
        FinalFrame secondThrown = (FinalFrame) firstThrown.throwBowl("4");
        assertThatIllegalArgumentException()
                .isThrownBy(() -> secondThrown.throwBowl("1"))
                .withMessage("3구를 던질수 없습니다.");
    }

    @Test
    @DisplayName("다음 차례가 존재하지 않으므로, 자신을 반환한다.")
    public void next() throws Exception {
        //given
        Frame frame = new FinalFrame();
        assertThat(frame.next()).isEqualTo(frame);
    }

    @ParameterizedTest
    @CsvSource(value = {"10, 1, 1", "5, 5, 1", "1, 1, null"})
    @DisplayName("해당 프레임이 완료됐을 경우, 참을 반환한다.")
    public void isFinished(int firstPinCount, String secondPinCount, String thirdPinCount) throws Exception {
        Frame firstThrown = new FinalFrame().throwBowl(firstPinCount);
        Frame lastThrown = firstThrown.throwBowl(secondPinCount);

        if (!thirdPinCount.equals("null")) {
            lastThrown = lastThrown.throwBowl(thirdPinCount);
        }

        assertThat(lastThrown.isFinished()).isTrue();
    }
}
