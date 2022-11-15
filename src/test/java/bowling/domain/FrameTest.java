package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class FrameTest {

    @Test
    void 일반프레임_종료판단_2회투구() {
        Frame frame = Frame.createNormal();

        frame.bowling(5);
        assertThat(frame.isEnd()).isFalse();

        frame.bowling(5);
        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    void 일반프레임_종료판단_스트라이크() {
        Frame frame = Frame.createNormal();
        frame.bowling(10);
        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    void 마지막프레임_종료판단_3회투구() {
        Frame frame = Frame.createFinal();
        frame.bowling(10);
        frame.bowling(10);
        frame.bowling(10);
        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    void 마지막프레임_예외() {
        Frame frame = Frame.createFinal();
        frame.bowling(10);
        frame.bowling(10);
        frame.bowling(10);

        assertThatIllegalArgumentException().isThrownBy(() -> frame.bowling(9));
    }

    @Test
    void 프레임종료() {
        Frame frame = Frame.createNormal();
        frame.bowling(5);
        frame.bowling(5);
        assertThat(frame.isEnd()).isTrue();
    }

}
