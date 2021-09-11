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
        Frame frame = NormalFrame.start(10);
        //when
        frame = frame.next(9);
        //then
        assertThat(frame).isEqualTo(NormalFrame.of(2, Score.first(9), false));
    }

    @Test
    public void 첫시도에서_스트라이크가_아니면_다음시도를_한다() {
        //given
        Frame frame = NormalFrame.start(9);
        //when
        frame = frame.next(1);
        //then
        assertThat(frame).isEqualTo(NormalFrame.of(1, Score.first(9).withSecond(1), true));
    }

    @Test
    public void 두번째_시도에서_다음시도를하면_다음Frame이_된다() {
        //given
        Frame frame = NormalFrame.start(9).next(0);
        //when
        frame = frame.next(3);
        //then
        assertThat(frame).isEqualTo(NormalFrame.of(2, Score.first(3).withSecond(0), false));
    }

    @Test
    public void 마지막시도_후_끝났는지_알_수_있다() {
        //given
        //when
        Frame frame = NormalFrame.of(9, Score.first(3).withSecond(1), true);
        //then
        assertThat(frame.isDone()).isTrue();
    }

    @Test
    public void 마지막시도가_아니면_끝나지_않았다() {
        //given
        //when
        Frame frame = NormalFrame.start(9).next(0);
        //then
        assertThat(frame.isDone()).isFalse();
    }

}
