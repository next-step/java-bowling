package bowling.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingGamesTest {

    public static final String USERNAME_1 = "one";
    public static final String USERNAME_2 = "two";

    @Test
    void constructor() {
        BowlingGames bowlingGames = BowlingGames.from(Arrays.asList(USERNAME_1, USERNAME_2));

        assertThat(bowlingGames).isNotNull();
    }

    @Test
    void get() {
        BowlingGames bowlingGames = BowlingGames.from(Arrays.asList(USERNAME_1));

        assertThat(bowlingGames.get(0).getPlayerName()).isEqualTo(USERNAME_1);
    }


    @Test
    void isEnd() {
        BowlingGames bowlingGames = BowlingGames.from(Arrays.asList(USERNAME_1));

        Bowling bowling = bowlingGames.get(0);

        hit_all_frame(bowling);

        assertThat(bowlingGames.isEnd()).isTrue();
    }

    void hit_all_frame(Bowling bowling) {
        bowling.hit(0);
        bowling.hit(0);

        bowling.hit(0);
        bowling.hit(0);

        bowling.hit(0);
        bowling.hit(0);

        bowling.hit(0);
        bowling.hit(0);

        bowling.hit(0);
        bowling.hit(0);

        bowling.hit(0);
        bowling.hit(0);

        bowling.hit(0);
        bowling.hit(0);

        bowling.hit(0);
        bowling.hit(0);

        bowling.hit(0);
        bowling.hit(0);

        bowling.hit(0);
        bowling.hit(0);
    }

    @Test
    void matchLastIndex() {
        assertThat(
                BowlingGames.from(Arrays.asList(USERNAME_1)).matchLastIndex(1)
        ).isTrue();

        assertThat(
                BowlingGames.from(Arrays.asList(USERNAME_1, USERNAME_2)).matchLastIndex(2)
        ).isTrue();
    }
}
