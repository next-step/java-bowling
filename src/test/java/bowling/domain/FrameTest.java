package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FrameTest {

    @Test
    void 첫_번째_프레임을_생성한다() {
        //given
        Frame frame = NormalFrame.first();
        //when
        FrameInfo frameInfo = frame.info();
        //then
        assertThat(frameInfo.no()).isEqualTo(0);
    }

    @Test
    void 두_번째_프레임을_생성한다() {
        //given
        Frame frame = NormalFrame.first();
        FrameInfo frameInfo = frame.info();
        //when
        Frame nextFrame = frame.next();
        FrameInfo nextFrameInfo = nextFrame.info();
        //then
        assertThat(frameInfo.no()).isEqualTo(0);
        assertThat(nextFrameInfo.no()).isEqualTo(1);
    }

    @Test
    void 마지막_프레임을_생성한다() {
        //given
        Frame frame = FinalFrame.create(FrameInfo.create(9));
        //when
        FrameInfo frameInfo = frame.info();
        //then
        assertThat(frameInfo.no()).isEqualTo(9);
    }

    @Test
    void 마지막_프레임_번호가_10이_아니면_IllegalArgumentException_이_발생한다() {
        //given
        //when
        //then
        assertThatThrownBy(() -> FinalFrame.create(FrameInfo.create(8)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 마지막_프레임_생성_후_다음_프레임을_생성하면_IllegalArgumentException_이_발생한다() {
        //given
        Frame finalFrame = FinalFrame.create(FrameInfo.create(9));
        FrameInfo frameInfo = finalFrame.info();
        assertThat(frameInfo.no()).isEqualTo(9);
        //when
        //then
        assertThatThrownBy(finalFrame::next)
                .isInstanceOf(IllegalArgumentException.class);
    }
}
