package bowling.domain.frame;

import bowling.domain.score.FinalScore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("NonAsciiCharacters")
public class FinalFrameTest {

    @Test
    public void 첫번째_FinalFrame을_만들_수_있다() {
        //given
        //when
        //then
        assertThat(FinalFrame.init()).isEqualTo(FinalFrame.init());
    }

    @Test
    public void 첫번째_시도후_다음시도를_할_수_있다() {
        //given
        FinalFrame frame = FinalFrame.init().tryFirst(8);
        //when
        frame = frame.trySecond(2);
        //then
        assertThat(frame).isEqualTo(FinalFrame.of(FinalScore.first(8).second(2), 2, true, false));
    }

    @ParameterizedTest
    @MethodSource
    public void 두번째_시도후_시도의_합이_10이상이면_세번째_시도를_할_수_있다(int first, int second) {
        //given
        FinalFrame frame = FinalFrame.init().tryFirst(first).trySecond(second);
        //when
        frame = frame.tryThird(10);
        //then
        assertThat(frame).isEqualTo(
                FinalFrame.of(FinalScore.first(first).second(second).third(10), 3, false, true));
    }

    private static Stream<Arguments> 두번째_시도후_시도의_합이_10이상이면_세번째_시도를_할_수_있다() {
        return Stream.of(
                Arguments.of(10, 10),
                Arguments.of(1, 9),
                Arguments.of(3, 7),
                Arguments.of(0, 10)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void 두번째_시도후_시도의_합이_10이상이면_세번째_시도_후_끝났음을_알_수_있다(int first, int second) {
        //given
        FinalFrame frame = FinalFrame.init().tryFirst(first).trySecond(second);
        //when
        FinalFrame lastFrame = frame.tryThird(10);
        //then
        assertAll(
                () -> assertFalse(frame.isLast()),
                () -> assertTrue(lastFrame.isLast())
        );
    }

    private static Stream<Arguments> 두번째_시도후_시도의_합이_10이상이면_세번째_시도_후_끝났음을_알_수_있다() {
        return Stream.of(
                Arguments.of(10, 10),
                Arguments.of(1, 9),
                Arguments.of(3, 7),
                Arguments.of(0, 10)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void 두번째_시도후_시도의_합이_10보다_작으면_두번째_시도_후_끝났음을_알_수_있다(int first, int second) {
        //given
        //when
        FinalFrame frame = FinalFrame.init().tryFirst(first).trySecond(second);
        //then
        assertTrue(frame.isLast());
    }

    private static Stream<Arguments> 두번째_시도후_시도의_합이_10보다_작으면_두번째_시도_후_끝났음을_알_수_있다() {
        return Stream.of(
                Arguments.of(1, 2),
                Arguments.of(1, 8),
                Arguments.of(2, 7),
                Arguments.of(3, 3)
        );
    }

}
