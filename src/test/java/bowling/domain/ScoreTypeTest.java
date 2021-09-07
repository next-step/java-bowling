package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTypeTest {

    @Test
    void findType(){
        assertThat(ScoreType.of(1,10)).isEqualTo(ScoreType.STRIKE);
        assertThat(ScoreType.of(2,10)).isEqualTo(ScoreType.SPARE);
        assertThat(ScoreType.of(2,4)).isEqualTo(ScoreType.MISS);
        assertThat(ScoreType.of(2,0)).isEqualTo(ScoreType.GUTTER);
    }

}