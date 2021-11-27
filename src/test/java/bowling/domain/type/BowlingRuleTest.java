package bowling.domain.type;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

@Nested
@DisplayName("볼링 규칙 테스트")
class BowlingRuleTest {

    @Nested
    @DisplayName("기본 프레임 규칙 검증")
    class NormalFrameRuleTest {
        @Test
        @DisplayName("스트라이크 검증")
        void strike() {
            BowlingRule rule = BowlingRule.convertForNormalFrame(10, true);
            assertThat(rule).isEqualTo(BowlingRule.STRIKE);
        }

        @Test
        @DisplayName("스페어 검증")
        void spare() {
            BowlingRule rule = BowlingRule.convertForNormalFrame(10, false);
            assertThat(rule).isEqualTo(BowlingRule.SPARE);
        }

        @RepeatedTest(9)
        @DisplayName("미스 검증")
        void miss(RepetitionInfo repetitionInfo) {
            BowlingRule rule = BowlingRule.convertForNormalFrame(repetitionInfo.getCurrentRepetition(), false);
            assertThat(rule).isEqualTo(BowlingRule.MISS);
        }

        @Test
        @DisplayName("거터 검증")
        void gutter() {
            BowlingRule rule = BowlingRule.convertForNormalFrame(0, true);
            assertThat(rule).isEqualTo(BowlingRule.GUTTER);
        }
    }

    @Nested
    @DisplayName("마지막 프레임 규칙 검증")
    class FinalFrameRuleTest {
        @Test
        @DisplayName("스트라이크 검증")
        void strike() {
            BowlingRule rule = BowlingRule.convertForFinalFrame(10, true, false);
            assertThat(rule).isEqualTo(BowlingRule.STRIKE);
        }

        @Test
        @DisplayName("스페어 검증")
        void spare() {
            BowlingRule rule = BowlingRule.convertForFinalFrame(10, true, true);
            assertThat(rule).isEqualTo(BowlingRule.SPARE);
        }

        @RepeatedTest(9)
        @DisplayName("미스 검증")
        void miss(RepetitionInfo repetitionInfo) {
            BowlingRule rule = BowlingRule.convertForFinalFrame(repetitionInfo.getCurrentRepetition(), true, true);
            assertThat(rule).isEqualTo(BowlingRule.MISS);
        }

        @Test
        @DisplayName("거터 검증")
        void gutter() {
            BowlingRule rule = BowlingRule.convertForFinalFrame(0, true, false);
            assertThat(rule).isEqualTo(BowlingRule.GUTTER);
        }
    }
}
