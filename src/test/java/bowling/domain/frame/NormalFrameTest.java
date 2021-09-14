package bowling.domain.frame;

import bowling.domain.score.NormalScore;
import org.junit.jupiter.api.Test;

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
                () -> assertEquals(NormalFrame.init(), NormalFrame.init()),
                () -> assertEquals(NormalFrame.init().tryFirst(10), NormalFrame.of(1, NormalScore.first(10), 1)),
                () -> assertEquals(NormalFrame.init().tryFirst(0).trySecond(3), NormalFrame.of(1, NormalScore.first(0).second(3), 2))
        );
    }

    @Test
    public void 첫시도에서_스트라이크를_치면_Frame이_끝난다() {
        //given
        NormalFrame frame = NormalFrame.init();
        //when
        frame = frame.tryFirst(10);
        //then
        assertTrue(frame.isNowFrameDone());
    }

    @Test
    public void 첫시도에서_스트라이크를_치면_Frame이_끝나지_않는다() {
        //given
        NormalFrame frame = NormalFrame.init();
        //when
        frame = frame.tryFirst(1);
        //then
        assertFalse(frame.isNowFrameDone());
    }

    @Test
    public void _9회차의_첫시도에서_스트라이크를_치면_끝난다() {
        //given
        NormalFrame frame = NormalFrame.of(8, NormalScore.first(3).second(3), 2);
        //when
        frame = frame.tryFirst(10);
        //then
        assertTrue(frame.isLast());
    }

    @Test
    public void _9회차의_첫시도가_스트라이크가_아니면_끝나지_않는다() {
        //given
        NormalFrame frame = NormalFrame.of(8, NormalScore.first(3).second(3), 2);
        //when
        frame = frame.tryFirst(8);
        //then
        assertFalse(frame.isLast());
    }

    @Test
    public void _9회차의_두번째_시도_후_끝난다() {
        //given
        NormalFrame frame = NormalFrame.of(8, NormalScore.first(3).second(3), 2);
        //when
        frame = frame.tryFirst(8).trySecond(2);
        //then
        assertTrue(frame.isLast());
    }

    @Test
    public void 마지막시도가_아니면_끝나지_않았다() {
        //given
        //when
        NormalFrame frame = NormalFrame.init().tryFirst(1);
        //then
        assertThat(frame.isLast()).isFalse();
    }

    @Test
    public void 라운드를_받아서_맞는지_여부를_판단할_수_있다() {
        //given
        //when
        NormalFrame frame = NormalFrame.of(8, NormalScore.first(3), 1);
        //then
        assertThat(frame.isFrame(8)).isTrue();
    }

}
