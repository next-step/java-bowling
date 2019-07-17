package bowling.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static bowling.model.DownPin.DOWN_ALL;
import static bowling.model.DownPin.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class GameEngineTest {

    private GameEngine gameEngine;

    @BeforeEach
    void setUp() {
        gameEngine = new GameEngine();
    }

    @DisplayName("볼링을 친 결과를 반환한다")
    @Test
    void bowl_success() {
        // when
        gameEngine.play(valueOf(1)); gameEngine.play(valueOf(1));
        gameEngine.play(valueOf(1)); gameEngine.play(valueOf(1));
        gameEngine.play(valueOf(10));
        gameEngine.play(valueOf(10));
        gameEngine.play(valueOf(5)); gameEngine.play(valueOf(5));
        gameEngine.play(valueOf(1)); gameEngine.play(valueOf(1));
        gameEngine.play(valueOf(1)); gameEngine.play(valueOf(1));
        gameEngine.play(valueOf(1)); gameEngine.play(valueOf(1));
        gameEngine.play(valueOf(1)); gameEngine.play(valueOf(1));
        gameEngine.play(valueOf(1)); gameEngine.play(valueOf(1));

        // then
        assertThat(gameEngine).isNotNull();
        assertThat(gameEngine.isGameOver()).isTrue();
    }

    @DisplayName("모두 스트라이크의 결과를 반환한다")
    @Test
    void bowl_all_strike_success() {
        // when
        gameEngine.play(DOWN_ALL);
        gameEngine.play(DOWN_ALL);
        gameEngine.play(DOWN_ALL);
        gameEngine.play(DOWN_ALL);
        gameEngine.play(DOWN_ALL);
        gameEngine.play(DOWN_ALL);
        gameEngine.play(DOWN_ALL);
        gameEngine.play(DOWN_ALL);
        gameEngine.play(DOWN_ALL);
        gameEngine.play(DOWN_ALL);
        gameEngine.play(DOWN_ALL);
        gameEngine.play(DOWN_ALL);

        // then
        assertThat(gameEngine).isNotNull();
        assertThat(gameEngine.isGameOver()).isTrue();
    }

    @DisplayName("플레이에 성공한다")
    @Test
    void play_success() {
        // given
        DownPin first = DownPin.DOWN_ZERO;
        DownPin second = DownPin.DOWN_ALL;

        // when
        GameEngine gameEngine = new GameEngine();
        gameEngine.play(first);
        gameEngine.play(second);

        // then
        assertThat(gameEngine.isGameOver()).isFalse();
    }

    @DisplayName("보너스 게임을 치는데 성공한다")
    @Test
    void bowl_hasBonus_success() {
        // when
        IntStream.rangeClosed(1, 12)
                .forEach((totalOfPlay) -> gameEngine.play(DownPin.DOWN_ALL));
    }

    @DisplayName("한 프레임에 " + DownPin.MAX + "초과로 던질 시 실패한다")
    @Test
    void play_whenPinsGreaterThanMax_exception() {
        // given
        DownPin first = DownPin.valueOf(DownPin.MAX - 1);
        DownPin second = DownPin.valueOf(DownPin.MAX - 1);

        // exception
        GameEngine gameEngine = new GameEngine();
        assertThatIllegalArgumentException()
                .isThrownBy(() -> {
                    gameEngine.play(first);
                    gameEngine.play(second);
                });
    }
}