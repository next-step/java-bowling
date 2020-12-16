package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static bowling.domain.FinalFrameState.*;
import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameStateTest {

    @DisplayName("첫 번째 투구 이후 상태 반환 테스트")
    @ParameterizedTest
    @MethodSource("generateSampleForFirstPitchingTest")
    void next_state_return_test_for_final_frame_when_first_pitching(final Pins firstPitching,
                                                                    final FinalFrameState beforeState,
                                                                    final FinalFrameState expectedState) {
        // when
        final FinalFrameState actualState = FinalFrameState.nextState(firstPitching, null, beforeState);

        // then
        assertThat(actualState).isEqualTo(expectedState);
    }

    static Stream<Arguments> generateSampleForFirstPitchingTest() {
        return Stream.of(
                Arguments.of(Pins.MAX, FIRST_PITCHING, SECOND_PITCHING),  // 1번 투구 스트라이크 => 2번 투구 가능
                Arguments.of(Pins.of(1), FIRST_PITCHING, SECOND_PITCHING) // 1번 투구 10개 미만 => 2번 투구 가능
        );
    }

    @DisplayName("두 번째 투구 이후 상태 반환 테스트")
    @ParameterizedTest
    @MethodSource("generateSampleForSecondPitchingTest")
    void next_state_return_test_for_final_frame_when_second_pitching(final Pins firstPitching,
                                                                     final Pins secondPitching,
                                                                     final FinalFrameState beforeState,
                                                                     final FinalFrameState expectedState) {
        // when
        final FinalFrameState actualState = FinalFrameState.nextState(firstPitching, secondPitching, beforeState);

        // then
        assertThat(actualState).isEqualTo(expectedState);
    }

    static Stream<Arguments> generateSampleForSecondPitchingTest() {
        return Stream.of(
                Arguments.of(Pins.MAX, Pins.MAX, SECOND_PITCHING, THIRD_PITCHING), // 1번 투구 스트라이크, 2번 투구 스트라이크 => 3번 투구 가능
                Arguments.of(Pins.MAX, Pins.of(9), SECOND_PITCHING, THIRD_PITCHING), // 1번 투구 스트라이크, 2번 투구 스트라이크 아닌 경우 => 3번 투구 가능
                Arguments.of(Pins.of(1), Pins.of(9), SECOND_PITCHING, THIRD_PITCHING), // 1번 2번 투구가 스페어인 경우 => 3번 투구 가능
                Arguments.of(Pins.of(1), Pins.of(8), SECOND_PITCHING, END) // 1번 2번 투구가 스페어가 아닌 경우 => 게임 종료
        );
    }

    @DisplayName("세 번째 투구 이후 상태 반환 테스트")
    @ParameterizedTest
    @MethodSource("generateSampleForThirdPitchingTest")
    void next_state_return_test_for_final_frame_when_third_pitching(final FinalFrameState beforeState,
                                                                    final FinalFrameState expectedState) {
        // when
        final FinalFrameState actualState = FinalFrameState.nextState(Pins.MAX, Pins.MAX, beforeState);

        // then
        assertThat(actualState).isEqualTo(expectedState);
    }

    static Stream<Arguments> generateSampleForThirdPitchingTest() {
        return Stream.of(
                Arguments.of(THIRD_PITCHING, END)
        );
    }
}