package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

public class NormalFrameTest {
    private final int FIRST_BALL = 3;
    private final int SECOND_BALL = 4;
    private final int STRIKE = 10;
    private final int SPARE = 7;
    private final int GUTTER = 0;

    @Test
    void 초구_던짐_5점() {
        NormalFrame normalFrame = new NormalFrame();
        assertThat(normalFrame.doBowling(FIRST_BALL)).isEqualTo(FIRST_BALL);
    }

    @Test
    void 두번째_던짐_총합_8점() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.doBowling(FIRST_BALL);
        assertThat(normalFrame.doBowling(SECOND_BALL)).isEqualTo(FIRST_BALL + SECOND_BALL);
    }

    @Test
    void 초구10점_두번째_또_던짐() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.doBowling(STRIKE);

        assertThatIllegalStateException().isThrownBy(() -> {
            normalFrame.doBowling(SECOND_BALL);
        });
    }

    @Test
    void 스트라이크_결과_출력() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.doBowling(STRIKE);

        assertThat(normalFrame.getScore()).isEqualTo("X");
    }

    @Test
    void 스페어_결과_출력() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.doBowling(FIRST_BALL);
        normalFrame.doBowling(SPARE);

        assertThat(normalFrame.getScore()).isEqualTo(FIRST_BALL+"|/");
    }

    @Test
    void 미스_결과_출력() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.doBowling(FIRST_BALL);
        normalFrame.doBowling(SECOND_BALL);

        assertThat(normalFrame.getScore()).isEqualTo(FIRST_BALL+"|"+SECOND_BALL);
    }

    @Test
    void 거터_결과_출력() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.doBowling(FIRST_BALL);
        normalFrame.doBowling(GUTTER);

        assertThat(normalFrame.getScore()).isEqualTo(FIRST_BALL+"|"+"-");
    }

}
