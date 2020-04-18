package bowling.domain.frame;

import bowling.domain.state.Ready;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.Constants.WRONG_FELLED_PIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertAll;

public class FrameTest {
    @Test
    @DisplayName("일반 Frame의 수는 10회 이상이 될 수 없다.")
    void assertNormalFrameNumber() {
        int frameNumber = 10;
        assertThatIllegalArgumentException().isThrownBy(() -> {
            NormalFrame.create(frameNumber);
        }).withMessage(FrameNumber.OVER_NORMAL_FRAME_NO_ERROR);
    }

    @Test
    @DisplayName("프레임의 첫 투구 점수는 0이상 10 이하여야 한다.")
    void assertFirstFelledPin() {
        Frame frame = NormalFrame.create(1);
        int felledPin = 11;

        assertThatIllegalArgumentException().isThrownBy(() -> {
            frame.play(felledPin);
        }).withMessage(WRONG_FELLED_PIN);
    }

    @Test
    @DisplayName("프레임의 두번째 투구는 첫번째 투구와 합하여 10 이하여야 한다.")
    void assertSecondFelledPin() {
        Frame frame = NormalFrame.create(1);
        int firstFelledPin = 2;
        int secondFelledPin = 9;

        frame.play(firstFelledPin);

        assertThatIllegalArgumentException().isThrownBy(() -> {
            frame.play(secondFelledPin);
        }).withMessage(WRONG_FELLED_PIN);
    }

    @Test
    @DisplayName("Frame을 처음 생성할 시에 Ready 상태를 가진다.")
    void frameFirstState() {
        Frame normalFrame = NormalFrame.create(1);
        Frame finalFrame = FinalFrame.create();

        assertAll(
                () -> assertThat(normalFrame.getState()).isInstanceOf(Ready.class),
                () -> assertThat(finalFrame.getState()).isInstanceOf(Ready.class)
        );
    }

    @Test
    @DisplayName("9프레임이 안채워졌을 경우 다음 프레임은 일반 Frame을 생성해야 한다.")
    void getNextNormalFrame() {
        Frame frame = NormalFrame.create(2);

        assertThat(frame.getNext()).isInstanceOf(NormalFrame.class);
    }

    @Test
    @DisplayName("9 프레임의 일반 Frame을 모두 진행하고 나면 다음 프레임은 마지막 Frame을 생성해야 한다.")
    void getNextFinalFrame() {
        Frame frame = NormalFrame.create(9);

        assertThat(frame.getNext()).isInstanceOf(FinalFrame.class);
    }

    @Test
    @DisplayName("Strike 했을 시 한번만 투구했어도 새로운 Frame을 생성한다.")
    void playStrike() {
        Frame frame = NormalFrame.create(1);

        frame.play(10);

        Frame nextFrame = frame.getNext();
        assertThat(nextFrame.getFrameNumber()).isEqualTo(2);
    }

    @Test
    @DisplayName("Strike 가 아닌 경우 두번 투구를 수행할 경우 Frame을 생성한다.")
    void playNotStrike() {
        Frame frame = NormalFrame.create(1);

        frame.play(1);
        frame.play(0);

        Frame nextFrame = frame.getNext();
        assertThat(nextFrame.getFrameNumber()).isEqualTo(2);
    }

    @Test
    @DisplayName("마지막 프레임(FinalFrame)의 경우, Spare가 나올 경우 isEndedFrame은 false이다. 후에 1번의 투구를 더 할 수 있다.")
    void isEndedFrameSpare() {
        Frame frame = FinalFrame.create();

        frame.play(5);
        frame.play(5);

        assertThat(frame.isEndedFrame()).isFalse();

        frame.play(5);

        assertThat(frame.isEndedFrame()).isTrue();
    }

    @Test
    @DisplayName("마지막 프레임(FinalFrame)의 경우, Strike 나올 경우 isEndedFrame은 false이다. 후에 2번의 투구를 더 할 수 있다.")
    void isEndedFrameStrike() {
        Frame frame = FinalFrame.create();

        frame.play(10);

        assertThat(frame.isEndedFrame()).isFalse();

        frame.play(5);
        frame.play(1);

        assertThat(frame.isEndedFrame()).isTrue();
    }
}
