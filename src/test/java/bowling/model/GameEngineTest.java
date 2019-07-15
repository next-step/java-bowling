package bowling.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static bowling.model.Pins.DOWN_ALL;
import static bowling.model.Pins.valueOf;
import static org.assertj.core.api.Assertions.*;

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
        gameEngine
                .play(valueOf(1)).play(valueOf(1))
                .play(valueOf(1)).play(valueOf(1))
                .play(valueOf(10))
                .play(valueOf(0)).play(valueOf(10))
                .play(valueOf(5)).play(valueOf(5))
                .play(valueOf(1)).play(valueOf(1))
                .play(valueOf(1)).play(valueOf(1))
                .play(valueOf(1)).play(valueOf(1))
                .play(valueOf(1)).play(valueOf(1))
                .play(valueOf(1)).play(valueOf(1));

        // then
        assertThat(gameEngine).isNotNull();
        assertThat(gameEngine.isGameOver()).isTrue();
    }

    @DisplayName("모두 스트라이크의 결과를 반환한다")
    @Test
    void bowl_all_strike_success() {
        // when
        gameEngine
                .play(DOWN_ALL)
                .play(DOWN_ALL)
                .play(DOWN_ALL)
                .play(DOWN_ALL)
                .play(DOWN_ALL)
                .play(DOWN_ALL)
                .play(DOWN_ALL)
                .play(DOWN_ALL)
                .play(DOWN_ALL)
                .play(DOWN_ALL)
                .play(DOWN_ALL)
                .play(DOWN_ALL);

        // then
        assertThat(gameEngine).isNotNull();
        assertThat(gameEngine.isGameOver()).isTrue();
    }

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

    @DisplayName("보너스 게임을 치는데 성공한다")
    @Test
    void bowl_hasBonus_success() {
        // when
        IntStream.rangeClosed(1, 12)
                .forEach((totalOfPlay) -> gameEngine.play(Pins.DOWN_ALL));
    }

    @DisplayName("마지막 프레임 이후 play 시 게임종료")
    @Test
    void play_excess_fail() {
        assertThatIllegalStateException()
                .isThrownBy(() -> IntStream.rangeClosed(1, 22)
                        .forEach(countOfPlay -> gameEngine.play(Pins.DOWN_ZERO)))
                .withMessage("게임이 종료되었습니다");
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