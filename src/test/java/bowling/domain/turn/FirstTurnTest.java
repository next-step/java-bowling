package bowling.domain.turn;

import bowling.domain.score.TurnScore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FirstTurnTest {
    @Test
    @DisplayName("점수가 10 일경우 Strike")
    void isStrikeTest() {
        assertThat(
                new FirstTurn(TurnScore.of(10)).isStrike()
        ).isTrue();
    }
}
