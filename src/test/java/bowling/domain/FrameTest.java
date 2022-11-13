package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class FrameTest {

    @Test
    void 노말프레임_생성() {
        Frame frame = Frame.initNormal(5);
        assertThat(frame).isEqualTo(Frame.initNormal(5));
        assertThat(frame.size()).isEqualTo(1);
        assertThat(frame.get(0)).isEqualTo(Bowling.of(5));
    }

    @Test
    void 노말프레임_투구() {
        Frame frame = Frame.initNormal(2);
        frame.bowling(8);

        assertThat(frame.get(1)).isEqualTo(Bowling.of(8));
    }

    @Test
    void 노말프레임_투구실패() {
        Frame frame = Frame.initNormal(8);
        assertThatIllegalArgumentException().isThrownBy(() -> frame.bowling(5));
    }

    @Test
    void 마지막프레임_투구() {
        Frame frame = Frame.initFinal(10);
        frame.bowling(10);
        frame.bowling(10);

        assertThat(frame.get(2)).isEqualTo(Bowling.of(10));
    }

    @Test
    void 마지막프레임_투구실패() {
        Frame frame = Frame.initFinal(10);
        frame.bowling(10);
        frame.bowling(10);

        assertThatIllegalArgumentException().isThrownBy(() -> frame.bowling(9));
    }

    @Test
    void 다음프레임() {
        Frame frame = Frame.initFinal(10).createNext(5);
        assertThat(frame).isEqualTo(Frame.initNormal(5));
    }

    @Test
    void 마지막프레임() {
        Frame frame = Frame.initFinal(10);
        for (int i = 0; i < 9; i++) {
             frame = frame.createNext(5);
        }
        assertThat(frame).isEqualTo(Frame.initFinal(5));
    }

    @Test
    void 프레임종료() { // TODO 여기서도 체크해야할까?
        Frame frame = Frame.initNormal(5);
        frame.bowling(5);
        assertThat(frame.isEnd()).isTrue();
    }
}
