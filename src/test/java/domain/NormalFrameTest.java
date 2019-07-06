package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {
    private final int FIRST_BALL = 3;
    private final int SECOND_BALL = 4;
    private final int STRIKE = 10;
    private final int SPARE = 7;
    private final int GUTTER = 0;
    private final String BLANK = "  ";

    private NormalFrame normalFrame;

    @BeforeEach
    void setUp() {
        normalFrame = new NormalFrame(0);
    }

    @Test
    void 스트라이크_결과_출력() {
        normalFrame.doBowling(STRIKE);

        assertThat(normalFrame.getResult()).isEqualTo("X" + BLANK);
    }

    @Test
    void 스페어_결과_출력() {
        normalFrame.doBowling(FIRST_BALL);
        normalFrame.doBowling(SPARE);

        assertThat(normalFrame.getResult()).isEqualTo(FIRST_BALL + "|/");
    }

    @Test
    void 미스_결과_출력() {
        normalFrame.doBowling(FIRST_BALL);
        normalFrame.doBowling(SECOND_BALL);

        assertThat(normalFrame.getResult()).isEqualTo(FIRST_BALL + "|" + SECOND_BALL);
    }

    @Test
    void 거터_결과_출력() {
        normalFrame.doBowling(FIRST_BALL);
        normalFrame.doBowling(GUTTER);

        assertThat(normalFrame.getResult()).isEqualTo(FIRST_BALL + "|" + "-");
    }

}
