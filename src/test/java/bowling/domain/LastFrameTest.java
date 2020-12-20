package bowling.domain;

import bowling.bowlingexception.InvalidDownedPinNumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class LastFrameTest {

    @Test
    @DisplayName("스페어나 스트라이크가 아닐 때의 종료 조건")
    void testOnNormal() {
        LastFrame lastFrame = new LastFrame();
        lastFrame.record(DownedPin.fromNumber(3));
        lastFrame.record(DownedPin.fromNumber(6));

        assertThat(lastFrame.isEnded()).isTrue();
    }

    @Test
    @DisplayName("Spare 의 종료 조건")
    void testRecordOnSpare() {
        LastFrame lastFrame = new LastFrame();
        lastFrame.record(DownedPin.fromNumber(3));
        lastFrame.record(DownedPin.fromNumber(7));

        lastFrame.record(DownedPin.fromNumber(8));

        assertThat(lastFrame.isEnded()).isTrue();
    }

    @Test
    @DisplayName("Strike 의 종료 조건")
    void testRecordOnStrike() {
        LastFrame lastFrame = new LastFrame();
        lastFrame.record(DownedPin.fromNumber(10));
        lastFrame.record(DownedPin.fromNumber(7));
        lastFrame.record(DownedPin.fromNumber(3));

        assertThat(lastFrame.isEnded()).isTrue();
    }

    @Test
    @DisplayName("Strike 이후 예외처리")
    void testInvalidInputOnStrike() {
        LastFrame lastFrame = new LastFrame();
        lastFrame.record(DownedPin.fromNumber(10));
        lastFrame.record(DownedPin.fromNumber(7));

        assertThatThrownBy(
                () -> lastFrame.record(DownedPin.fromNumber(4))
        ).isInstanceOf(InvalidDownedPinNumberException.class);

    }

    private static Stream<Arguments> makeSparePattern() {
        return Stream.of(
                Arguments.of(Arrays.asList(3, 7, 10), Arrays.asList(false, true, false)),
                Arguments.of(Arrays.asList(10, 4, 6), Arrays.asList(false, false, true)),
                Arguments.of(Arrays.asList(4, 6), Arrays.asList(false, true, false))
        );
    }

    private static Stream<Arguments> makeStrikePattern() {
        return Stream.of(
                Arguments.of(Arrays.asList(3, 7, 10), Arrays.asList(false, false, true)),
                Arguments.of(Arrays.asList(10, 4, 6), Arrays.asList(true, false, false)),
                Arguments.of(Arrays.asList(10, 4), Arrays.asList(true, false, false))
        );
    }

    @MethodSource("makeSparePattern")
    @ParameterizedTest
    @DisplayName("해당 인덱스의 스페어 여부 처리")
    void testSpare(List<Integer> scores, List<Boolean> expected) {
        LastFrame lastFrame = new LastFrame();
        for (Integer score : scores) {
            lastFrame.record(DownedPin.fromNumber(score));
        }

        for (int i = 0; i < 3; i++) {
            assertThat(lastFrame.isSpare(i)).isEqualTo(expected.get(i));
        }
    }

    @MethodSource("makeStrikePattern")
    @ParameterizedTest
    @DisplayName("해당 인덱스의 스트라이크 여부 확인")
    void testIsStrike(List<Integer> scores, List<Boolean> expected) {
        LastFrame lastFrame = new LastFrame();
        for (Integer score : scores) {
            lastFrame.record(DownedPin.fromNumber(score));
        }

        for (int i = 0; i < 3; i++) {
            assertThat(lastFrame.isStrike(i)).isEqualTo(expected.get(i));
        }
    }
}
