package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class BowlResultTest {

    @Test
    void strike(){
        assertThat(BowlResult.find(0, 10, 10)).isEqualTo(BowlResult.STRIKE);
    }

    @Test
    void spare(){
        assertThat(BowlResult.find(1, 10, 10)).isEqualTo(BowlResult.SPARE);
    }

    @Test
    void gutter_first(){
        assertThat(BowlResult.find(0, 0, 0)).isEqualTo(BowlResult.GUTTER);
    }

    @Test
    void gutter_second(){
        assertThat(BowlResult.find(1, 0, 0)).isEqualTo(BowlResult.GUTTER);
    }

    @Test
    void miss(){
        assertThat(BowlResult.find(0, 1, 1)).isEqualTo(BowlResult.MISS);
        assertThat(BowlResult.find(1, 4, 3)).isEqualTo(BowlResult.MISS);

    }
}
