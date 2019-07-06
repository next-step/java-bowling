package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class FinalScoreTest {
    private FinalScore finalScore;

    @BeforeEach
    void setUp() {
        finalScore = new FinalScore();
    }

    @Test
    void 거터() {
        finalScore.bowl(0);
        finalScore.bowl(0);
        assertThat(finalScore.sumScore()).isEqualTo(0);
    }

    @Test
    void 거터_미스() {
        finalScore.bowl(0);
        finalScore.bowl(7);
        assertThat(finalScore.sumScore()).isEqualTo(7);
    }

    @Test
    void 거터_스페어_거터() {
        finalScore.bowl(0);
        finalScore.bowl(10);
        finalScore.bowl(0);
        assertThat(finalScore.sumScore()).isEqualTo(10);
    }

    @Test
    void 거터_스페어_미스() {
        finalScore.bowl(0);
        finalScore.bowl(10);
        finalScore.bowl(5);
        assertThat(finalScore.sumScore()).isEqualTo(15);
    }

    @Test
    void 거터_스페어_스트라이크() {
        finalScore.bowl(0);
        finalScore.bowl(10);
        finalScore.bowl(10);
        assertThat(finalScore.sumScore()).isEqualTo(20);
    }

    @Test
    void 미스() {
        finalScore.bowl(7);
        finalScore.bowl(2);
        assertThat(finalScore.sumScore()).isEqualTo(9);
    }

    @Test
    void 스페어_거터() {
        finalScore.bowl(7);
        finalScore.bowl(3);
        finalScore.bowl(0);
        assertThat(finalScore.sumScore()).isEqualTo(10);
    }

    @Test
    void 스페어_미스() {
        finalScore.bowl(7);
        finalScore.bowl(3);
        finalScore.bowl(5);
        assertThat(finalScore.sumScore()).isEqualTo(15);
    }

    @Test
    void 스페어_스트라이크() {
        finalScore.bowl(7);
        finalScore.bowl(3);
        finalScore.bowl(10);
        assertThat(finalScore.sumScore()).isEqualTo(20);
    }

    @Test
    void 스트라이크_거터_거터() {
        finalScore.bowl(10);
        finalScore.bowl(0);
        finalScore.bowl(0);
        assertThat(finalScore.sumScore()).isEqualTo(10);
    }

    @Test
    void 스트라이크_거터_스페어() {
        finalScore.bowl(10);
        finalScore.bowl(0);
        finalScore.bowl(10);
        assertThat(finalScore.sumScore()).isEqualTo(20);
    }

    @Test
    void 스트라이크_미스() {
        finalScore.bowl(10);
        finalScore.bowl(3);
        finalScore.bowl(4);
        assertThat(finalScore.sumScore()).isEqualTo(17);
    }

    @Test
    void 스트라이크_스페어() {
        finalScore.bowl(10);
        finalScore.bowl(3);
        finalScore.bowl(7);
        assertThat(finalScore.sumScore()).isEqualTo(20);
    }

    @Test
    void 스트라이크_연속_두번_거터() {
        finalScore.bowl(10);
        finalScore.bowl(10);
        finalScore.bowl(0);
        assertThat(finalScore.sumScore()).isEqualTo(20);
    }

    @Test
    void 스트라이크_연속_두번_미스() {
        finalScore.bowl(10);
        finalScore.bowl(10);
        finalScore.bowl(7);
        assertThat(finalScore.sumScore()).isEqualTo(27);
    }

    @Test
    void 스트라이크_연속_세번() {
        finalScore.bowl(10);
        finalScore.bowl(10);
        finalScore.bowl(10);
        assertThat(finalScore.sumScore()).isEqualTo(30);
    }

    @Test
    void 두번째_커버안하고_세번째_투구() {
        finalScore.bowl(7);
        finalScore.bowl(2);
        assertThat(finalScore.bowl(5)).isFalse();
    }

    @Test
    void 마이너스_점수_예외발생() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            finalScore.bowl(-1);
        });
    }

    @Test
    void 점수_10점_초과_예외발생() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            finalScore.bowl(11);
        });
    }

    @Test
    void 처음_두번_점수_10점_초과_예외발생() {
        finalScore.bowl(7);
        assertThatIllegalArgumentException().isThrownBy(() -> {
            finalScore.bowl(4);
        });
    }

    @Test
    void 마지막_두번_점수_10점_초과_예외발생() {
        finalScore.bowl(10);
        finalScore.bowl(7);
        assertThatIllegalArgumentException().isThrownBy(() -> {
            finalScore.bowl(4);
        });
    }

    @Test
    void 네번_투구_불가() {
        finalScore.bowl(10);
        finalScore.bowl(10);
        finalScore.bowl(10);
        assertThat(finalScore.bowl(10)).isFalse();

    }
}
