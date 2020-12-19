package bowling.model.player;

import bowling.model.Name;
import bowling.model.frame.Frames;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PlayerTest {


    @Test
    void isTurnOver_STRIKE() {
        Player player = Player.of(Name.from("TST"), new Frames());
        player.bowling(10);
        assertThat(player.isTurnOver()).isTrue();
    }

    @Test
    void isTurnOver_SPARE() {
        Player player = Player.of(Name.from("TST"), new Frames());
        player.bowling(9);
        assertThat(player.isTurnOver()).isFalse();
        player.bowling(1);
        assertThat(player.isTurnOver()).isTrue();
    }

    @Test
    void isTurnOver_MISS() {
        Player player = Player.of(Name.from("TST"), new Frames());
        player.bowling(1);
        assertThat(player.isTurnOver()).isFalse();
        player.bowling(8);
        assertThat(player.isTurnOver()).isTrue();
    }

    @Test
    void isEnd_STRIKE_STRIKE_STRIKE(){
        Player player = lastFramePlayer();

        player.bowling(10);
        assertThat(player.isTurnOver()).isFalse();
        player.bowling(10);
        assertThat(player.isTurnOver()).isFalse();
        player.bowling(10);
        assertThat(player.isTurnOver()).isTrue();
        assertThat(player.isEnd()).isTrue();
    }

    private Player lastFramePlayer(){
        Player player = Player.of(Name.from("TST"), new Frames());
        IntStream.range(0,9).forEach(idx -> player.bowling(10));
        return player;
    }
}