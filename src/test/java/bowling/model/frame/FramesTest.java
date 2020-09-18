package bowling.model.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FramesTest {

    private static Stream<Arguments> provideForPlayBowling() {
        return Stream.of(
                Arguments.of(Arrays.asList(10), 2),
                Arguments.of(Arrays.asList(5), 1),
                Arguments.of(Arrays.asList(2, 8), 2),
                Arguments.of(Arrays.asList(3, 7, 3), 2),
                Arguments.of(Arrays.asList(3, 7, 3, 4, 5), 3));
    }

    @ParameterizedTest
    @MethodSource("provideForPlayBowling")
    @DisplayName("볼링 진행하기")
    void playBowling(List<Integer> fallenPinsList, int expected) {
        // given
        Frames frames = new Frames();
        int frameNo = 1;

        // when
        for (Integer fallenPins : fallenPinsList) {
            frameNo = frames.playBowling(frameNo, fallenPins);
        }

        // then
        assertThat(frameNo).isEqualTo(expected);

    }

    private static Stream<Arguments> provideForIsEnd() {
        return Stream.of(
                Arguments.of(Arrays.asList(10), false),
                Arguments.of(Arrays.asList(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10), true),
                Arguments.of(Arrays.asList(2, 8, 10, 0, 0, 10, 2, 8, 10, 10, 9, 1, 9, 0, 1, 2), true),
                Arguments.of(Arrays.asList(3, 7, 3, 2, 10), false));
    }

    @ParameterizedTest
    @MethodSource("provideForIsEnd")
    @DisplayName("모든 프레임이 완료되었는지 확인하기")
    void isEnd(List<Integer> fallenPinsList, boolean expected) {
        // given
        Frames frames = new Frames();
        int frameNo = 1;
        for (Integer fallenPins : fallenPinsList) {
            frameNo = frames.playBowling(frameNo, fallenPins);
        }

        // when
        boolean result = frames.isEnd();

        // then
        assertThat(result).isEqualTo(expected);

    }
}
