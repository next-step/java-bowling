package bowling.domain;

import static bowling.domain.PitchFactory.*;
import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import bowling.domain.count.Gutter;
import bowling.domain.count.Remain;
import bowling.domain.count.Spare;
import bowling.domain.count.Strike;

class FinalFrameTest {
    private Frame frame;

    @BeforeEach
    void setUp() {
        frame = new FinalFrame();
    }

    @DisplayName("마지막 투구이고, 남은 핀의 갯수가 0보다 크면 true 를 리턴하고 아니라면 false 를 리턴한다.")
    @ParameterizedTest
    @MethodSource("isEnd")
    void isEnd(int pitchNumber, int remainPinCount, boolean expected) {
        assertThat(frame.isEnd(pitchNumber, remainPinCount)).isEqualTo(expected);
    }

    @DisplayName("남은 핀의 갯수는 현재 핀의 갯수에서 넘어뜨린 핀의 갯수를 뺀 핀의 갯수이고, 갯수가 1보다 작다면 전체 핀의 갯수를 리턴한다.")
    @ParameterizedTest
    @MethodSource("getRemainPinCount")
    void getRemainPinCount(int pinCount, Pitch pitch, int expected) {
        assertThat(frame.getRemainPinCount(pinCount,  pitch)).isEqualTo(expected);
    }

    private static Stream isEnd() {
        return Stream.of(
                Arguments.arguments(3, 1, true),
                Arguments.arguments(3, PIN_COUNT, true),
                Arguments.arguments(1, 0, false),
                Arguments.arguments(2, 0, false),
                Arguments.arguments(1, PIN_COUNT, false),
                Arguments.arguments(2, PIN_COUNT, false)
        );
    }

    private static Stream getRemainPinCount() {
        return Stream.of(
                Arguments.arguments(PIN_COUNT, new Strike(1), PIN_COUNT),
                Arguments.arguments(PIN_COUNT, new Spare(1, PIN_COUNT), PIN_COUNT),
                Arguments.arguments(PIN_COUNT, new Gutter(1), PIN_COUNT),
                Arguments.arguments(5, new Spare(1, 5), PIN_COUNT),
                Arguments.arguments(PIN_COUNT, new Remain(1, 7), 3)
        );
    }
}
