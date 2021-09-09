package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FinalScoresTest {
    @Test
    void create() {
        FinalScores finalScores = new FinalScores();
        assertThat(finalScores).isEqualTo(new FinalScores());
    }

    @DisplayName("miss 인 경우 3번째 투구를 하지 않는다.")
    @Test
    void secondTurn_miss() {
        FinalScores finalScores = new FinalScores();
        finalScores.add(1);
        finalScores.add(2);
        assertThatThrownBy(() -> {
            finalScores.add(1);
        }).isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("spare 인 경우 3번째 투구를 한다.")
    @Test
    void thirdTurn_spare() {
        FinalScores finalScores = new FinalScores();
        finalScores.add(1);
        finalScores.add(9);
        finalScores.add(2);
        assertThat(finalScores.getScores().get(1).getScoreType()).isEqualTo(ScoreType.SPARE);
    }

    @DisplayName("strike 인 경우 3번째 투구를 한다.")
    @Test
    void thirdTurn_strike() {
        FinalScores finalScores = new FinalScores();
        finalScores.add(10);
        finalScores.add(1);
        finalScores.add(9);
        assertThat(finalScores.getScores().get(0).getScoreType()).isEqualTo(ScoreType.STRIKE);
        assertThat(finalScores.getScores().get(2).getScoreType()).isEqualTo(ScoreType.SPARE);
    }

    @DisplayName("4번째 투구 시도시 에러 발생")
    @Test
    void forthTurn_error() {
        FinalScores finalScores = new FinalScores();
        finalScores.add(10);
        finalScores.add(1);
        finalScores.add(2);
        assertThatThrownBy(() -> {
            finalScores.add(1);
        }).isInstanceOf(IllegalStateException.class);
    }

}
