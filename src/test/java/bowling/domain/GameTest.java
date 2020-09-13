package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {

    @Test
    void start() {
        assertThat(Game.start()).isNotNull();
    }

    @Test
    void isFinish() {
        Game game = Game.start();

        int tryCount = 0;

        while (!game.isFinish()) {
            game.hit(0);
            tryCount++;
        }

        assertThat(tryCount).isEqualTo(20);
    }

    @Test
    void isFinishWithAllStrike() {
        Game game = Game.start();

        int tryCount = 0;

        while (!game.isFinish()) {
            game.hit(10);
            tryCount++;
        }

        assertThat(tryCount).isEqualTo(12);
    }

    @Test
    void isFinishWithLastSpare() {
        Game game = Game.start();

        int tryCount = 0;

        while (!game.isFinish()) {
            tryCount++;

            if (tryCount == 10) {
                game.hit(9);
            } else if (tryCount == 11) {
                game.hit(1);
            } else {
                game.hit(10);
            }
        }

        assertThat(tryCount).isEqualTo(12);
    }

    @Test
    void getNumber() {
        Game game = Game.start();
        assertThat(game.getPlayNumber()).isEqualTo(1);
        game.hit(10);
        assertThat(game.getPlayNumber()).isEqualTo(2);
    }
}