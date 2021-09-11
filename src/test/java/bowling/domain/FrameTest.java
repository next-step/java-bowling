package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
class FrameTest {

    @Test
    public void 첫번째_Frame을_생성할_수_있다() {
        //given
        //when
        //then
        assertThat(Frame.start(10)).isEqualTo(Frame.start(10));
    }

    @Test
    public void Frame의_첫시도에서_스트라이크를_치면_다음_Frame으로_넘어간다() {
        //given
        Frame frame = Frame.start(10);
        //when
        frame = frame.nextTurn(9);
        //then
        assertThat(frame).isEqualTo(Frame.of(2, Score.first(9), false));
    }

    @Test
    public void Frame에서_첫시도에서_스트라이크가_아니면_다음시도를_한다() {
        //given
        Frame frame = Frame.start(9);
        //when
        frame = frame.nextTurn(1);
        //then
        assertThat(frame).isEqualTo(Frame.of(1, Score.first(9).withSecond(1), true));
    }

    @Test
    public void Frame에서_두번째_시도에서_다음시도를하면_다음Frame이_된다() {
        //given
        Frame frame = Frame.start(9).nextTurn(0);
        //when
        frame = frame.nextTurn(3);
        //then
        assertThat(frame).isEqualTo(Frame.of(2, Score.first(3).withSecond(0), false));
    }


}
