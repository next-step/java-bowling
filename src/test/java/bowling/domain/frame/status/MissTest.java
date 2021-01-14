package bowling.domain.frame.status;

import bowling.bowlingexception.IllegalFrameRecordException;
import bowling.domain.frame.DownedPin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MissTest {

    private static Stream<Arguments> makeGutterScenario() {
        return Stream.of(
                Arguments.of(0, 0, "- | -"),
                Arguments.of(6, 0, "6 | -"),
                Arguments.of(0, 4, "- | 4")
        );
    }

    @Test
    @DisplayName("추가 입력 예외처리")
    void additionalInputException() {
        DownedPin firstPitch = DownedPin.fromNumber(5);
        DownedPin secondPitch = firstPitch.fromPreviousPitch(4);
        Miss status = new Miss(firstPitch, secondPitch);

        assertThatThrownBy(
                () -> status.record(4)
        ).isInstanceOf(IllegalFrameRecordException.class);
    }

    @Test
    @DisplayName("미스 출력 테스트(Gutter X)")
    void missDescriptionWithoutGutter() {
        DownedPin firstPitch = DownedPin.fromNumber(5);
        DownedPin secondPitch = firstPitch.fromPreviousPitch(4);
        Miss status = new Miss(firstPitch, secondPitch);

        assertThat(status.getDescription())
                .isEqualTo("5 | 4");
    }

    @MethodSource("makeGutterScenario")
    @ParameterizedTest
    @DisplayName("미스 출력 테스트(Gutter O)")
    void missDescriptionWithGutter(int first, int second, String expected) {
        DownedPin firstPitch = DownedPin.fromNumber(first);
        DownedPin secondPitch = firstPitch.fromPreviousPitch(second);
        Miss status = new Miss(firstPitch, secondPitch);

        assertThat(status.getDescription())
                .isEqualTo(expected);
    }
}
