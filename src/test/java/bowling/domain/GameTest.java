package bowling.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

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
            tryCount++;
            game.hit(0);
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
        assertThat(game.getSumScores()).isEqualTo(Arrays.asList(10));
    }

    @Test
    void hit_spare() {
        Game game = Game.start();
        assertThat(game.hit(1).get(0)).isEqualTo(Arrays.asList("1"));
        assertThat(game.hit(9).get(0)).isEqualTo(Arrays.asList("1", "/"));
        assertThat(game.getSumScores()).isEqualTo(Arrays.asList(10));
    }

    @Test
    void hit_miss() {
        Game game = Game.start();
        assertThat(game.hit(1).get(0)).isEqualTo(Arrays.asList("1"));
        assertThat(game.hit(8).get(0)).isEqualTo(Arrays.asList("1", "8"));
        assertThat(game.getSumScores()).isEqualTo(Arrays.asList(9));
    }

    @Test
    void hit_gutter() {
        Game game = Game.start();
        assertThat(game.hit(0).get(0)).isEqualTo(Arrays.asList("-"));
        assertThat(game.hit(0).get(0)).isEqualTo(Arrays.asList("-", "-"));
        assertThat(game.getSumScores()).isEqualTo(Arrays.asList(0));
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
        assertThat(game.getSumScores().get(9)).isEqualTo(300);
    }

    @Test
    void hit_spare_last() {
        Game game = setLastGame();
        assertThat(game.hit(1).get(9)).isEqualTo(Arrays.asList("1"));
        assertThat(game.hit(9).get(9)).isEqualTo(Arrays.asList("1", "/"));
        assertThat(game.hit(10).get(9)).isEqualTo(Arrays.asList("1", "/", "X"));
        assertThat(game.getSumScores().get(9)).isEqualTo(290);
    }

    @Test
    void hit_miss_last() {
        Game game = setLastGame();
        assertThat(game.hit(1).get(9)).isEqualTo(Arrays.asList("1"));
        assertThat(game.hit(8).get(9)).isEqualTo(Arrays.asList("1", "8"));
        assertThat(game.getSumScores().get(9)).isEqualTo(267);
    }

    @Test
    void hit_gutter_last() {
        Game game = setLastGame();
        assertThat(game.hit(0).get(9)).isEqualTo(Arrays.asList("-"));
        assertThat(game.hit(0).get(9)).isEqualTo(Arrays.asList("-", "-"));
        assertThat(game.getSumScores().get(9)).isEqualTo(240);
    }


    @Test
    void next_last() {
        Frame frame = NormalFrame.from();

        for (int index = 0; index < 10; index++) {
            frame = frame.hit(10);
        }

        assertThat(frame.getNumber()).isEqualTo(10);
        assertThat(frame).isInstanceOf(LastFrame.class);
    }

    @Test
    void getSumScores() {
        Game game = Game.start();
        game.hit(9);
        game.hit(1);
        assertThat(game.getSumScores().get(0)).isEqualTo(10);
    }

    @Test
    void getSumScores_all_strike() {
        Game game = Game.start();
        for (int index = 0; index < 12; index++) {
            game.hit(10);
        }

        List<Integer> scores = game.getSumScores();

        assertThat(scores.get(0)).isEqualTo(30);
        assertThat(scores.get(1)).isEqualTo(60);
        assertThat(scores.get(2)).isEqualTo(90);
        assertThat(scores.get(3)).isEqualTo(120);
        assertThat(scores.get(4)).isEqualTo(150);
        assertThat(scores.get(5)).isEqualTo(180);
        assertThat(scores.get(6)).isEqualTo(210);
        assertThat(scores.get(7)).isEqualTo(240);
        assertThat(scores.get(8)).isEqualTo(270);
        assertThat(scores.get(9)).isEqualTo(300);
    }

    @Test
    void getSumScores_lastSpare() {
        Game game = Game.start();
        for (int index = 0; index < 12; index++) {
            if (index == 9) {
                game.hit(9);
            } else if (index == 10) {
                game.hit(1);
            } else {
                game.hit(10);
            }
        }

        List<Integer> scores = game.getSumScores();

        assertThat(scores.get(0)).isEqualTo(30);
        assertThat(scores.get(1)).isEqualTo(60);
        assertThat(scores.get(2)).isEqualTo(90);
        assertThat(scores.get(3)).isEqualTo(120);
        assertThat(scores.get(4)).isEqualTo(150);
        assertThat(scores.get(5)).isEqualTo(180);
        assertThat(scores.get(6)).isEqualTo(210);
        assertThat(scores.get(7)).isEqualTo(240);
        assertThat(scores.get(8)).isEqualTo(270);
        assertThat(scores.get(9)).isEqualTo(290);
    }

    @Test
    void getSumScores_sample() {
        Game game = Game.start();

        game.hit(1); game.hit(2);
        game.hit(9); game.hit(1);
        game.hit(2); game.hit(2);
        game.hit(10); game.hit(0);
        game.hit(10); game.hit(0);
        game.hit(10); game.hit(0);
        game.hit(10); game.hit(9);
        game.hit(10); game.hit(0);
        game.hit(9); game.hit(1);
        game.hit(10); game.hit(9);  game.hit(1);

        List<Integer> scores = game.getSumScores();

        assertThat(scores.get(0)).isEqualTo(3);
        assertThat(scores.get(1)).isEqualTo(15);
        assertThat(scores.get(2)).isEqualTo(20);
        assertThat(scores.get(3)).isEqualTo(50);
        assertThat(scores.get(4)).isEqualTo(80);
        assertThat(scores.get(5)).isEqualTo(110);
        assertThat(scores.get(6)).isEqualTo(139);
        assertThat(scores.get(7)).isEqualTo(159);
        assertThat(scores.get(8)).isEqualTo(179);
        assertThat(scores.get(9)).isEqualTo(199);
    }
}