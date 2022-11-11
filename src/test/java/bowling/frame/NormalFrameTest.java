package bowling.frame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import bowling.FallenPins;
import bowling.exception.ExceedFallenPinsException;
import org.junit.jupiter.api.Test;

class NormalFrameTest {

    @Test
    void 첫번째_투구에_모든_핀_쓰러뜨리면_스트라이크() {
        NormalFrame frame = new NormalFrame();
        frame = frame.update(FallenPins.of(10));

        assertThat(frame.getResult()).isEqualTo("X ");
    }

    @Test
    void 첫번째_투구_후_핀이_남아있고_두번째_투구에_모든_핀_쓰러뜨리면_스페어() {
        NormalFrame frame = new NormalFrame();
        frame = frame.update(FallenPins.of(8));
        frame = frame.update(FallenPins.of(2));

        assertThat(frame.getResult()).isEqualTo("8|/");
    }

    @Test
    void 두번째_투구_후에도_모든_핀을_쓰러뜨리지_못하면_미스() {
        NormalFrame frame = new NormalFrame();
        frame = frame.update(FallenPins.of(8));
        frame = frame.update(FallenPins.of(1));

        assertThat(frame.getResult()).isEqualTo("8|1");
    }

    @Test
    void 첫번째_투구_시_하나도_핀을_못쓰러뜨리면_첫번째_거터() {
        NormalFrame frame = new NormalFrame();
        frame = frame.update(FallenPins.of(0));

        assertThat(frame.getResult()).isEqualTo("- ");
    }

    @Test
    void 두번째_투구_시_하나도_핀을_못쓰러뜨리면_두번째_거터() {
        NormalFrame frame = new NormalFrame();
        frame = frame.update(FallenPins.of(7));
        frame = frame.update(FallenPins.of(0));

        assertThat(frame.getResult()).isEqualTo("7|-");
    }

    @Test
    void 첫번째_투구_시_스트라이크는_아닐때_첫번째_점수_표기() {
        NormalFrame frame = new NormalFrame();
        frame = frame.update(FallenPins.of(4));

        assertThat(frame.getResult()).isEqualTo("4 ");
    }

    @Test
    void 프레임_종료됨() {
        NormalFrame frame = new NormalFrame();
        frame = frame.update(FallenPins.of(7));
        frame = frame.update(FallenPins.of(3));

        assertThat(frame.isFinish()).isTrue();
    }

    @Test
    void 프레임_진행중() {
        NormalFrame frame = new NormalFrame();
        frame = frame.update(FallenPins.of(7));

        assertThat(frame.isFinish()).isFalse();
    }

    @Test
    void 두번째_투구_시_남아있는_볼링핀보다_더_많은_핀을_쓰러뜨리려면_예외_발생() {
        assertThatExceptionOfType(ExceedFallenPinsException.class)
                .isThrownBy(() -> {
                    NormalFrame frame = new NormalFrame();
                    frame = frame.update(FallenPins.of(7));
                    frame = frame.update(FallenPins.of(7));
                });
    }
}
