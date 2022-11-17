package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import bowling.domain.exception.EndedFrameException;
import bowling.domain.exception.ExceedFallenPinsException;
import bowling.domain.pin.FallenPins;
import org.junit.jupiter.api.Test;

class NormalFrameTest {

    @Test
    void 첫번째_투구에_모든_핀_쓰러뜨리면_스트라이크() {
        Frame frame = new NormalFrame();
        frame = frame.updateFrameState(FallenPins.of(10));

        assertThat(frame.getResult()).isEqualTo("X ");
    }

    @Test
    void 첫번째_투구_후_핀이_남아있고_두번째_투구에_모든_핀_쓰러뜨리면_스페어() {
        Frame frame = new NormalFrame();
        frame = frame.updateFrameState(FallenPins.of(8));
        frame = frame.updateFrameState(FallenPins.of(2));

        assertThat(frame.getResult()).isEqualTo("8|/");
    }

    @Test
    void 두번째_투구_후에도_모든_핀을_쓰러뜨리지_못하면_미스() {
        Frame frame = new NormalFrame();
        frame = frame.updateFrameState(FallenPins.of(8));
        frame = frame.updateFrameState(FallenPins.of(1));

        assertThat(frame.getResult()).isEqualTo("8|1");
    }

    @Test
    void 첫번째_투구_시_하나도_핀을_못쓰러뜨리면_첫번째_거터() {
        Frame frame = new NormalFrame();
        frame = frame.updateFrameState(FallenPins.of(0));

        assertThat(frame.getResult()).isEqualTo("- ");
    }

    @Test
    void 두번째_투구_시_하나도_핀을_못쓰러뜨리면_두번째_거터() {
        Frame frame = new NormalFrame();
        frame = frame.updateFrameState(FallenPins.of(7));
        frame = frame.updateFrameState(FallenPins.of(0));

        assertThat(frame.getResult()).isEqualTo("7|-");
    }

    @Test
    void 첫번째_거터이며_두번째_투구_10개_전부_쓰러뜨리면_스페어() {
        Frame frame = new NormalFrame();
        frame = frame.updateFrameState(FallenPins.of(0));
        frame = frame.updateFrameState(FallenPins.of(10));

        assertThat(frame.getResult()).isEqualTo("-|/");
    }

    @Test
    void 첫번째_투구_시_스트라이크는_아닐때_첫번째_점수_표기() {
        Frame frame = new NormalFrame();
        frame = frame.updateFrameState(FallenPins.of(4));

        assertThat(frame.getResult()).isEqualTo("4 ");
    }

    @Test
    void 프레임_종료됨() {
        Frame frame = new NormalFrame();
        frame = frame.updateFrameState(FallenPins.of(7));
        frame = frame.updateFrameState(FallenPins.of(3));

        assertThat(frame.isFinish()).isTrue();
    }

    @Test
    void 프레임_종료됨_스트라이크() {
        Frame frame = new NormalFrame();
        frame = frame.updateFrameState(FallenPins.of(10));

        assertThat(frame.isFinish()).isTrue();
    }

    @Test
    void 프레임_진행중() {
        Frame frame = new NormalFrame();
        frame = frame.updateFrameState(FallenPins.of(7));

        assertThat(frame.isFinish()).isFalse();
    }

    @Test
    void 두번째_투구_시_남아있는_볼링핀보다_더_많은_핀을_쓰러뜨리려면_예외_발생() {
        assertThatExceptionOfType(ExceedFallenPinsException.class)
                .isThrownBy(() -> {
                    NormalFrame frame = new NormalFrame();
                    frame = frame.updateFrameState(FallenPins.of(7));
                    frame = frame.updateFrameState(FallenPins.of(7));
                });
    }

    @Test
    void 프레임이_스트라이크로_종료되었는데_업데이트_진행_시_예외_발생() {
        assertThatExceptionOfType(EndedFrameException.class)
                .isThrownBy(() -> {
                    NormalFrame frame = new NormalFrame();
                    frame = frame.updateFrameState(FallenPins.of(10));
                    frame = frame.updateFrameState(FallenPins.of(0));
                });
    }

    @Test
    void 프레임이_모두_투구되어_종료되었는데_업데이트_진행_시_예외_발생() {
        assertThatExceptionOfType(EndedFrameException.class)
                .isThrownBy(() -> {
                    NormalFrame frame = new NormalFrame();
                    frame = frame.updateFrameState(FallenPins.of(0));
                    frame = frame.updateFrameState(FallenPins.of(10));
                    frame = frame.updateFrameState(FallenPins.of(4));
                });
    }

}
