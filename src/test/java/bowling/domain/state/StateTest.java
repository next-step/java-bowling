package bowling.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.Constants.WRONG_FELLED_PIN;
import static bowling.domain.PinCountTest.*;
import static bowling.domain.state.End.GAME_END_ERROR;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

public class StateTest {
    @Test
    @DisplayName("Ready에서 play를 수행했을 때의 상태")
    void playFromReady() {
        assertAll(
                () -> assertThat(new Ready().play(pinCount0)).isInstanceOf(Gutter.class),
                () -> assertThat(new Ready().play(pinCount0)).isInstanceOf(Gutter.class),
                () -> assertThat(new Ready().play(pinCount10)).isInstanceOf(Strike.class)
        );
    }

    @Test
    @DisplayName("Playing 상태로부터 play를 수행했을 때의 상태")
    void playFromPlaying() {
        assertAll(
                () -> assertThat(new Playing(pinCount5).play(pinCount5)).isInstanceOf(Spare.class),
                () -> assertThat(new Playing(pinCount5).play(pinCount1)).isInstanceOf(Miss.class)
        );
    }

    @Test
    @DisplayName("첫 투구의 Gutter로부터 play를 수행했을 때의 상태")
    void playFromGutter() {
        assertAll(
                () -> assertThat(new Gutter().play(pinCount0)).isInstanceOf(Miss.class),
                () -> assertThat(new Gutter().play(pinCount10)).isInstanceOf(Spare.class)
        );
    }

    @Test
    @DisplayName("마지막 Frame에서 앞서 두 투구가 Spare가 나온 후 play를 수행했을 때의 상태")
    void playFromSpare() {
        assertAll(
                () -> assertThat(new Spare(pinCount5, pinCount5).play(pinCount5)).isInstanceOf(End.class),
                () -> assertThat(new Spare(pinCount5, pinCount5).play(pinCount0)).isInstanceOf(Gutter.class),
                () -> assertThat(new Spare(pinCount5, pinCount5).play(pinCount10)).isInstanceOf(Strike.class)
        );
    }

    @Test
    @DisplayName("마지막 Frame에서 앞선 투구가 Strike가 나온 후 play를 수행했을 때의 상태")
    void playFromStrike() {
        assertAll(
                () -> assertThat(new Strike().play(pinCount5)).isInstanceOf(Playing.class),
                () -> assertThat(new Strike().play(pinCount0)).isInstanceOf(Gutter.class),
                () -> assertThat(new Strike().play(pinCount10)).isInstanceOf(Strike.class)
        );
    }

    @Test
    @DisplayName("End 상태로부터 play를 수행했을 때의 상태")
    void playFromEnd() {
        assertThatIllegalStateException().isThrownBy(() -> {
            new End(pinCount5).play(pinCount5);
        }).withMessage(GAME_END_ERROR);
    }

    @Test
    @DisplayName("프레임의 두번째 투구는 첫번째 투구와 합하여 10 이상이 될 수 없다.")
    void assertSecondFelledPin() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Playing(pinCount5).play(pinCount10);
        }).withMessage(WRONG_FELLED_PIN);
    }
}
