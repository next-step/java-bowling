package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {
    @DisplayName("isZero 테스트")
    @Test
    void isZero() {
        assertThat(new Score(0).isZero())
                .isTrue();
    }

    @CsvSource({
            "10, 10",
            "0, 10",
            "0, 0"
    })
    @DisplayName("sum 테스트")
    @ParameterizedTest
    void sum(int leftScore, int rightScore) {
        assertThat(
                new Score(leftScore).sum(
                        new Score(rightScore)
                )
        ).isEqualTo(
                new Score(leftScore + rightScore)
        );
    }
}