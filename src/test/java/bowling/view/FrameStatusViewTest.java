package bowling.view;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.pin.FinalPins;
import bowling.domain.pin.NormalPins;
import bowling.domain.pin.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static bowling.domain.TestFixture.GUTTER_PIN;
import static bowling.domain.TestFixture.STRIKE_PIN;
import static bowling.domain.frame.RoundNumber.firstRoundNumber;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class FrameStatusViewTest {

    static Stream<Arguments> frameStatusSource() {
        return Stream.of(
                arguments(NormalFrame.createFirstFrame(), ""),
                arguments(NormalFrame.of(firstRoundNumber(), NormalPins.create()), ""),
                arguments(NormalFrame.of(firstRoundNumber(), NormalPins.of()), ""),
                arguments(NormalFrame.of(firstRoundNumber(), NormalPins.of(STRIKE_PIN)), "X"),
                arguments(NormalFrame.of(firstRoundNumber(), NormalPins.of(GUTTER_PIN)), "-"),
                arguments(NormalFrame.of(firstRoundNumber(), NormalPins.of(new Pin(3))), "3"),
                arguments(NormalFrame.of(firstRoundNumber(), NormalPins.of(new Pin(2), new Pin(8))), "2|/"),
                arguments(NormalFrame.of(firstRoundNumber(), NormalPins.of(GUTTER_PIN, new Pin(8))), "-|8"),
                arguments(NormalFrame.of(firstRoundNumber(), NormalPins.of(GUTTER_PIN, STRIKE_PIN)), "-|/"),
                arguments(NormalFrame.of(firstRoundNumber(), NormalPins.of(GUTTER_PIN, GUTTER_PIN)), "-|-"),
                arguments(NormalFrame.of(firstRoundNumber(), NormalPins.of(new Pin(3), new Pin(4))), "3|4"),
                arguments(FinalFrame.from(FinalPins.create()), ""),
                arguments(FinalFrame.from(FinalPins.of()), ""),
                arguments(FinalFrame.from(FinalPins.of(STRIKE_PIN)), "X"),
                arguments(FinalFrame.from(FinalPins.of(STRIKE_PIN, STRIKE_PIN)), "X|X"),
                arguments(FinalFrame.from(FinalPins.of(new Pin(3), new Pin(4))), "3|4"),
                arguments(FinalFrame.from(FinalPins.of(GUTTER_PIN, GUTTER_PIN)), "-|-"),
                arguments(FinalFrame.from(FinalPins.of(GUTTER_PIN, STRIKE_PIN)), "-|/"),
                arguments(FinalFrame.from(FinalPins.of(new Pin(2), new Pin(8))), "2|/"),
                arguments(FinalFrame.from(FinalPins.of(STRIKE_PIN, STRIKE_PIN, STRIKE_PIN)), "X|X|X"),
                arguments(FinalFrame.from(FinalPins.of(STRIKE_PIN, STRIKE_PIN, new Pin(9))), "X|X|9"),
                arguments(FinalFrame.from(FinalPins.of(STRIKE_PIN, STRIKE_PIN, GUTTER_PIN)), "X|X|-"),
                arguments(FinalFrame.from(FinalPins.of(STRIKE_PIN, GUTTER_PIN, STRIKE_PIN)), "X|-|/"),
                arguments(FinalFrame.from(FinalPins.of(STRIKE_PIN, GUTTER_PIN, GUTTER_PIN)), "X|-|-"),
                arguments(FinalFrame.from(FinalPins.of(STRIKE_PIN, new Pin(2), new Pin(8))), "X|2|/"),
                arguments(FinalFrame.from(FinalPins.of(STRIKE_PIN, new Pin(3), new Pin(4))), "X|3|4"),
                arguments(FinalFrame.from(FinalPins.of(new Pin(2), new Pin(8), STRIKE_PIN)), "2|/|X"),
                arguments(FinalFrame.from(FinalPins.of(new Pin(2), new Pin(8), new Pin(3))), "2|/|3"),
                arguments(FinalFrame.from(FinalPins.of(new Pin(2), new Pin(8), GUTTER_PIN)), "2|/|-")
        );
    }

    @ParameterizedTest
    @MethodSource("frameStatusSource")
    @DisplayName("Frame의 상태를 의도한 대로 출력해주는지 확인한다.")
    void testName(Frame frame, String expectedOutput) {
        // given
        final FrameStatusView frameStatusView = new FrameStatusView(frame);

        // when
        final String frameStatus = frameStatusView.frameStatus();

        // then
        assertThat(frameStatus).isEqualTo(expectedOutput);
    }
}
