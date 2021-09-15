package bowling;

import bowling.view.BowlingInputView;
import bowling.view.BowlingOutputView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BowlingMain {
    public static void main(String[] args) {
        int numberOfPlayers = BowlingInputView.getIntInput("How many people? ");

        List<String> playerNames = getPlayerNames(numberOfPlayers);

        Players players = new Players(playerNames);

        BowlingOutputView.printPlayersFramesStatus(players);

        while (players.isContinued()) {
            doBowls(players);
        }
    }

    private static List<String> getPlayerNames(int numberOfPlayers) {
        return IntStream.rangeClosed(1, numberOfPlayers)
                .mapToObj(i -> String.format("플레이어 %d의 이름은(3 english letters)?: ", i))
                .map(BowlingInputView::getStringInput)
                .collect(Collectors.toList());
    }

    private static void doBowls(Players players) {
        for (Player player : players.getContinuablePlayers()) {
            String message = String.format("%s's turn : ", player.getNameString());
            int score = BowlingInputView.getIntInput(message);

            scoreFrames.bowl(score);

            BowlingOutputView.printFramesStatus(player, scoreFrames);
        }
    }
}
