package bowling.domain.state;

import bowling.domain.score.Pins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created : 2020-12-24 오후 4:29
 * Developer : Seo
 */
class SpareTest {

    private Spare spare;

    @BeforeEach
    void setUp() {
        spare = new Spare(new Pins(1), new Pins(9));
    }

    @Test
    void init() {
        assertThat(spare).isNotNull().isInstanceOf(Spare.class);
    }

    @Test
    void getScore() {
        assertThat(spare.getScore().getFrameScore()).isEqualTo(10);
        assertThat(spare.getScore().getFirst().get()).isEqualTo(1);
        assertThat(spare.getScore().getSecond().get()).isEqualTo(9);
    }

    @Test
    void getSymbol() {
        assertThat(spare.getSymbol()).isEqualTo("1|/");
    }
}
