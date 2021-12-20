package bowling.scores;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import bowling.domain.scores.FinalScores;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FinalScoresTest {

    @Test
    @DisplayName("점수 리스트가 정상적으로 생성된다.")
    void createTest() {
        assertThatCode(() -> new FinalScores(1)).doesNotThrowAnyException();
        assertThatCode(() -> new FinalScores(1, 2)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("점수가 4개 이상이면 예외가 발생한다.")
    void createExceptionTest() {
        assertThatIllegalArgumentException().isThrownBy(() -> new FinalScores(1, 2, 3, 4));
    }

    @Test
    @DisplayName("마지막 프레임의 투구 횟수는 최대 2회이다.")
    void isClosedMaxRoundTest() {
        assertThat(new FinalScores(1).isClosed()).isFalse();
        assertThat(new FinalScores(1, 2).isClosed()).isTrue();
    }

    @Test
    @DisplayName("마지막 프레임의 투구중 STRIKE 이거나, SPARE가 존재하면, 투구 횟수는 최대 3회이다.")
    void isClosedMaxRoundStrikeOrSpareTest() {
        assertThat(new FinalScores(10, 10).isClosed()).isFalse();
        assertThat(new FinalScores(3, 7).isClosed()).isFalse();
        assertThat(new FinalScores(10, 10).isClosed()).isFalse();
        assertThat(new FinalScores(10, 10, 5).isClosed()).isTrue();
    }


}
