package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.assertj.core.api.Assertions.assertThat;

class ScoreMarkTest {

    @DisplayName("스트라이크")
    @Test
    void strikeTest() {
        ScoreMark actual = ScoreMark.of(10, TRUE);
        assertThat(ScoreMark.STRIKE).isEqualTo(actual);
    }

    @DisplayName("스페어")
    @Test
    public void spareTest() {
        ScoreMark actual = ScoreMark.of(10, FALSE);
        assertThat(ScoreMark.SPARE).isEqualTo(actual);
    }

    @DisplayName("미스")
    @Test
    public void missTest() {
        ScoreMark actual = ScoreMark.of(8, TRUE);
        assertThat(ScoreMark.MISS).isEqualTo(actual);
    }

    @DisplayName("거터")
    @Test
    public void gutterTest() {
        ScoreMark actual = ScoreMark.of(0, FALSE);
        assertThat(ScoreMark.GUTTER).isEqualTo(actual);
    }

}