package bowling;

import bowling.domain.Player;
import bowling.domain.Players;
import bowling.view.BowlingConsoleInputView;
import bowling.view.BowlingConsoleOutputView;

import java.util.List;
import java.util.Scanner;

public class ApplicationRunner {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BowlingConsoleInputView inputView = new BowlingConsoleInputView(scanner);
        BowlingConsoleOutputView outputView = new BowlingConsoleOutputView();

        int playerNumber = inputView.enterPlayerNumber();
        List<String> names = inputView.enterPlayers(playerNumber);

        Players players = new Players(names);

        while (!players.isFinish()) {
            Player player = players.currentPlayer();
            String scoreText = inputView.enterScore(player.getName().value());
            players.roll(scoreText);
            outputView.print(players);
        }
    }
}
