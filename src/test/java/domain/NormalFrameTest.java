package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

public class NormalFrameTest {
    private final int FIRST_BALL = 5;
    private final int SECOND_BALL = 3;
    private final int STRIKE = 10;

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
}
