package bowling.domain;

import bowling.domain.frame.NormalFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class ScoreCalculatorTest {

    private ScoreCalculator scoreCalculator;

    @BeforeEach
    void setUp() {
        scoreCalculator = new ScoreCalculator();
    }

    @DisplayName("점수계산 성공")
    @Test
    void calculate_success() {
        // given
        NormalFrame current = new NormalFrame(1, 3, List.of(3, 4), null, null, 0);

        // when
        scoreCalculator.calculate(current);

        // then
        assertThat(current.score()).isEqualTo(7);
    }
}
