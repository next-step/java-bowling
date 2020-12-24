package bowling.domain.state;

import bowling.domain.score.Pins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created : 2020-12-24 오후 4:13
 * Developer : Seo
 */
class MissTest {

    private Miss miss;

    @BeforeEach
    void setUp() {
        miss = new Miss(new Pins(8));
    }

    @Test
    void init() {
        assertThat(miss).isNotNull().isInstanceOf(Miss.class);
    }

    @Test
    void getScore() {
        assertThat(miss.getScore().getFrameScore()).isEqualTo(8);
        assertThat(miss.getScore().getFirst().get()).isEqualTo(8);

        miss = new Miss(new Pins(8), new Pins(1));
        assertThat(miss.getScore().getFrameScore()).isEqualTo(9);
        assertThat(miss.getScore().getFirst().get()).isEqualTo(8);
        assertThat(miss.getScore().getSecond().get()).isEqualTo(1);
    }

    @Test
    void isFinished() {
        assertThat(miss.isFinished()).isFalse();

        miss = new Miss(new Pins(8), new Pins(1));
        assertThat(miss.isFinished()).isTrue();
    }

    @Test
    void getSymbol() {
        assertThat(miss.getSymbol()).isEqualTo("8");

        miss = new Miss(new Pins(0));
        assertThat(miss.getSymbol()).isEqualTo("-");

        miss = new Miss(new Pins(0), new Pins(8));
        assertThat(miss.getSymbol()).isEqualTo("-|8");

        miss = new Miss(new Pins(8), new Pins(0));
        assertThat(miss.getSymbol()).isEqualTo("8|-");

        miss = new Miss(new Pins(8), new Pins(1));
        assertThat(miss.getSymbol()).isEqualTo("8|1");
    }
}
