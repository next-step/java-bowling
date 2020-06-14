package bowling;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NormalFrameTest {

    @Test
    void play_then_strike(){
        NormalFrame normalFrame = NormalFrame.first();

        String result = normalFrame.play(10);
        assertThat(result).isEqualTo("X");
    }

    @DisplayName("strike후에 플레이 하면 예외 발생한다.")
    @Test
    void strike_and_play_then_throw_exception(){
        NormalFrame normalFrame = NormalFrame.first();

        normalFrame.play(10);
        assertThatThrownBy(()->normalFrame.play(1))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void play_then_miss(){
        NormalFrame normalFrame = NormalFrame.first();

        String first = normalFrame.play(8);
        String second = normalFrame.play(1);

        assertThat(first).isEqualTo("8");
        assertThat(second).isEqualTo("9");
    }

    @Test
    void play_then_spare(){
        NormalFrame normalFrame = NormalFrame.first();

        String first = normalFrame.play(9);
        String second = normalFrame.play(1);

        assertThat(first).isEqualTo("9");
        assertThat(second).isEqualTo("/");
    }

    @Test
    void play_then_gutter(){
        NormalFrame normalFrame = NormalFrame.first();

        String first = normalFrame.play(0);
        String second = normalFrame.play(0);

        assertThat(first).isEqualTo("-");
        assertThat(second).isEqualTo("-");
    }
}
