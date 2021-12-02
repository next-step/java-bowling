package bowling.domain.type;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

@Nested
@DisplayName("볼링 규칙 테스트")
class MarkTest {
    @Test
    @DisplayName("스트라이크 검증")
    void strike() {
        Mark rule = Mark.convert(10, true);
        assertThat(rule).isSameAs(Mark.STRIKE);
    }

    @Test
    @DisplayName("스페어 검증")
    void spare() {
        Mark rule = Mark.convert(10, false);
        assertThat(rule).isSameAs(Mark.SPARE);
    }

    @RepeatedTest(9)
    @DisplayName("미스 검증")
    void miss(RepetitionInfo repetitionInfo) {
        Mark rule = Mark.convert(repetitionInfo.getCurrentRepetition(), false);
        assertThat(rule).isSameAs(Mark.MISS);
    }

    @Test
    @DisplayName("거터 검증")
    void gutter() {
        Mark rule = Mark.convert(0, true);
        assertThat(rule).isSameAs(Mark.GUTTER);
    }
}
