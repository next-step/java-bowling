package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class NormalScoreTest {
    private NormalScore normalScore;

    @BeforeEach
    void setUp() {
        normalScore = new NormalScore();
    }

    @Test
    void 두_번_투구() {
        assertThat(normalScore.bowl(5)).isEqualTo(5);
        assertThat(normalScore.bowl(4)).isEqualTo(9);
    }

    @Test
    void 마이너스_점수_예외발생() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            normalScore.bowl(-1);
        });
    }

    @Test
    void 점수_10점_초과_예외발생() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            normalScore.bowl(11);
        });
    }

    @Test
    void 초구_10점_두번째_또_던짐_예외발생() {
        normalScore.bowl(10);
        assertThatIllegalStateException().isThrownBy(() -> {
            normalScore.bowl(1);
        });
    }

    @Test
    void 세_번_굴리면_예외발생() {
        normalScore.bowl(5);
        normalScore.bowl(2);

        assertThatIllegalStateException().isThrownBy(() -> {
            normalScore.bowl(1);
        });
    }
}
