package bowling.domain;

import bowling.engine.ScoreStrategy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class RandomScoreStrategyTest {

    @Test
    void createSecond() {
        ScoreStrategy scoreStrategy = new RandomScoreStrategy();
        Assertions.assertThat(scoreStrategy.createSecond(8) <= 2).isTrue();
    }
}
