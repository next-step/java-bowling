package bowling;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoresTest {

    @Test
    @DisplayName("점수 리스트가 정상적으로 생성된다.")
    void createTest() {
        assertThatCode(() -> new Scores(1)).doesNotThrowAnyException();
        assertThatCode(() -> new Scores(1, 2)).doesNotThrowAnyException();
        assertThatCode(() -> new Scores(1, 2, 3)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("점수가 4개 이상이면 예외가 발생한다.")
    void createExceptionTest() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Scores(1, 2, 3, 4));
    }

    @Test
    @DisplayName("Score 점수 계산 테스트")
    void sumScoreTest() {
        assertThat(new Scores(7, 3).sumScore()).isEqualTo(10);
    }

    @Test
    @DisplayName("프레임의 투구 횟수는 최대 2회이다.")
    void isClosedTest() {
        assertThat(new Scores(1).isClosed()).isFalse();
        assertThat(new Scores(1, 2).isClosed()).isTrue();
    }

    @Test
    @DisplayName("프레임의 첫번째 투구가 STRIKE 인경우, 프레임이 종료된다.")
    void isClosedStrikeTest() {
        assertThat(new Scores(10).isClosed()).isTrue();
    }

    @Test
    @DisplayName("마지막 프레임의 투구 횟수는 최대 2회이다.")
    void isClosedMaxRoundTest() {
        assertThat(new Scores(1).isClosedMaxRound()).isFalse();
        assertThat(new Scores(1, 2).isClosedMaxRound()).isTrue();
    }

    @Test
    @DisplayName("마지막 프레임의 투구중 STRIKE 이거나, SPARE가 존재하면, 투구 횟수는 최대 3회이다.")
    void isClosedMaxRoundStrikeOrSpareTest() {
        assertThat(new Scores(10, 10).isClosedMaxRound()).isFalse();
        assertThat(new Scores(3, 7).isClosedMaxRound()).isFalse();
        assertThat(new Scores(10, 10).isClosedMaxRound()).isFalse();
        assertThat(new Scores(10, 10, 5).isClosedMaxRound()).isTrue();
    }


}
