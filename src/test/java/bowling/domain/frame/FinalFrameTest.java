package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.pin.Pins;
import bowling.exception.NoNextFrameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static bowling.domain.TestFixture.GUTTER_PIN;
import static bowling.domain.TestFixture.STRIKE_PIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class FinalFrameTest {

    static Stream<Arguments> endedFinalFrameSource() {
        return Stream.of(
                arguments(FinalFrame.from(Pins.create()), false),
                arguments(FinalFrame.from(Pins.of(STRIKE_PIN)), false),
                arguments(FinalFrame.from(Pins.of(GUTTER_PIN)), false),
                arguments(FinalFrame.from(Pins.of(new Pin(5))), false),
                arguments(FinalFrame.from(Pins.of(new Pin(5), new Pin(5))), false),
                arguments(FinalFrame.from(Pins.of(new Pin(5), GUTTER_PIN)), true),
                arguments(FinalFrame.from(Pins.of(GUTTER_PIN, GUTTER_PIN)), true),
                arguments(FinalFrame.from(Pins.of(STRIKE_PIN, STRIKE_PIN)), false),
                arguments(FinalFrame.from(Pins.of(new Pin(5), new Pin(5), GUTTER_PIN)), true),
                arguments(FinalFrame.from(Pins.of(STRIKE_PIN, STRIKE_PIN, STRIKE_PIN)), true)
        );
    }

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
        finalFrame.knockDownPin(pin);

        // then
        assertThat(finalFrame.isEnded()).isTrue();
    }

    @ParameterizedTest
    @MethodSource("endedFinalFrameSource")
    @DisplayName("마지막 프레임의 상태별로 종료되었는지 여부를 반환한다.")
    void isEnded(FinalFrame finalFrame, boolean expected) {
        // given
        // when
        final boolean ended = finalFrame.isEnded();

        // then
        assertThat(ended).isEqualTo(expected);
    }
}
