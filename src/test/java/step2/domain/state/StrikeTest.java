package step2.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import step2.domain.Score;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StrikeTest {

    @Test
    @DisplayName("스트라이크 점수 가져오기")
    void getScore() {
        Ready ready = new Ready();
        State state = ready.bowl(10);

        assertThat(state.getScore()).isEqualTo(Score.of(10, 2));
    }

    @Test
    @DisplayName("추가점수 계산 - Miss")
    void calculateAdditionalMissScore() {
        Ready ready = new Ready();
        State state = ready.bowl(10);

        Score score = Score.ofMiss(5);
        Score newScore1 = state.calculateAdditionalScore(score);

        assertThat(newScore1.getScore()).isEqualTo(5);
    }

    @Test
    @DisplayName("추가점수 계산 - Spare")
    void calculateAdditionalSpareScore() {
        Ready ready = new Ready();
        State state = ready.bowl(10);

        Score score = Score.ofSpare();
        Score newScore1 = state.calculateAdditionalScore(score);

        assertThat(newScore1.getScore()).isEqualTo(20);
    }

    @Test
    @DisplayName("추가점수 계산 - Strike")
    void calculateAdditionalStrikeScore() {
        Ready ready = new Ready();
        State state = ready.bowl(10);

        Score score = Score.ofStrike();
        Score newScore1 = state.calculateAdditionalScore(score);

        assertThrows(IllegalArgumentException.class,
                newScore1::getScore);
    }

}