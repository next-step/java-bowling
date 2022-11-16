package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.domain.FallenPins;
import org.junit.jupiter.api.Test;

class FinalFrameTest {

    @Test
    void 첫번째_투구에_모든_핀_쓰러뜨리면_스트라이크() {
        Frame frame = new FinalFrame();
        frame = frame.updateFrameState(FallenPins.of(10));

        assertThat(frame.getResult()).isEqualTo("X  ");
    }

    @Test
    void 첫번째_투구_후_핀이_남아있고_두번째_투구에_모든_핀_쓰러뜨리면_스페어() {
        Frame frame = new FinalFrame();
        frame = frame.updateFrameState(FallenPins.of(8));
        frame = frame.updateFrameState(FallenPins.of(2));

        assertThat(frame.getResult()).isEqualTo("8|/ ");
    }

    @Test
    void 스페어_후_보너스_투구() {
        Frame frame = new FinalFrame();
        frame = frame.updateFrameState(FallenPins.of(8));
        frame = frame.updateFrameState(FallenPins.of(2));
        frame = frame.updateFrameState(FallenPins.of(10));

        assertThat(frame.getResult()).isEqualTo("8|/|X");
    }

    @Test
    void 스트라이크_후_두번째_투구() {
        Frame frame = new FinalFrame();
        frame = frame.updateFrameState(FallenPins.of(10));
        frame = frame.updateFrameState(FallenPins.of(0));

        assertThat(frame.getResult()).isEqualTo("X|- ");
    }


    @Test
    void 두번째_투구_후에도_모든_핀을_쓰러뜨리지_못하면_미스() {
        Frame frame = new FinalFrame();
        frame = frame.updateFrameState(FallenPins.of(8));
        frame = frame.updateFrameState(FallenPins.of(1));

        assertThat(frame.getResult()).isEqualTo("8|1 ");
    }

    @Test
    void 첫번째_투구_시_하나도_핀을_못쓰러뜨리면_첫번째_거터() {
        Frame frame = new FinalFrame();
        frame = frame.updateFrameState(FallenPins.of(0));

        assertThat(frame.getResult()).isEqualTo("-  ");
    }

    @Test
    void 두번째_투구_시_하나도_핀을_못쓰러뜨리면_두번째_거터() {
        Frame frame = new FinalFrame();
        frame = frame.updateFrameState(FallenPins.of(7));
        frame = frame.updateFrameState(FallenPins.of(0));

        assertThat(frame.getResult()).isEqualTo("7|- ");
    }

    @Test
    void 첫번째_투구_시_스트라이크는_아닐때_첫번째_점수_표기() {
        Frame frame = new FinalFrame();
        frame = frame.updateFrameState(FallenPins.of(4));

        assertThat(frame.getResult()).isEqualTo("4  ");
    }

    @Test
    void 프레임_추가_투구_스트라이크로부터_존재하는_상황에서_종료됨() {
        Frame frame = new FinalFrame();
        frame = frame.updateFrameState(FallenPins.of(10));
        frame = frame.updateFrameState(FallenPins.of(0));
        frame = frame.updateFrameState(FallenPins.of(4));

        assertThat(frame.isFinish()).isTrue();
    }

    @Test
    void 프레임_추가_투구_스페어로부터_존재하는_상황에서_종료됨() {
        Frame frame = new FinalFrame();
        frame = frame.updateFrameState(FallenPins.of(2));
        frame = frame.updateFrameState(FallenPins.of(8));
        frame = frame.updateFrameState(FallenPins.of(10));

        assertThat(frame.isFinish()).isTrue();
    }

    @Test
    void 프레임_추가_투구_스트라이크로부터_존재하는_상황에서_진행중() {
        Frame frame = new FinalFrame();
        frame = frame.updateFrameState(FallenPins.of(10));
        frame = frame.updateFrameState(FallenPins.of(10));

        assertThat(frame.isFinish()).isFalse();
    }

    @Test
    void 프레임_추가_투구_스페어로부터_존재하는_상황에서_진행중() {
        Frame frame = new FinalFrame();
        frame = frame.updateFrameState(FallenPins.of(7));
        frame = frame.updateFrameState(FallenPins.of(3));

        assertThat(frame.isFinish()).isFalse();
    }

    @Test
    void 프레임_추가_투구_미존재하는_상황에서_종료됨() {
        Frame frame = new FinalFrame();
        frame = frame.updateFrameState(FallenPins.of(7));
        frame = frame.updateFrameState(FallenPins.of(1));

        assertThat(frame.isFinish()).isTrue();
    }

    @Test
    void 프레임_추가_투구_미존재하는_상황에서_진행중() {
        Frame frame = new FinalFrame();
        frame = frame.updateFrameState(FallenPins.of(7));

        assertThat(frame.isFinish()).isFalse();
    }

}
