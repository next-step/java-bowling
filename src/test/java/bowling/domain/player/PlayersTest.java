package bowling.domain.player;

import bowling.domain.frame.Pitch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PlayersTest {

    @ParameterizedTest
    @DisplayName("모든 플레이어가 종료 되었다면 game over")
    @MethodSource("gameOverSource")
    void gameOverTest(Player player1, Player player2) {
        Players players = new Players(Arrays.asList(player1, player2));

        assertThat(players.gameOver()).isTrue();
    }

    private static Stream<Arguments> gameOverSource() {
        Player player1 = new Player(new Name("qwe"));
        player1.bowl(Pitch.from(10));
        player1.bowl(Pitch.from(10));
        player1.bowl(Pitch.from(10));
        player1.bowl(Pitch.from(10));
        player1.bowl(Pitch.from(10));
        player1.bowl(Pitch.from(10));
        player1.bowl(Pitch.from(10));
        player1.bowl(Pitch.from(10));
        player1.bowl(Pitch.from(10));
        //10 frame
        player1.bowl(Pitch.from(4));
        player1.bowl(Pitch.from(3));

        Player player2 = new Player(new Name("asd"));
        player2.bowl(Pitch.from(10));
        player2.bowl(Pitch.from(10));
        player2.bowl(Pitch.from(10));
        player2.bowl(Pitch.from(10));
        player2.bowl(Pitch.from(10));
        player2.bowl(Pitch.from(10));
        player2.bowl(Pitch.from(10));
        player2.bowl(Pitch.from(10));
        player2.bowl(Pitch.from(10));
        //10frame
        player2.bowl(Pitch.from(1));
        player2.bowl(Pitch.from(1));

        return Stream.of(Arguments.of(player1, player2));
    }

    @ParameterizedTest
    @DisplayName("한명의 플레이어만 종료 되었다면 false")
    @MethodSource("nonOverSource")
    void game_nonOver(Player player1, Player player2) {
        Players players = new Players(Arrays.asList(player1, player2));

        assertThat(players.gameOver()).isFalse();
    }

    private static Stream<Arguments> nonOverSource() {
        Player player1 = new Player(new Name("qwe"));
        player1.bowl(Pitch.from(10));
        player1.bowl(Pitch.from(10));
        player1.bowl(Pitch.from(10));
        player1.bowl(Pitch.from(10));
        player1.bowl(Pitch.from(10));
        player1.bowl(Pitch.from(10));
        player1.bowl(Pitch.from(10));
        player1.bowl(Pitch.from(10));
        player1.bowl(Pitch.from(10));
        //10frame
        player1.bowl(Pitch.from(1));
        player1.bowl(Pitch.from(1));

        Player player2 = new Player(new Name("asd"));
        player2.bowl(Pitch.from(10));
        player2.bowl(Pitch.from(10));
        player2.bowl(Pitch.from(10));
        player2.bowl(Pitch.from(10));
        player2.bowl(Pitch.from(10));
        player2.bowl(Pitch.from(10));
        player2.bowl(Pitch.from(10));
        player2.bowl(Pitch.from(10));
        player2.bowl(Pitch.from(10));
        //10frame
        player2.bowl(Pitch.from(10));

        return Stream.of(Arguments.of(player1, player2));
    }


}
