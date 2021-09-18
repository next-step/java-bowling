package bowling;

import bowling.view.BowlingInputView;
import bowling.view.BowlingOutputView;

import java.util.List;

public class BowlingMain {
    public static void main(String[] args) {
        int numberOfPlayers = BowlingInputView.getIntInput("How many people? ");
        List<String> playerNames =
                BowlingInputView.getStringsInputWithFormatString(numberOfPlayers, "플레이어 %d의 이름은(3 english letters)?: ");

        Players players = new Players(playerNames);

        BowlingOutputView.printPlayerFrames(players);

        while (players.isContinued()) {
            playersDoBowl(players);
        }
    }

    private static void playersDoBowl(Players players) {
        for (Player player : players) {
            doBowl(players, player);
        }
    }

    private static void doBowl(Players players, Player player) {
        boolean frameUpdated = false;

        while(!frameUpdated) {
            String message = String.format("%s's turn : ", player.getNameString());
            int score = BowlingInputView.getIntInput(message);
            frameUpdated = player.playBowl(score);

            BowlingOutputView.printPlayerFrames(players);
        }
    }
}
