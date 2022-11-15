package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ResultTest {
    @Test
    void 처음_스트라이크() {
        Result result = Result.from(Frame2.createNormal(), 10);
        assertThat(result).isEqualTo(Result.STRIKE);
    }

    @Test
    void 처음_거터() {
        Result result = Result.from(Frame2.createNormal(), 0);
        assertThat(result).isEqualTo(Result.GUTTER);
    }

    @Test
    void 처음_미스() {
        Result result = Result.from(Frame2.createNormal(), 8);
        assertThat(result).isEqualTo(Result.MISS);
    }

    @Test
    void 다음_스페어() {
        Frame2 frame = Frame2.createNormal();
        frame.bowling(5);

        Result result = Result.from(frame, 5);

        assertThat(result).isEqualTo(Result.SPARE);
    }

    @Test
    void 다음_거터() {
        Frame2 frame = Frame2.createNormal();
        frame.bowling(5);

        Result result = Result.from(frame, 0);

        assertThat(result).isEqualTo(Result.GUTTER);
    }

    @Test
    void 다음_미스() {
        Frame2 frame = Frame2.createNormal();
        frame.bowling(5);

        Result result = Result.from(frame, 2);

        assertThat(result).isEqualTo(Result.MISS);
    }
}
