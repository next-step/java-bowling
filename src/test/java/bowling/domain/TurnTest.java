package bowling.domain;

import bowling.domain.Turn;
import bowling.domain.score.TurnScore;
import bowling.exception.InvalidTurnScoreException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

class TurnTest {
    private static class FakeTurn extends Turn {
        protected FakeTurn(final TurnScore score) {
            super(score);
        }
    }

    @Test
    @DisplayName("점수가 0 일경우 Gutter")
    void isGutterTest() {
        assertThat(
                new FakeTurn(TurnScore.of(0)).isGutter()
        ).isTrue();
    }

    @CsvSource({
            "10,0,10",
            "0,10,10",
            "5,5,10",
            "0,0,0"
    })
    @ParameterizedTest
    @DisplayName("union 테스트")
    void unionTest(int leftTurnValue, int rightTurnValue, int resultTurnValue) {
        assertThat(
                toTurn(leftTurnValue).union(toTurn(rightTurnValue))
        ).isEqualTo(
                toTurn(resultTurnValue)
        );
    }

    @CsvSource({
            "10,1",
            "1,10",
            "10,10"
    })
    @ParameterizedTest
    @DisplayName("union 테스트 - score 결과는 10을 넘을 수 없다.")
    void unionTest(int leftTurnValue, int rightTurnValue) {
        assertThatThrownBy(() ->
                toTurn(leftTurnValue).union(toTurn(rightTurnValue))
        ).isInstanceOf(InvalidTurnScoreException.class);
    }

    Turn toTurn(int turnValue) {
        return new Turn(TurnScore.of(turnValue));
    }
}
