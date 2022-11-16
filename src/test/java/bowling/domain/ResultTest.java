package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ResultTest {
    @Test
    void 처음_스트라이크() {
        Result result = Result.from(Frame.createNormal(), 10);
        assertThat(result).isEqualTo(Result.STRIKE);
    }

    @Test
    void 처음_거터() {
        Result result = Result.from(Frame.createNormal(), 0);
        assertThat(result).isEqualTo(Result.GUTTER);
    }

    @Test
    void 처음_미스() {
        Result result = Result.from(Frame.createNormal(), 8);
        assertThat(result).isEqualTo(Result.MISS);
    }

    @Test
    void 다음_스페어() {
        Frame frame = Frame.createNormal();
        frame.bowling(5);

        Result result = Result.from(frame, 5);

        assertThat(result).isEqualTo(Result.SPARE);
    }

    @Test
    void 다음_에러_스트라이크() {
        Frame frame = Frame.createNormal();
        frame.bowling(5);
        assertThatIllegalArgumentException().isThrownBy(() -> Result.from(frame, 10));
    }

    @Test
    void 다음_거터() {
        Frame frame = Frame.createNormal();
        frame.bowling(5);

        Result result = Result.from(frame, 0);

        assertThat(result).isEqualTo(Result.GUTTER);
    }

    @Test
    void 다음_미스() {
        Frame frame = Frame.createNormal();
        frame.bowling(5);

        Result result = Result.from(frame, 2);

        assertThat(result).isEqualTo(Result.MISS);
    }

    @Test
    void 마지막프레임_3스트라이크() {
        Frame frame = Frame.createFinal();
        frame.bowling(10);
        assertThat(frame.isEnd()).isFalse();
        frame.bowling(10);
        assertThat(frame.isEnd()).isFalse();
        frame.bowling(10);
        assertThat(frame.isEnd()).isTrue();

        assertThat(frame.getResult(0)).isEqualTo(Result.STRIKE);
        assertThat(frame.getResult(1)).isEqualTo(Result.STRIKE);
        assertThat(frame.getResult(2)).isEqualTo(Result.STRIKE);
    }

    @Test
    void 마지막프레임_스페어_스트라이크() {
        Frame frame = Frame.createFinal();
        frame.bowling(5);
        frame.bowling(5);
        assertThat(frame.isEnd()).isFalse();

        frame.bowling(10);
        assertThat(frame.isEnd()).isTrue();

        assertThat(frame.getResult(0)).isEqualTo(Result.MISS);
        assertThat(frame.getResult(1)).isEqualTo(Result.SPARE);
        assertThat(frame.getResult(2)).isEqualTo(Result.STRIKE);
    }

    @Test
    void 마지막프레임_스페어_스트라이크2() {
        Frame frame = Frame.createFinal();
        frame.bowling(5);
        frame.bowling(3);
        assertThat(frame.isEnd()).isTrue();
        assertThat(frame.getResult(0)).isEqualTo(Result.MISS);
        assertThat(frame.getResult(1)).isEqualTo(Result.MISS);

        assertThatIllegalStateException().isThrownBy(() -> frame.bowling(10));
    }

    @Test
    void 마지막프레임_예외() {
        Frame frame = Frame.createFinal();
        frame.bowling(5);
        assertThatIllegalArgumentException().isThrownBy(() -> frame.bowling(10));
    }
}
