package bowling.game.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BowlingGameTest {

    @DisplayName("플레이어 이름을 받아 볼링 게임을 진행 할 수 있다")
    @Test
    public void gameTest() {
        BowlingGame game = BowlingGame.init("LJW");
        game.bowl(10);
        game.bowl(10);
        game.bowl(10);
        game.bowl(10);
        game.bowl(10);
        game.bowl(10);
        game.bowl(10);
        game.bowl(10);
        game.bowl(10);
        game.bowl(10);
        game.bowl(10);
        // 10프레임 2번째 시도 0일 경우
        game.bowl(0);
        assertThat(game.isDone()).isTrue();
    }

    @DisplayName("게임 진행시 쓰러트린 볼링핀은 0 ~ 10 사이의 수를 입력해야 한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    public void invalidFelledPinsTest(int invalidFelledPins) {
        BowlingGame game = BowlingGame.init("LJW");

        assertThatThrownBy(() -> game.bowl(invalidFelledPins))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("쓰러트린 볼링핀은 0 ~ 10 사이로 입력하세요");
    }

}
