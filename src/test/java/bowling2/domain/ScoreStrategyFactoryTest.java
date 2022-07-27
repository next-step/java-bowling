package bowling2.domain;

import bowling2.domain.frame.FinalFrame;
import bowling2.domain.frame.Frame;
import bowling2.domain.frame.NormalFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreStrategyFactoryTest {

    @DisplayName("프레임에 맞는 점수 계산 전략을 꺼낸다.")
    @Test
    void getStrategy() {
        // given
        ScoreStrategyFactory factory = new ScoreStrategyFactory();
        Frame commonFrame = new NormalFrame(1, 3, List.of(3, 4));
        Frame spareFrame = new NormalFrame(1, 0, List.of(3, 7));
        Frame strikeFrame = new NormalFrame(1, 0, List.of(10));
        Frame finalFrame = new FinalFrame(1, 0, List.of(10,10,10));

        // when, then
        assertThat(factory.getScoreStrategy(commonFrame)).isInstanceOf(CommonScoreStrategy.class);
        assertThat(factory.getScoreStrategy(spareFrame)).isInstanceOf(SpareScoreStrategy.class);
        assertThat(factory.getScoreStrategy(strikeFrame)).isInstanceOf(StrikeScoreStrategy.class);
        assertThat(factory.getScoreStrategy(finalFrame)).isInstanceOf(CommonScoreStrategy.class);
    }
}
