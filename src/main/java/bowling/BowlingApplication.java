package bowling;

import bowling.domain.*;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BowlingApplication {

    public static void main(String[] args) {

        final Players players = generatePlayers();

        ResultView.printFrameResults(players);

        while (!players.canEnd()) {
            bowl(players);
        }
    }

    private static void bowl(final Players players) {

        for (Player player : players.getPlayers()) {
                inputBowlNumberAndPrintResult(player);
                ResultView.printFrameResults(players);
        }
    }

    private static Players generatePlayers() {

        final int numberOfPlayers = InputView.inputPlayers();
        final List<Player> players = IntStream.range(0, numberOfPlayers)
                .mapToObj(i -> generatePlayer(i + 1))
                .collect(Collectors.toList());
        return new Players(players);
    }

    private static Player generatePlayer(final int index) {

        return new Player(InputView.inputPlayerName(index));
    }

    private static void inputBowlNumberAndPrintResult(final Player player) {

        if (player.canBowl()) {
            inputBowlNumber(player);
        }
    }

    private static void inputBowlNumber(final Player player) {

        player.bowl(InputView.inputBowlNumber(player.getName()));
    }
}
