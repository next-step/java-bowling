package seul.bowling.view;

import org.junit.jupiter.api.Test;
import seul.bowling.domain.Players;

import java.util.Arrays;

public class OutputViewTest {
    @Test
    void printScoreBoard() {
        Players players = Players.of(Arrays.asList("PES", "EEE"));

        players.play("PES", 10);
        players.play("EEE", 10);
        players.play("PES", 9);
        players.play("PES", 1);
        players.play("EEE", 8);
        players.play("EEE", 2);

        OutputView.printScoreBoard(players.getPlayers());
    }
}
