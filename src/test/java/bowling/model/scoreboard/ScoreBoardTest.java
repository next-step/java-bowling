package bowling.model.scoreboard;

import bowling.model.Player;
import bowling.model.Result;
import bowling.model.frame.Frame;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class ScoreBoardTest {
    public static final String NOBODY = "NOB";

    @Test
    public void add_ShouldThrow() {
        ScoreBoard scoreBoard = new ScoreBoard();
        assertThatIllegalArgumentException().isThrownBy(() -> scoreBoard.add(null));

        scoreBoard.add(new Player("DUP"));
        assertThatIllegalArgumentException().isThrownBy(() -> scoreBoard.add(new Player("DUP")));
    }

    @ParameterizedTest
    @MethodSource("getNames")
    public void getPlayers(List<Player> players) {
        ScoreBoard scoreBoard = new ScoreBoard();
        players.forEach(scoreBoard::add);
        assertThat(scoreBoard.getPlayers()).containsExactlyElementsOf(players);
    }

    @ParameterizedTest
    @MethodSource("getNames")
    public void get(List<Player> players) {
        ScoreBoard scoreBoard = new ScoreBoard();
        players.forEach(scoreBoard::add);
        assertThatIllegalArgumentException().isThrownBy(() -> scoreBoard.get(new Player(NOBODY)));
        assertThat(scoreBoard.get(players.get(0)));
    }

    @Test
    public void isEnded() {
        ScoreBoard scoreBoard = new ScoreBoard();
        assertThat(scoreBoard.isEnded()).isTrue();
        Player player1 = new Player("PL1");
        Player player2 = new Player("PL2");
        scoreBoard.add(player1);
        scoreBoard.add(player2);

        for (int i = 0; i <= Frame.MAX_FRAME_INDEX; i++) {
            scoreBoard.get(player1).addResult(Result.MAX_PIN_COUNT);
            scoreBoard.get(player2).addResult(Result.MAX_PIN_COUNT);
            assertThat(scoreBoard.isEnded()).isFalse();
        }

        scoreBoard.get(player1).addResult(Result.MAX_PIN_COUNT);
        assertThat(scoreBoard.isEnded()).isFalse();
        scoreBoard.get(player2).addResult(Result.MAX_PIN_COUNT);
        assertThat(scoreBoard.isEnded()).isTrue();
    }

    private static Stream<List<Player>> getNames() {
        return Stream.of(
                toPlayers(Arrays.asList("ABC", "BCD", "CDE", "DEF")),
                toPlayers(Arrays.asList("BOB", "OAK", "POB", "SON", "KIM", "LEE", "HEE"))
        );
    }

    private static List<Player> toPlayers(List<String> names) {
        return names.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }
}
