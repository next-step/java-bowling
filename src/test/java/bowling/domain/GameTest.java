package bowling.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {

    public static final String USERNAME = "kyd";

    @Test
    void start() {
        assertThat(Game.start(USERNAME)).isNotNull();
    }

    @Test
    void isFinish() {
        Game game = Game.start(USERNAME);

        int tryCount = 0;

        while (!game.isEnd()) {
            tryCount++;
            game.hit(0);
        }

        assertThat(tryCount).isEqualTo(20);
    }

    @Test
    void isFinishWithAllStrike() {
        Game game = Game.start(USERNAME);

        int tryCount = 0;

        while (!game.isEnd()) {
            tryCount++;
            game.hit(10);
        }

        assertThat(tryCount).isEqualTo(12);
    }

    @Test
    void isFinishWithLastSpare() {
        Game game = Game.start(USERNAME);

        int tryCount = 0;

        while (!game.isEnd()) {
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

    private Game setLastGame() {
        Game game = Game.start(USERNAME);

        for (int index = 0; index < 9; index++) {
            game.hit(10);
        }

        return game;
    }

    @Test
    void hit_strike_last() {
        Game game = setLastGame();

        Frame frame = game.hit(10).hit(10).hit(10);

        assertThat(frame.toResults()).isEqualTo(Arrays.asList("X", "X", "X"));
        assertThat(frame.getScore().toInt()).isEqualTo(30);
    }

    @Test
    void hit_spare_last() {
        Game game = setLastGame();

        Frame frame = game.hit(1).hit(9).hit(10);

        assertThat(frame.toResults()).isEqualTo(Arrays.asList("1", "/", "X"));
        assertThat(frame.getScore().toInt()).isEqualTo(20);
    }

    @Test
    void hit_miss_last() {
        Game game = setLastGame();

        Frame frame = game.hit(1).hit(8);

        assertThat(frame.toResults()).isEqualTo(Arrays.asList("1", "8"));
        assertThat(frame.getScore().toInt()).isEqualTo(9);
    }

    @Test
    void hit_gutter_last() {
        Game game = setLastGame();

        Frame frame = game.hit(0).hit(0);

        assertThat(frame.toResults()).isEqualTo(Arrays.asList("-", "-"));
        assertThat(frame.getScore().toInt()).isEqualTo(0);
    }

    @Test
    void next_last() {
        Game game = Game.start(USERNAME);
        Frame frame = null;

        for (int index = 0; index < 10; index++) {
            frame = game.hit(10);
        }

        assertThat(game.getPlayFrameNumber()).isEqualTo(10);
        assertThat(frame).isInstanceOf(LastFrame.class);
    }
}