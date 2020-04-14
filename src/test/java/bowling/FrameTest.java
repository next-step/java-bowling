package bowling;

import bowling.domain.FinalFrame;
import bowling.domain.Frame;
import bowling.domain.NormalFrame;
import bowling.domain.Ready;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class FrameTest {
    @Test
    @DisplayName("일반 Frame의 수는 10회 이상이 될 수 없다.")
    void assertNormalFrameNumber() {
        int no = 10;
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new NormalFrame(no);
        }).withMessage(NormalFrame.OVER_NORMAL_FRAME_NO_ERROR);
    }

    @Test
    @DisplayName("마지막 Frame의 수는 2회 이상이 될 수 없다.")
    void assertFinalFrameNumber() {
        int no = 2;
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new FinalFrame(no);
        }).withMessage(FinalFrame.OVER_FINAL_FRAME_NO_ERROR);
    }

    @Test
    @DisplayName("프레임의 첫 투구 점수는 0이상 10 이하여야 한다.")
    void assertFirstFalledPin() {
        Frame frame = new NormalFrame(1);
        int falledPin = 11;

        assertThatIllegalArgumentException().isThrownBy(() -> {
            frame.play(falledPin);
        }).withMessage(Frame.WRONG_FALLED_PIN);
    }

    @Test
    @DisplayName("프레임의 두번째 투구는 첫번째 투구와 합하여 10 이하여야 한다.")
    void assertSecondFalledPinl() {
        Frame frame = new NormalFrame(1);
        int firstFalledPin = 2;

        Frame secondFrame = frame.play(firstFalledPin);
        int secondFalledPin = 9;

        assertThatIllegalArgumentException().isThrownBy(() -> {
            secondFrame.play(secondFalledPin);
        }).withMessage("넘어뜨린 핀의 개수가 알맞지 않습니다.");
    }

    @Test
    @DisplayName("Frame 생성시 첫 상태는 Ready 이다.")
    void firstFrameState() {
        Frame frame = new NormalFrame(1);
        assertThat(frame.getStatus()).isEqualTo(new Ready());
    }

    @Test
    @DisplayName("다음 Frame을 생성한다. 생성된 프레임은 이전 Frame보다 no가 1이 크다 다 ")
    void nextFrame() {
        Frame frame = new NormalFrame(1);
        assertThat(frame.nextFrame().getNo()).isEqualTo(2);
    }

    @Test
    @DisplayName("Strike 했을 시 한번만 투구했어도 새로운 Frame을 생성한다.")
    void playStrike() {
        Frame frame = new NormalFrame(1);
        Frame next = frame.play(10);

        assertThat(next.getNo()).isEqualTo(2);
    }

    @Test
    @DisplayName("Strike 가 아닌 경우 두번 투구를 수행할 경우 Frame을 생성한다.")
    void playNotStrike() {
        Frame frame = new NormalFrame(1);

        Frame next = frame.play(1);
        assertThat(next.getNo()).isEqualTo(1);
        next = next.play(0);
        assertThat(next.getNo()).isEqualTo(2);
    }
}
