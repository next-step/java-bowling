package bowling.domain.type;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("볼링 규칙 테스트")
class BowlingRuleTest {
    @Test
    @DisplayName("스트라이크 검증")
    void strike() {
        BowlingRule rule = BowlingRule.convertRuleByPinCount(10, false);
        assertThat(rule).isEqualTo(BowlingRule.STRIKE);
    }

    @Test
    @DisplayName("스페어 검증")
    void spare() {
        BowlingRule rule = BowlingRule.convertRuleByPinCount(10, true);
        assertThat(rule).isEqualTo(BowlingRule.SPARE);
    }

    @RepeatedTest(9)
    @DisplayName("미스 검증")
    void miss(RepetitionInfo repetitionInfo) {
        BowlingRule rule = BowlingRule.convertRuleByPinCount(repetitionInfo.getCurrentRepetition(), true);
        assertThat(rule).isEqualTo(BowlingRule.MISS);
    }

    @Test
    @DisplayName("스페어 검증")
    void gutter() {
        BowlingRule rule = BowlingRule.convertRuleByPinCount(0, false);
        assertThat(rule).isEqualTo(BowlingRule.GUTTER);
    }
}
