package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.pin.Pins;
import bowling.exception.NoNextFrameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FinalFrameTest {

    @Test
    @DisplayName("Pins를 받아 마지막 프레임을 생성한다.")
    void create() {
        // given
        final Pins pins = Pins.create();

        // when
        final FinalFrame finalFrame = FinalFrame.from(pins);

        // then
        assertThat(finalFrame).isEqualTo(FinalFrame.from(Pins.create()));
    }

    @Test
    @DisplayName("마지막 프레임의 다음 프레임을 요청할 경우 예외가 발생한다.")
    void nextFrame() {
        // given
        final FinalFrame finalFrame = FinalFrame.from(Pins.create());

        // when
        // then
        assertThatThrownBy(() -> finalFrame.nextFrame())
                .isInstanceOf(NoNextFrameException.class)
                .hasMessage(NoNextFrameException.FINAL_FRAME_CANNOT_CREATE_NEXT_FRAME);
    }

    @Test
    @DisplayName("FinalFrame의 다음 프레임은 생성되지 않는다.")
    void createNextFrame() {
        // given
        final FinalFrame finalFrame = FinalFrame.from(Pins.create());

        // when
        // then
        assertThatThrownBy(() -> finalFrame.createNextFrame())
                .isInstanceOf(NoNextFrameException.class)
                .hasMessage(NoNextFrameException.FINAL_FRAME_CANNOT_CREATE_NEXT_FRAME);
    }

    @Test
    @DisplayName("투구를 한다. 투구를 한 후 상태가 변경된다.")
    void knockDownPin() {
        // given
        final FinalFrame finalFrame = FinalFrame.from(Pins.create());
        final Pin pin = new Pin(0);

        // when
        finalFrame.knockDownPin(pin);

        // then
        assertThat(finalFrame.isEnded()).isFalse();
    }

    // TODO: 마지막 프레임 종료 테스트
}
