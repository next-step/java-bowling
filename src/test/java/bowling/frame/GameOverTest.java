package bowling.frame;

import bowling.Pins;
import bowling.state.ended.Miss;
import bowling.state.ended.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameOverTest {


    @DisplayName("마지막 프레임에서 스트라이크나 스페시 3번의 기회를 모두 사용")
    @Test
    void 스트라이크_스페어로_경기_종료() {
        GameOver gameOver = new GameOver();
        gameOver.increaseBowlCount();
        gameOver.increaseBowlCount();
        gameOver.increaseBowlCount();

        assertThat(gameOver.isEndGame(null)).isTrue();
    }

    @Test
    void 미스로_경기_종료() {
        GameOver gameOver = new GameOver();
        gameOver.increaseBowlCount();
        gameOver.increaseBowlCount();

        assertThat(gameOver.isEndGame(new Miss(new Pins(3), new Pins(2)))).isTrue();
    }

    @DisplayName("마지막 프레임에서 스트라이크나 스페어를 한 후 1번의 기회가 남음")
    @Test
    void 스트라이크_스페어로_경기_진행_중() {
        GameOver gameOver = new GameOver();
        gameOver.increaseBowlCount();
        gameOver.increaseBowlCount();

        assertThat(gameOver.isEndGame(new Strike())).isFalse();
    }

    @Test
    void 첫_투구로_경기_진행_중() {
        GameOver gameOver = new GameOver();
        gameOver.increaseBowlCount();

        assertThat(gameOver.isEndGame(new Strike())).isFalse();
    }
}