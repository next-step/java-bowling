package bowling.scores;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import bowling.domain.scores.GeneralScores;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GeneralScoresTest {

    private static final int STRIKE = 10;
    private static final int[] SPARE = new int[]{3, 7};

    @Test
    @DisplayName("점수 리스트가 정상적으로 생성된다.")
    void createTest() {
        assertThatCode(() -> new GeneralScores(1)).doesNotThrowAnyException();
        assertThatCode(() -> new GeneralScores(1, 2)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("점수가 3개 이상이면 예외가 발생한다.")
    void createExceptionTest() {
        assertThatIllegalArgumentException().isThrownBy(() -> new GeneralScores(1, 2, 3));
    }

    @Test
    @DisplayName("Score 점수 계산 테스트")
    void sumScoreTest() {
        assertThat(new GeneralScores(SPARE).sumScore()).isEqualTo(10);
    }

    @Test
    @DisplayName("프레임의 투구 횟수는 최대 2회이다.")
    void isClosedTest() {
        assertThat(new GeneralScores(1).isClosed()).isFalse();
        assertThat(new GeneralScores(1, 2).isClosed()).isTrue();
    }

    @Test
    @DisplayName("프레임의 첫번째 투구가 STRIKE 인경우, 프레임이 종료된다.")
    void isClosedStrikeTest() {
        assertThat(new GeneralScores(STRIKE).isClosed()).isTrue();
    }

    @Test
    @DisplayName("투구시 Score 추가가 정상적으로 된다.")
    void addTest() {
        assertThatCode(() -> new GeneralScores().add(3)).doesNotThrowAnyException();
        assertThatCode(() -> new GeneralScores(0).add(3)).doesNotThrowAnyException();
        assertThatCode(() -> new GeneralScores(STRIKE).add(3)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("투구시 최대 투구 횟수를 넘어가면 예외가 발생한다.")
    void addExceptionTest() {
        assertThatIllegalArgumentException().isThrownBy(() -> new GeneralScores(1, 0).add(3));
        assertThatIllegalArgumentException().isThrownBy(() -> new GeneralScores(1, 0).add(3));
        assertThatIllegalArgumentException().isThrownBy(
            () -> new GeneralScores(STRIKE, STRIKE).add(STRIKE).add(STRIKE));
    }

}
