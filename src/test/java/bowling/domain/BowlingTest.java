package bowling.domain;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class BowlingTest {
    @Test
    public void create() {
        final List<Player> players = Arrays.asList(new Player("LHG"), new Player("PJS"));
        assertThatCode(() -> new Bowling(players)).doesNotThrowAnyException();
    }

    @Test
    public void currentPlayer() {
        final List<Player> players = Arrays.asList(new Player("LHG"), new Player("PJS"));
        Bowling bowling = new Bowling(players);
        assertThat(bowling.currentPlayer().name()).isEqualTo("LHG");

        bowling = bowling.play(10);
        assertThat(bowling.currentPlayer().name()).isEqualTo("PJS");

        bowling = bowling.play(8);
        assertThat(bowling.currentPlayer().name()).isEqualTo("PJS");

        bowling = bowling.play(2);
        assertThat(bowling.currentPlayer().name()).isEqualTo("LHG");

        bowling = bowling.play(8);
        assertThat(bowling.currentPlayer().name()).isEqualTo("LHG");

        bowling = bowling.play(2);
        assertThat(bowling.currentPlayer().name()).isEqualTo("PJS");

        bowling = bowling.play(10);
        assertThat(bowling.currentPlayer().name()).isEqualTo("LHG");
    }
}
