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
import bowling.domain.count.Miss;
import bowling.domain.count.Remain;
import bowling.domain.count.Spare;
import bowling.domain.count.Strike;

class NormalFrameTest {
    private Frame frame;

    @BeforeEach
    void setUp() {
        frame = new NormalFrame(1);
    }

    @DisplayName("최대 투구 갯수보다 크거나 남은 핀의 갯수가 없다면 true 를 리턴하고 아니라면 false 를 리턴한다.")
    @ParameterizedTest
    @MethodSource("isEnd")
    void isEnd(int pitchNumber, int remainPinCount, boolean expected) {
        assertThat(frame.isEnd(pitchNumber, remainPinCount)).isEqualTo(expected);
    }

    @DisplayName("남은 핀의 갯수는 현재 핀의 갯수에서 넘어뜨린 핀의 갯수를 뺀 값이다.")
    @ParameterizedTest
    @MethodSource("getRemainPinCount")
    void getRemainPinCount(int pinCount, Pitch pitch) {
        assertThat(frame.getRemainPinCount(pinCount,  pitch)).isEqualTo(pinCount - pitch.getPinCount());
    }

    private static Stream isEnd() {
        return Stream.of(
                Arguments.arguments(3, 0, true),
                Arguments.arguments(3, PIN_COUNT, true),
                Arguments.arguments(1, 0, true),
                Arguments.arguments(2, 0, true),
                Arguments.arguments(3, 0, true),
                Arguments.arguments(1, 3, false),
                Arguments.arguments(2, 5, false)
        );
    }
    
    private static Stream getRemainPinCount() {
        return Stream.of(
                Arguments.arguments(PIN_COUNT, new Strike(1)),
                Arguments.arguments(9, new Miss(1, 5)),
                Arguments.arguments(3, new Gutter(1)),
                Arguments.arguments(PIN_COUNT, new Spare(1, 9)),
                Arguments.arguments(8, new Remain(1, 7))
        );
    }
}
