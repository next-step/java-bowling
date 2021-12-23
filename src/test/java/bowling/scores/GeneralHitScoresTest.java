package bowling.scores;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import bowling.domain.scores.GeneralHitScores;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GeneralHitScoresTest {

    private static final int STRIKE = 10;
    private static final int[] SPARE = new int[]{3, 7};

    @Test
    @DisplayName("점수 리스트가 정상적으로 생성된다.")
    void createTest() {
        assertThatCode(() -> new GeneralHitScores(1)).doesNotThrowAnyException();
        assertThatCode(() -> new GeneralHitScores(1, 2)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("점수가 3개 이상이면 예외가 발생한다.")
    void createExceptionTest() {
        assertThatIllegalArgumentException().isThrownBy(() -> new GeneralHitScores(1, 2, 3));
    }

    @Test
    @DisplayName("Score 점수 계산 테스트")
    void sumScoreTest() {
        assertThat(new GeneralHitScores(SPARE).sumScore()).isEqualTo(10);
    }

    @Test
    @DisplayName("프레임의 투구 횟수는 최대 2회이다.")
    void isClosedTest() {
        assertThat(new GeneralHitScores(1).isClosed()).isFalse();
        assertThat(new GeneralHitScores(1, 2).isClosed()).isTrue();
    }

    @Test
    @DisplayName("프레임의 첫번째 투구가 STRIKE 인경우, 프레임이 종료된다.")
    void isClosedStrikeTest() {
        assertThat(new GeneralHitScores(STRIKE).isClosed()).isTrue();
    }

    @Test
    @DisplayName("투구시 Score 추가가 정상적으로 된다.")
    void addTest() {
        assertThatCode(() -> new GeneralHitScores().add(3)).doesNotThrowAnyException();
        assertThatCode(() -> new GeneralHitScores(0).add(3)).doesNotThrowAnyException();
        assertThatCode(() -> new GeneralHitScores(STRIKE)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("투구시 최대 투구 횟수를 넘어가면 예외가 발생한다.")
    void addExceptionTest() {
        assertThatIllegalArgumentException().isThrownBy(() -> new GeneralHitScores(1, 0).add(3));
        assertThatIllegalArgumentException().isThrownBy(() -> new GeneralHitScores(1, 0).add(3));
        assertThatIllegalArgumentException().isThrownBy(
            () -> new GeneralHitScores(STRIKE, STRIKE).add(STRIKE).add(STRIKE));
    }

    @Test
    @DisplayName("투구시 최대 핀 갯수를 넘어가면 예외가 발생한다.")
    void addExceptionTest2() {
        assertThatIllegalArgumentException().isThrownBy(() -> new GeneralHitScores(9, 3));
        assertThatIllegalArgumentException().isThrownBy(() -> new GeneralHitScores().add(9).add(3));
        assertThatIllegalArgumentException().isThrownBy(() -> new GeneralHitScores().add(STRIKE).add(STRIKE));
    }
}
