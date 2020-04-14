package bowling;

import bowling.domain.state.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StateTest {
    @Test
    @DisplayName("Ready에서 play를 수행했을 때의 상태")
    void playFromReady() {
        assertThat(new Ready().play(0)).isInstanceOf(Gutter.class);
        assertThat(new Ready().play(1)).isInstanceOf(Playing.class);
        assertThat(new Ready().play(10)).isInstanceOf(Strike.class);
    }

    @Test
    @DisplayName("Playing 상태로부터 play를 수행했을 때의 상태")
    void playFromPlaying() {
        assertThat(new Playing(5).play(5)).isInstanceOf(Spare.class);
        assertThat(new Playing(5).play(3)).isInstanceOf(Miss.class);
    }

    @Test
    @DisplayName("첫 투구의 Gutter로부터 play를 수행했을 때의 상태")
    void playFromGutter() {
        assertThat(new Gutter().play(0)).isInstanceOf(Miss.class);
        assertThat(new Gutter().play(10)).isInstanceOf(Spare.class);
    }
}
