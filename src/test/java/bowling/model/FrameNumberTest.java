package bowling.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static bowling.model.Pins.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class FrameNumberTest {

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

        String currentStates = gameEngine.getCurrentStates();

        // then
        assertThat(gameEngine).isNotNull();
        assertThat(currentStates).isEqualTo("1|1,1|1,X,-|/,5|/,1|1,1|1,1|1,1|1,1|1");
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
}