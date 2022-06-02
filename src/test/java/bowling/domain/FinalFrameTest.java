package bowling.domain;

import bowling.engine.ScoreStrategy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class FinalFrameTest {

    @Test
    void selectBonus() {
        ScoreStrategy scoreStrategy = new RandomScoreStrategy();
        FinalFrame finalFrame = new FinalFrame(NormalFrame.firstWithFactor(10, 0), 10);

        Assertions.assertThat(finalFrame.selectBonus(10, scoreStrategy) > 0).isTrue();
    }
}
