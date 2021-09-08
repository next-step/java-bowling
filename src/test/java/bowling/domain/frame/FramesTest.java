package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Frames Test")
public class FramesTest {

    static Stream<Arguments> provideNextTurnTest() {
        return Stream.of(
                Arguments.of(8, new int[]{10}, 9),
                Arguments.of(9, new int[]{0, 0}, 10),
                Arguments.of(1, new int[]{4, 4}, 2),
                Arguments.of(8, new int[]{9}, 8),
                Arguments.of(2, new int[]{0}, 2)
        );
    }

    @ParameterizedTest(name = "{0} -> {1} -> {2}")
    @MethodSource("provideNextTurnTest")
    @DisplayName("투구할 프레임 투구 번호 가져오기")
    void nextTurnRoundNumber(int roundNumber, int[] pinNumbers, int nextRoundNumber) {
        //given
        Frame secondFrame = NormalFrame.of(roundNumber, pinNumbers);

        //when
        Frames frames = Frames.generate(Collections.singletonList(secondFrame));
        int actual = frames.nextTurnRoundNumber();

        //then
        assertThat(actual).isEqualTo(nextRoundNumber);
    }


    static Stream<Arguments> provideFinishedTest() {
        return Stream.of(
                Arguments.of(new int[]{10, 10, 10}, true),
                Arguments.of(new int[]{9, 1, 0}, true),
                Arguments.of(new int[]{8, 1}, true),
                Arguments.of(new int[]{0, 0}, true),
                Arguments.of(new int[]{10, 10}, false),
                Arguments.of(new int[]{10, 0}, false),
                Arguments.of(new int[]{10}, false),
                Arguments.of(new int[]{0}, false)
        );
    }

    @ParameterizedTest(name = "{0} -> {1}")
    @MethodSource("provideFinishedTest")
    @DisplayName("마지막 프레임까지 종료가 되었는지 확인")
    void lastFrameRoundNumber(int[] pinNumbers, boolean expected) {
        //given
        Frame finalFrame = FinalFrame.of(pinNumbers);

        //when
        Frames frames = Frames.generate(Collections.singletonList(finalFrame));
        boolean actual = frames.isFinished();

        //then
        assertThat(actual).isEqualTo(expected);
    }
}
