package bowling.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class GameEngineTest {

    @DisplayName("플레이에 성공한다")
    @Test
    void play_success() {
        // given
        Pins first = Pins.DOWN_ZERO;
        Pins second = Pins.DOWN_ALL;

        // when
        GameEngine gameEngine = new GameEngine();
        gameEngine.play(first);
        gameEngine.play(second);

        // then
        assertThat(gameEngine.isGameOver()).isFalse();
    }

    @DisplayName("한 프레임에 " + Pins.MAX + "초과로 던질 시 실패한다")
    @Test
    void play_whenPinsGreaterThanMax_exception() {
        // given
        Pins first = Pins.valueOf(Pins.MAX - 1);
        Pins second = Pins.valueOf(Pins.MAX - 1);

        // exception
        GameEngine gameEngine = new GameEngine();
        assertThatIllegalArgumentException()
                .isThrownBy(() -> {
                    gameEngine.play(first);
                    gameEngine.play(second);
                });
    }
}