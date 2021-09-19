package bowling.domain.frame;

import bowling.domain.score.NormalScore;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("NonAsciiCharacters")
class NormalFrameTest {

    @Test
    public void 첫번째_NormalFrame을_생성할_수_있다() {
        //given
        //when
        //then
        assertAll(
                () -> assertEquals(NormalFrame.start(1), NormalFrame.start(1)),
                () -> assertEquals(NormalFrame.start(10), NormalFrame.of(1, NormalScore.first(10), 1)),
                () -> assertEquals(NormalFrame.start(0).tryNext(3), NormalFrame.of(1, NormalScore.first(0).second(3), 2))
        );
    }

    @Test
    public void 첫시도에서_스트라이크를_치면_Frame이_끝난다() {
        //given
        //when
        Frame frame = NormalFrame.start(10);
        //then
        assertThat(frame.next()).isEqualTo(2);
    }

    @Test
    public void 첫시도에서_스트라이크를_치지_못하면_Frame이_끝나지_않는다() {
        //given
        //when
        Frame frame = NormalFrame.start(1);
        //then
        assertThat(frame.next()).isEqualTo(1);
    }

    @Test
    public void _9회차의_첫시도에서_스트라이크를_치면_끝난다() {
        //given
        Frame frame = NormalFrame.of(8, NormalScore.first(3).second(3), 2);
        //when
        frame = frame.tryNext(10);
        //then
        Frame finalFrame = frame;
        assertAll(
                () -> assertEquals(10, finalFrame.next()),
                () -> assertTrue(finalFrame.isLast())
        );
    }

    @Test
    public void _9회차의_첫시도가_스트라이크가_아니면_끝나지_않는다() {
        //given
        Frame frame = NormalFrame.of(8, NormalScore.first(3).second(3), 2);
        //when
        frame = frame.tryNext(8);
        //then
        Frame finalFrame = frame;
        assertAll(
                () -> assertEquals(9, finalFrame.next()),
                () -> assertFalse(finalFrame.isLast())
        );
    }

    @Test
    public void _9회차의_두번째_시도_후_끝난다() {
        //given
        Frame frame = NormalFrame.of(8, NormalScore.first(3).second(3), 2);
        //when
        frame = frame.tryNext(8).tryNext(2);
        //then
        Frame finalFrame = frame;
        assertAll(
                () -> assertEquals(10, finalFrame.next()),
                () -> assertTrue(finalFrame.isLast())
        );
    }

    @Test
    public void 마지막시도가_아니면_끝나지_않았다() {
        //given
        //when
        Frame frame = NormalFrame.start(1);
        //then
        assertThat(frame.isLast()).isFalse();
    }

    @Test
    public void 한_프레임의_모든점수를_가져올_수_있다() {
        //given
        Frame frame = NormalFrame.start(1).tryNext(3);
        //when
        List<Integer> scores = frame.getAllScores();
        //then
        assertThat(scores).containsExactly(1, 3);
    }
}
