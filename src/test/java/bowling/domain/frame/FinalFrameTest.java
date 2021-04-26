package bowling.domain.frame;

import bowling.domain.pin.FinalPins;
import bowling.domain.pin.Pin;
import bowling.domain.pin.Pins;
import bowling.exception.NoNextFrameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FinalFrameTest {

    @Test
    @DisplayName("FrameScore를 받아 마지막 프레임을 생성한다.")
    void create() {
        // given
        final FrameScore frameScore = new FrameScore();

        // when
        final FinalFrame finalFrame = FinalFrame.from(frameScore);

        // then
        assertThat(finalFrame).isEqualTo(FinalFrame.from(frameScore));
    }

    @Test
    @DisplayName("FinalFrame의 다음 프레임은 생성되지 않는다.")
    void nextFrame() {
        // given
        final FrameScore frameScore = new FrameScore();
        final FinalFrame finalFrame = FinalFrame.from(frameScore);

        // when
        // then
        assertThatThrownBy(finalFrame::createNextFrame)
                .isInstanceOf(NoNextFrameException.class)
                .hasMessage(NoNextFrameException.FINAL_FRAME_CANNOT_CREATE_NEXT_FRAME);
    }

    @Test
    @DisplayName("FinalFrame은 FinalFrame이다.")
    void isFinalFrame() {
        assertThat(FinalFrame.from(new FrameScore()).isFinalFrame()).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"10,10,10,X|X|X", "10,0,10,X|-|X", "0,10,10|-|/|X", "9,1,3,9|/|3", "3,4,0,3|4", "0,0,0,-|-"})
    @DisplayName("FinalFrame은 조건별로 출력이 다르다.")
    void status(int firstPinCount, int secondPinCount, int thirdPinCount, String expected) {
        // given
        final Pin firstPin = new Pin(firstPinCount);
        final Pin secondPin = new Pin(secondPinCount);
        final Pin thirdPin = new Pin(thirdPinCount);
        final Pins pins = new FinalPins(firstPin, secondPin, thirdPin);
        final Frame frame = FinalFrame.from(new FrameScore(pins));

        // when
        final String status = frame.status();

        // then
        assertThat(status).isEqualTo(expected);
    }
}
