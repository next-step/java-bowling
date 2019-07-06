package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalFrameTest {
    private final int FIRST_BALL = 3;
    private final int SECOND_BALL = 4;
    private final int STRIKE = 10;
    private final int SPARE = 7;
    private final int GUTTER = 0;

    @Test
    void 스트라이크_결과_출력() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.doBowling(STRIKE);

        assertThat(finalFrame.getResult()).isEqualTo("X");
    }

    @Test
    void 스트라이크_연속세번_결과_출력() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.doBowling(STRIKE);
        finalFrame.doBowling(STRIKE);
        finalFrame.doBowling(STRIKE);

        assertThat(finalFrame.getResult()).isEqualTo("X|X|X");
    }

    @Test
    void 스페어_결과_출력() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.doBowling(FIRST_BALL);
        finalFrame.doBowling(SPARE);

        assertThat(finalFrame.getResult()).isEqualTo(FIRST_BALL + "|/");
    }

    @Test
    void 미스_결과_출력() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.doBowling(FIRST_BALL);
        finalFrame.doBowling(SECOND_BALL);

        assertThat(finalFrame.getResult()).isEqualTo(FIRST_BALL + "|" + SECOND_BALL);
    }

    @Test
    void 거터_결과_출력() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.doBowling(FIRST_BALL);
        finalFrame.doBowling(GUTTER);

        assertThat(finalFrame.getResult()).isEqualTo(FIRST_BALL + "|" + "-");
    }
}
