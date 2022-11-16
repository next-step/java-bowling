package bowling.domain;

import static bowling.domain.Frames.*;
import static bowling.domain.PitchFactory.*;
import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import bowling.domain.count.Gutter;
import bowling.domain.count.Miss;
import bowling.domain.count.Remain;
import bowling.domain.count.Spare;
import bowling.domain.count.Strike;

class PitchFactoryTest {
    @DisplayName("첫 투구에서 모든 핀을 쓰러뜨리면 Strike 이고, 하나도 쓰러뜨리지 못하면 Gutter 이고, 그 이외는 Remain 이다.")
    @ParameterizedTest
    @MethodSource("create_when_first_pitch")
    void create_when_first_pitch(int frameNumber, int pinCount, Class expected) {
        Pitch pitch = new PitchFactory(remainPinCount -> pinCount).create(frameNumber, 1, PIN_COUNT);
        assertThat(pitch).isInstanceOf(expected);
        assertThat(pitch.getFrameNumber()).isEqualTo(frameNumber);
        assertThat(pitch.getPinCount()).isEqualTo(pinCount);
    }

    @DisplayName("일반 프레임에서 두번째 투구 시 핀 10개를 쓰러뜨리거나 남은 핀 모두를 쓰러뜨리면 Spare 이고, 하나도 쓰러뜨리지 못하면 Gutter 이고, 그 외에는 Miss 이다.")
    @ParameterizedTest
    @MethodSource("create_when_normal_frame")
    void create_when_normal_frame(int frameNumber, int pinCount, int remainPinCount, Class expected) {
        Pitch pitch = new PitchFactory(it -> pinCount).create(frameNumber, 2, remainPinCount);
        assertThat(pitch).isInstanceOf(expected);
        assertThat(pitch.getFrameNumber()).isEqualTo(frameNumber);
        assertThat(pitch.getPinCount()).isEqualTo(pinCount);
    }

    @DisplayName("마지막 프레임에서 두번째 또는 세번째 투구 시 핀 10개를 쓰러뜨리면 Strike 이고, 남은 핀 모두를 쓰러뜨리면 Spare 이고, 하나도 쓰러뜨리지 못하면 Gutter 이고, 그 이외에는 Miss 이다.")
    @ParameterizedTest
    @MethodSource("create_when_final_frame")
    void create_when_final_frame(int pitchNumber, int pinCount, int remainPinCount, Class expected) {
        Pitch pitch = new PitchFactory(it -> pinCount).create(FRAME_COUNT, pitchNumber, remainPinCount);
        assertThat(pitch).isInstanceOf(expected);
        assertThat(pitch.getFrameNumber()).isEqualTo(FRAME_COUNT);
        assertThat(pitch.getPinCount()).isEqualTo(pinCount);
    }

    private static Stream<Arguments> create_when_first_pitch() {
        return Stream.of(
                Arguments.arguments(1, 9, Remain.class),
                Arguments.arguments(2, PIN_COUNT, Strike.class),
                Arguments.arguments(3, 0, Gutter.class),
                Arguments.arguments(FRAME_COUNT, 0, Gutter.class),
                Arguments.arguments(FRAME_COUNT, 5, Remain.class),
                Arguments.arguments(FRAME_COUNT, PIN_COUNT, Strike.class)
        );
    }

    private static Stream<Arguments> create_when_normal_frame() {
        return Stream.of(
                Arguments.arguments(1, PIN_COUNT, PIN_COUNT, Spare.class),
                Arguments.arguments(4, 5, 5, Spare.class),
                Arguments.arguments(6, 3, 5, Miss.class),
                Arguments.arguments(6, 0, 1, Gutter.class)
        );
    }

    private static Stream<Arguments> create_when_final_frame() {
        return Stream.of(
                Arguments.arguments(2, PIN_COUNT, PIN_COUNT, Strike.class),
                Arguments.arguments(3, PIN_COUNT, PIN_COUNT, Strike.class),
                Arguments.arguments(2, 5, 5, Spare.class),
                Arguments.arguments(3, 2, 2, Spare.class),
                Arguments.arguments(2, 1, 2, Miss.class),
                Arguments.arguments(3, 2, 3, Miss.class),
                Arguments.arguments(2, 0, 3, Gutter.class),
                Arguments.arguments(3, 0, PIN_COUNT, Gutter.class)
        );
    }
}
