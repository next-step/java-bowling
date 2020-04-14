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
    @DisplayName("프레임의 첫 투구 점수는 0이상 10 이하여야 한다.")
    void assertFirstFalledPin() {
        Frame frame = new NormalFrame(1);
        int falledPin = 11;

        assertThatIllegalArgumentException().isThrownBy(() -> {
            frame.play(falledPin);
        }).withMessage(Frame.WRONG_FALLED_PIN);
    }

//    @Test
//    @DisplayName("프레임의 두번째 투구는 첫번째 투구와 합하여 10 이하여야 한다.")
//    void assertSecondFalledPin() {
//        Frame frame = new NormalFrame(1);
//        int firstFalledPin = 2;
//        int secondFalledPin = 9;
//
//        frame.play(firstFalledPin);
//
//        assertThatIllegalArgumentException().isThrownBy(() -> {
//            frame.play(secondFalledPin);
//        }).withMessage("넘어뜨린 핀의 개수가 알맞지 않습니다.");
//    }

    @Test
    @DisplayName("9프레임이 안채워졌을 경우 다음 프레임은 일반 Frame을 생성해야 한다.")
    void getNextNormalFrame() {
        Frame frame = new NormalFrame(2);

        assertThat(frame.getNext()).isInstanceOf(NormalFrame.class);
    }

    @Test
    @DisplayName("9 프레임의 일반 Frame을 모두 진행하고 나면 다음 프레임은 마지막 Frame을 생성해야 한다.")
    void getNextFinalFrame() {
        Frame frame = new NormalFrame(9);

        assertThat(frame.getNext()).isInstanceOf(FinalFrame.class);
    }

    @Test
    @DisplayName("Strike 했을 시 한번만 투구했어도 새로운 Frame을 생성한다.")
    void playStrike() {
        Frame frame = new NormalFrame(1);

        frame.play(10);

        Frame nextFrame = frame.getNext();
        assertThat(nextFrame.getNo()).isEqualTo(2);
    }

    @Test
    @DisplayName("Strike 가 아닌 경우 두번 투구를 수행할 경우 Frame을 생성한다.")
    void playNotStrike() {
        Frame frame = new NormalFrame(1);

        frame.play(1);
        frame.play(0);

        Frame nextFrame = frame.getNext();
        assertThat(nextFrame.getNo()).isEqualTo(2);
    }
}
