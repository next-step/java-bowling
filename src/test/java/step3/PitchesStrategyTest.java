package step3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import step3.strategy.BowlingPitchesStrategy;
import step3.strategy.PitchesStrategy;

import static org.assertj.core.api.Assertions.assertThat;

public class PitchesStrategyTest {

    @DisplayName("투구 생성 전략 테스트")
    @Test
    void pitches() {
        PitchesStrategy strategy = new BowlingPitchesStrategy();
        for (int i = 0; i < 100; i++) {
            assertThat(strategy.shot(0)).isLessThanOrEqualTo(10).isGreaterThan(0);
            assertThat(strategy.shot(6)).isLessThanOrEqualTo(4).isGreaterThan(0);
        }
    }

}
