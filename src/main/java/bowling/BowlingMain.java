package bowling;

import bowling.view.BowlingInputView;
import bowling.view.BowlingOutputView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BowlingMain {
    public static void main(String[] args) {
        int numberOfPlayers = BowlingInputView.getIntInput("How many people? ");

        List<String> playerNames =
                BowlingInputView.getStringsInput(numberOfPlayers, "플레이어 %d의 이름은(3 english letters)?: ");

        Players players = new Players(playerNames);

        BowlingOutputView.printPlayersFramesStatus(players);

        while (players.isContinued()) {
            doBowls(players);
        }
    }

    private static void doBowls(Players players) {
        for (Player player : players.getContinuablePlayers()) {
            String message = String.format("%s's turn : ", player.getNameString());
            int score = BowlingInputView.getIntInput(message);

            player.playBowl(score);

            BowlingOutputView.printPlayersFramesStatus(players);
        }
    }
}
