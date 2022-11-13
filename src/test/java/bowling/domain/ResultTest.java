package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ResultTest {

    @Test
    void 결과_스트라이크() {
        Frame frame = Frame.createNormal();
        frame.bowling(10);

        assertThat(Result.valueOf(frame, 0)).isEqualTo(Result.STRIKE);
    }

    @Test
    void 결과_거터() {
        Frame frame = Frame.createNormal();
        frame.bowling(0);

        assertThat(Result.valueOf(frame, 0)).isEqualTo(Result.NONE);
    }

    @Test
    void 결과_노말_스페어() {
        Frame frame = Frame.createNormal();
        frame.bowling(5);
        frame.bowling(5);

        assertThat(Result.valueOf(frame, 0)).isEqualTo(Result.NONE);
        assertThat(Result.valueOf(frame, 1)).isEqualTo(Result.SPARE);
    }

    @Test
    void 결과_마지막_스페어() {
        Frame frame = Frame.createFinal();
        frame.bowling(5);
        frame.bowling(5);
        frame.bowling(10);

        assertThat(Result.valueOf(frame, 1)).isEqualTo(Result.SPARE);
        assertThat(Result.valueOf(frame, 2)).isEqualTo(Result.STRIKE);
    }
}
