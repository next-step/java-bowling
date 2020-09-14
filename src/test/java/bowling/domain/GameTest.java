package bowling.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

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
    void hit_strike() {
        Game game = Game.start();
        assertThat(game.hit(10).get(0)).isEqualTo(Arrays.asList("X"));
    }

    @Test
    void hit_spare() {
        Game game = Game.start();
        assertThat(game.hit(1).get(0)).isEqualTo(Arrays.asList("1"));
        assertThat(game.hit(9).get(0)).isEqualTo(Arrays.asList("1", "/"));
    }

    @Test
    void hit_miss() {
        Game game = Game.start();
        assertThat(game.hit(1).get(0)).isEqualTo(Arrays.asList("1"));
        assertThat(game.hit(8).get(0)).isEqualTo(Arrays.asList("1", "8"));
    }

    @Test
    void hit_gutter() {
        Game game = Game.start();
        assertThat(game.hit(0).get(0)).isEqualTo(Arrays.asList("-"));
        assertThat(game.hit(0).get(0)).isEqualTo(Arrays.asList("-", "-"));
    }

    private Game setLastGame() {
        Game game = Game.start();

        for (int index = 0; index < 9; index++) {
            game.hit(10);
        }

        return game;
    }

    @Test
    void hit_strike_last() {
        Game game = setLastGame();
        assertThat(game.hit(10).get(9)).isEqualTo(Arrays.asList("X"));
        assertThat(game.hit(10).get(9)).isEqualTo(Arrays.asList("X", "X"));
        assertThat(game.hit(10).get(9)).isEqualTo(Arrays.asList("X", "X", "X"));
    }

    @Test
    void hit_spare_last() {
        Game game = setLastGame();
        assertThat(game.hit(1).get(9)).isEqualTo(Arrays.asList("1"));
        assertThat(game.hit(9).get(9)).isEqualTo(Arrays.asList("1", "/"));
        assertThat(game.hit(10).get(9)).isEqualTo(Arrays.asList("1", "/", "X"));
    }

    @Test
    void hit_miss_last() {
        Game game = setLastGame();
        assertThat(game.hit(1).get(9)).isEqualTo(Arrays.asList("1"));
        assertThat(game.hit(8).get(9)).isEqualTo(Arrays.asList("1", "8"));
    }

    @Test
    void hit_gutter_last() {
        Game game = setLastGame();
        assertThat(game.hit(0).get(9)).isEqualTo(Arrays.asList("-"));
        assertThat(game.hit(0).get(9)).isEqualTo(Arrays.asList("-", "-"));
    }
}