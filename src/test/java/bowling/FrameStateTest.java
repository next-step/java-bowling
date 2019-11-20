package bowling;

import bowling.domain.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameStateTest {

    @Test
    void playTest() {
        assertThat(new Ready().play(10)).isInstanceOf(Strike.class);
        assertThat(new Ready().play(5)).isInstanceOf(Hit.class);
        assertThat(new Ready().play(0)).isInstanceOf(Gutter.class);

        assertThat(new Hit(9).play(9)).isInstanceOf(Spare.class);
        assertThat(new Hit(9).play(8)).isInstanceOf(Miss.class);
        assertThat(new Hit(9).play(7)).isInstanceOf(Miss.class);
        assertThat(new Hit(9).play(6)).isInstanceOf(Miss.class);
        assertThat(new Hit(9).play(5)).isInstanceOf(Miss.class);
        assertThat(new Hit(9).play(4)).isInstanceOf(Miss.class);
        assertThat(new Hit(9).play(3)).isInstanceOf(Miss.class);
        assertThat(new Hit(9).play(2)).isInstanceOf(Miss.class);
        assertThat(new Hit(9).play(1)).isInstanceOf(Miss.class);
        assertThat(new Hit(9).play(0)).isInstanceOf(Gutter.class);

        assertThat(new Gutter().play(0)).isInstanceOf(Gutter.class);
        assertThat(new Gutter().play(10)).isInstanceOf(Spare.class);
        assertThat(new Gutter().play(1)).isInstanceOf(Miss.class);
        assertThat(new Gutter().play(2)).isInstanceOf(Miss.class);
        assertThat(new Gutter().play(3)).isInstanceOf(Miss.class);
        assertThat(new Gutter().play(4)).isInstanceOf(Miss.class);
        assertThat(new Gutter().play(5)).isInstanceOf(Miss.class);
        assertThat(new Gutter().play(6)).isInstanceOf(Miss.class);
        assertThat(new Gutter().play(7)).isInstanceOf(Miss.class);
        assertThat(new Gutter().play(8)).isInstanceOf(Miss.class);
        assertThat(new Gutter().play(9)).isInstanceOf(Miss.class);
    }
}
