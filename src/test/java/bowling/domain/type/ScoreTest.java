package bowling.domain.type;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

@Nested
@DisplayName("볼링 규칙 테스트")
class ScoreTest {
    @Test
    @DisplayName("스트라이크 검증")
    void strike() {
        Score rule = Score.convert(10, true);
        assertThat(rule).isSameAs(Score.STRIKE);
    }

    @Test
    @DisplayName("스페어 검증")
    void spare() {
        Score rule = Score.convert(10, false);
        assertThat(rule).isSameAs(Score.SPARE);
    }

    @RepeatedTest(9)
    @DisplayName("미스 검증")
    void miss(RepetitionInfo repetitionInfo) {
        Score rule = Score.convert(repetitionInfo.getCurrentRepetition(), false);
        assertThat(rule).isSameAs(Score.MISS);
    }

    @Test
    @DisplayName("거터 검증")
    void gutter() {
        Score rule = Score.convert(0, true);
        assertThat(rule).isSameAs(Score.GUTTER);
    }
}
