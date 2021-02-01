package bowling.domain.score;

import bowling.bowlingexception.InvalidLeftChanceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScoreTest {

    private Score spare;

    @BeforeEach
    void setUp() {
        spare = new Score(10, 1);
    }

    @Test
    @DisplayName("Score Equality")
    void equality() {
        Score sample = new Score(14, 2);

        assertThat(sample).isEqualTo(new Score(14, 2));
        assertThat(sample).isNotEqualTo(new Score(14, 1));
    }

    @Test
    @DisplayName("보너스 점수 추가")
    void scoring() {
        Score newScore = spare.addScore(5);

        assertThat(newScore).isEqualTo(new Score(15, 0));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 3})
    @DisplayName("leftChance 가 0<=x<=2 의 범위를 벗어날 때의 예외처리")
    void leftChanceRange(int leftChance) {
        assertThatThrownBy(
                () -> new Score(10, leftChance)
        ).isInstanceOf(InvalidLeftChanceException.class);
    }
}
