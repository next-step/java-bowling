package bowling;

import bowling.domain.state.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameStateTest {

    @Test
    void playTest() {
        assertThat(new Ready().play(10)).isInstanceOf(Strike.class);
        assertThat(new Ready().play(5)).isInstanceOf(Hit.class);
        assertThat(new Ready().play(0)).isInstanceOf(FirstGutter.class);

        assertThat(new Hit(9).play(1)).isInstanceOf(Spare.class);
        assertThat(new Hit(9).play(0)).isInstanceOf(SecondGutter.class);

        assertThat(new FirstGutter().play(0)).isInstanceOf(SecondGutter.class);
        assertThat(new FirstGutter().play(10)).isInstanceOf(Spare.class);
        assertThat(new FirstGutter().play(1)).isInstanceOf(Miss.class);
        assertThat(new FirstGutter().play(9)).isInstanceOf(Miss.class);
    }

    @Test
    void endGameTest() {
        // 시작전
        assertThat(new Ready().isEnd()).isFalse();

        // 1회 플레이
        assertThat(new Strike().isEnd()).isTrue();
        assertThat(new Hit(5).isEnd()).isFalse();
        assertThat(new FirstGutter().isEnd()).isFalse();

        // 2회 플레이
        assertThat(new SecondGutter().isEnd()).isTrue();
        assertThat(new Miss(5).isEnd()).isTrue();
        assertThat(new Spare().isEnd()).isTrue();
    }
}
