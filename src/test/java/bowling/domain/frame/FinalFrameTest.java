package bowling.domain.frame;

import bowling.domain.score.FinalScore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
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
        assertThat(FinalFrame.start(1)).isEqualTo(FinalFrame.start(1));
    }

    @Test
    public void 첫번째_시도후_다음시도를_할_수_있다() {
        //given
        Frame frame = FinalFrame.start(8);
        //when
        frame = frame.tryNext(2);
        //then
        assertThat(frame).isEqualTo(FinalFrame.of(FinalScore.first(8).second(2), 2, true, false));
    }

    @ParameterizedTest
    @MethodSource
    public void 두번째_시도후_시도의_합이_10이상이면_세번째_시도를_할_수_있다(int first, int second) {
        //given
        Frame frame = FinalFrame.start(first).tryNext(second);
        //when
        frame = frame.tryNext(10);
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
        Frame frame1 = FinalFrame.start(first).tryNext(second);
        Frame frame2 = FinalFrame.start(first).tryNext(second).tryNext(3);
        //when
        //then
        assertAll(
                () -> assertFalse(frame1.isLast()),
                () -> assertTrue(frame2.isLast())
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
        Frame frame = FinalFrame.start(first).tryNext(second);
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

    @Test
    public void 한_프레임의_모든점수를_가져올_수_있다() {
        //given
        Frame frame = FinalFrame.start(10).tryNext(9).tryNext(10);
        //when
        List<Integer> scores = frame.getAllScores();
        //then
        assertThat(scores).containsExactly(10, 9, 10);

    }

}
