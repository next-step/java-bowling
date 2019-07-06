package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class NormalScoreTest {
    private NormalScore normalScore;

    @BeforeEach
    void setUp() {
        normalScore = new NormalScore();
    }

    @Test
    void 두_번_투구() {
        normalScore.bowl(5);
        normalScore.bowl(4);
        assertThat(normalScore.sumScore()).isEqualTo(9);
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
    void 초구_10점_두번째_또_던짐_False() {
        normalScore.bowl(10);
        assertThat(normalScore.bowl(1)).isFalse();
    }

    @Test
    void 세_번_굴리면_False() {
        normalScore.bowl(5);
        normalScore.bowl(2);

        assertThat(normalScore.bowl(1)).isFalse();
    }

    @Test
    void 한번_미스_더_굴릴수_있는_상태() {
        normalScore.bowl(3);
        assertThat(normalScore.nowBowlable()).isTrue();
    }

    @Test
    void 스페어_더_굴릴수_없는_상태() {
        normalScore.bowl(3);
        normalScore.bowl(7);
        assertThat(normalScore.nowBowlable()).isFalse();
    }

    @Test
    void 스트라이크_더_굴릴수_없는_상태() {
        normalScore.bowl(10);
        assertThat(normalScore.nowBowlable()).isFalse();
    }

    @Test
    void 스트라이크_여부() {
        normalScore.bowl(10);
        assertThat(normalScore.isStrike()).isTrue();
    }

    @Test
    void 스페어_여부() {
        final int SECOND_BOWL = 1;
        normalScore.bowl(3);
        normalScore.bowl(7);
        assertThat(normalScore.isSpare(SECOND_BOWL)).isTrue();
    }

    @Test
    void 결과_출력() {
        normalScore.bowl(3);
        normalScore.bowl(7);
        assertThat(normalScore.getResult()).isEqualTo("3|/ ");
    }
}
