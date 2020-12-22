package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameStatusTest {

    private static Stream<Arguments> provideMissStatus() {
        return Stream.of(
                Arguments.of(DownedPin.fromNumber(4), DownedPin.fromNumber(3)),
                Arguments.of(DownedPin.fromNumber(0), DownedPin.fromNumber(6)),
                Arguments.of(DownedPin.fromNumber(5), null)
        );
    }

    @Test
    @DisplayName("Strike 여부확인(Strike)")
    void testIsStrike() {
        assertThat(FrameStatus.getStatus(DownedPin.fromNumber(10), null))
                .isEqualTo(FrameStatus.STRIKE);
    }

    @Test
    @DisplayName("Spare 상태 확인")
    void testIsSpare() {
        assertThat(FrameStatus.getStatus(DownedPin.fromNumber(4), DownedPin.fromNumber(6)))
                .isEqualTo(FrameStatus.SPARE);
    }

    @ParameterizedTest
    @MethodSource("provideMissStatus")
    @DisplayName("Miss 상태")
    void testMiss(DownedPin previousTurn, DownedPin currentTurn) {
        assertThat(FrameStatus.getStatus(previousTurn, currentTurn))
                .isEqualTo(FrameStatus.MISS);
    }

    @Test
    @DisplayName("null 값 체크")
    void testNullValue() {
        assertThat(FrameStatus.getStatus(null, null))
                .isEqualTo(FrameStatus.MISS);
    }
}
