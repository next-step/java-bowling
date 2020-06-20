package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ScoreTypeTest {

    @Test
    void strike(){
        assertThat(ScoreType.find(0, 10, 10)).isEqualTo(ScoreType.STRIKE);
    }

    @Test
    void spare(){
        assertThat(ScoreType.find(1, 10, 10)).isEqualTo(ScoreType.SPARE);
    }

    @Test
    void gutter_first(){
        assertThat(ScoreType.find(0, 0, 0)).isEqualTo(ScoreType.GUTTER);
    }

    @Test
    void gutter_second(){
        assertThat(ScoreType.find(1, 0, 0)).isEqualTo(ScoreType.GUTTER);
    }

    @Test
    void miss(){
        assertThat(ScoreType.find(0, 1, 1)).isEqualTo(ScoreType.MISS);
        assertThat(ScoreType.find(1, 4, 3)).isEqualTo(ScoreType.MISS);

    }
}
