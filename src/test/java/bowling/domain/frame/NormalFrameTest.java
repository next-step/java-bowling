package bowling.domain.frame;

import bowling.domain.Score;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
class NormalFrameTest {

    @Test
    public void 첫번째_NormalFrame을_생성할_수_있다() {
        //given
        //when
        //then
        assertThat(NormalFrame.start(10)).isEqualTo(NormalFrame.start(10));
    }

    @Test
    public void 첫시도에서_스트라이크를_치면_다음_Frame으로_넘어간다() {
        //given
        NormalFrame frame = NormalFrame.start(10);
        //when
        frame = frame.next(9);
        //then
        assertThat(frame).isEqualTo(NormalFrame.of(2, Score.first(9), false));
    }

    @Test
    public void 첫시도에서_스트라이크가_아니면_다음시도를_한다() {
        //given
        NormalFrame frame = NormalFrame.start(9);
        //when
        frame = frame.next(1);
        //then
        assertThat(frame).isEqualTo(NormalFrame.of(1, Score.first(9).withSecond(1), true));
    }

    @Test
    public void 두번째_시도에서_다음시도를하면_다음Frame이_된다() {
        //given
        NormalFrame frame = NormalFrame.start(9).next(0);
        //when
        frame = frame.next(3);
        //then
        assertThat(frame).isEqualTo(NormalFrame.of(2, Score.first(3).withSecond(0), false));
    }

    @Test
    public void _9회차의_첫시도에서_스트라이크를_치면_끝난다() {
        //given
        NormalFrame frame = NormalFrame.of(8, Score.first(3).withSecond(1), true);
        //when
        frame = frame.next(10);
        //then
        assertThat(frame.isLast()).isTrue();
    }

    @Test
    public void _9회차의_첫시도가_스트라이크가_아니면_끝나지_않는다() {
        //given
        NormalFrame frame = NormalFrame.of(8, Score.first(3).withSecond(1), true);
        //when
        frame = frame.next(8);
        //then
        assertThat(frame.isLast()).isFalse();
    }

    @Test
    public void _9회차의_두번째_시도_후_끝난다() {
        //given
        NormalFrame frame = NormalFrame.of(8, Score.first(3).withSecond(1), true);
        //when
        frame = frame.next(8).next(1);
        //then
        assertThat(frame.isLast()).isTrue();
    }

    @Test
    public void 마지막시도가_아니면_끝나지_않았다() {
        //given
        //when
        NormalFrame frame = NormalFrame.start(9).next(0);
        //then
        assertThat(frame.isLast()).isFalse();
    }

}
