package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class NormalScoreTest {
    private final int FIRST_BALL = 3;
    private final int SECOND_BALL = 4;
    private final int STRIKE = 10;
    private final int SPARE = 7;
    private final int GUTTER = 0;

    private NormalScore normalScore;

    @BeforeEach
    void setUp() {
        normalScore = new NormalScore(0);
    }

    @Test
    void 두_번_투구() {
        normalScore.bowl(FIRST_BALL);
        normalScore.bowl(SECOND_BALL);
        assertThat(normalScore.sumScore()).isEqualTo(7);
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
        normalScore.bowl(STRIKE);
        assertThat(normalScore.bowl(1)).isFalse();
    }

    @Test
    void 세_번_굴리면_False() {
        normalScore.bowl(FIRST_BALL);
        normalScore.bowl(SECOND_BALL);

        assertThat(normalScore.bowl(1)).isFalse();
    }

    @Test
    void 한번_미스_더_굴릴수_있는_상태() {
        normalScore.bowl(FIRST_BALL);
        assertThat(normalScore.nowBowlable()).isTrue();
    }

    @Test
    void 스페어_더_굴릴수_없는_상태() {
        normalScore.bowl(FIRST_BALL);
        normalScore.bowl(SPARE);
        assertThat(normalScore.nowBowlable()).isFalse();
    }

    @Test
    void 스트라이크_더_굴릴수_없는_상태() {
        normalScore.bowl(STRIKE);
        assertThat(normalScore.nowBowlable()).isFalse();
    }

    @Test
    void 스트라이크_여부() {
        normalScore.bowl(STRIKE);
        assertThat(normalScore.isStrike()).isTrue();
    }

    @Test
    void 스페어_여부() {
        normalScore.bowl(FIRST_BALL);
        normalScore.bowl(SPARE);
        assertThat(normalScore.isSpare()).isTrue();
    }

    @Test
    void 스트라이크_결과_출력() {
        normalScore.bowl(STRIKE);

        assertThat(normalScore.framePoint()).isEqualTo("X   ");
    }

    @Test
    void 스페어_결과_출력() {
        normalScore.bowl(FIRST_BALL);
        normalScore.bowl(SPARE);

        assertThat(normalScore.framePoint()).isEqualTo(FIRST_BALL + "|/ ");
    }

    @Test
    void 미스_결과_출력() {
        normalScore.bowl(FIRST_BALL);
        normalScore.bowl(SECOND_BALL);

        assertThat(normalScore.framePoint()).isEqualTo(FIRST_BALL + "|" + SECOND_BALL + " ");
    }

    @Test
    void 거터_결과_출력() {
        normalScore.bowl(FIRST_BALL);
        normalScore.bowl(GUTTER);

        assertThat(normalScore.framePoint()).isEqualTo(FIRST_BALL + "|" + "- ");
    }
}
