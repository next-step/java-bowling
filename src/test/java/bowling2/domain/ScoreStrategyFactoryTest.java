package bowling2.domain;

import bowling2.domain.frame.FinalFrame;
import bowling2.domain.frame.Frame;
import bowling2.domain.frame.NormalFrame;
import bowling2.domain.score.CommonScoreStrategy;
import bowling2.domain.score.ScoreStrategyFactory;
import bowling2.domain.score.SpareScoreStrategy;
import bowling2.domain.score.StrikeScoreStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreStrategyFactoryTest {

    @DisplayName("프레임에 맞는 점수 계산 전략을 꺼낸다.")
    @Test
    void getStrategy() {
        // given
        Frame commonFrame = new NormalFrame(1, 3, List.of(3, 4));
        Frame spareFrame = new NormalFrame(1, 0, List.of(3, 7));
        Frame strikeFrame = new NormalFrame(1, 0, List.of(10));
        Frame finalFrame = new FinalFrame(1, 0, List.of(10,10,10));

        // when, then
        assertThat(ScoreStrategyFactory.getScoreStrategy(commonFrame)).isInstanceOf(CommonScoreStrategy.class);
        assertThat(ScoreStrategyFactory.getScoreStrategy(spareFrame)).isInstanceOf(SpareScoreStrategy.class);
        assertThat(ScoreStrategyFactory.getScoreStrategy(strikeFrame)).isInstanceOf(StrikeScoreStrategy.class);
        assertThat(ScoreStrategyFactory.getScoreStrategy(finalFrame)).isInstanceOf(CommonScoreStrategy.class);
    }
}
