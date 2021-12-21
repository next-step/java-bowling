package bowling.scores;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import bowling.domain.scores.FinalHitScores;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FinalHitScoresTest {

    private static final int STRIKE = 10;
    private static final int[] SPARE = new int[]{3, 7};
    private static final int[] SPARE_WITH_BONUS = new int[]{3, 7, 5};

    @Test
    @DisplayName("점수 리스트가 정상적으로 생성된다.")
    void createTest() {
        assertThatCode(() -> new FinalHitScores(1)).doesNotThrowAnyException();
        assertThatCode(() -> new FinalHitScores(1, 2)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("점수가 4개 이상이면 예외가 발생한다.")
    void createExceptionTest() {
        assertThatIllegalArgumentException().isThrownBy(() -> new FinalHitScores(1, 2, 3, 4));
    }

    @Test
    @DisplayName("마지막 프레임의 투구 횟수는 최대 2회이다.")
    void isClosedMaxRoundTest() {
        assertThat(new FinalHitScores(1).isClosed()).isFalse();
        assertThat(new FinalHitScores(1, 2).isClosed()).isTrue();
    }

    @Test
    @DisplayName("마지막 프레임의 투구중 STRIKE 이거나, SPARE가 존재하면, 투구 횟수는 최대 3회이다.")
    void isClosedMaxRoundStrikeOrSpareTest() {
        assertThat(new FinalHitScores(STRIKE, STRIKE).isClosed()).isFalse();
        assertThat(new FinalHitScores(SPARE).isClosed()).isFalse();
        assertThat(new FinalHitScores(SPARE_WITH_BONUS).isClosed()).isTrue();
        assertThat(new FinalHitScores(STRIKE, STRIKE).isClosed()).isFalse();
        assertThat(new FinalHitScores(STRIKE, STRIKE, 5).isClosed()).isTrue();
    }

    @Test
    @DisplayName("투구시 Score 추가가 정상적으로 된다.")
    void addTest() {
        assertThatCode(() -> new FinalHitScores().add(3)).doesNotThrowAnyException();
        assertThatCode(() -> new FinalHitScores(0).add(3)).doesNotThrowAnyException();
        assertThatCode(() -> new FinalHitScores(STRIKE, 0).add(3)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("투구시 최대 투구 횟수를 넘어가면 예외가 발생한다.")
    void addExceptionTest() {
        assertThatIllegalArgumentException().isThrownBy(() -> new FinalHitScores(1, 0).add(3));
        assertThatIllegalArgumentException().isThrownBy(() -> new FinalHitScores(1, 0).add(3));
        assertThatIllegalArgumentException().isThrownBy(
            () -> new FinalHitScores(STRIKE, STRIKE).add(STRIKE).add(STRIKE));
    }

}
