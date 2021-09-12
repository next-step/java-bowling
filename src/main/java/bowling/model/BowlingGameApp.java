package bowling.model;

import bowling.model.player.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.ArrayList;
import java.util.List;

public class BowlingGameApp {
    public static final int FIRST_PLAYER_NUMBER = 1;

    public static void main(String[] args) {
        int playerCount = InputView.inputPlayerCount();

        List<Player> players = new ArrayList<>();
        for (int playerNumber = FIRST_PLAYER_NUMBER; playerNumber <= playerCount; playerNumber++) {
            Player player = new Player(playerNumber, InputView.inputPlayerName(playerNumber));
            players.add(player);
        }
        ResultView.printBoard(players);

        BowlingGames bowlingGames = new BowlingGames(players);
        while (bowlingGames.canPlayNext()) {
            int fallenPinCount = InputView.inputFallenPinCount(bowlingGames.currentPlayerName());
            bowlingGames.play(fallenPinCount);
            ResultView.printFrames(bowlingGames.games());
        }
    }
}
