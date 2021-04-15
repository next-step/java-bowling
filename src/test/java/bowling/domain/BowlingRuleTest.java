package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingRuleTest {

    @DisplayName("볼링 룰 확인 테스트")
    @Test
    void match_볼링_룰_테스트() {
        // when
        BowlingRule strike = BowlingRule.match("strike");
        // then
        assertThat(strike).isEqualTo(BowlingRule.STRIKE);
    }

    @DisplayName("볼링 기본 값 확인 테스트")
    @Test
    void match_기본값_테스트() {
        // when
        BowlingRule rule = BowlingRule.match("??");
        // then
        assertThat(rule).isEqualTo(BowlingRule.GUTTER);
    }
}
