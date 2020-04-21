package seul.bowling;

import seul.bowling.domain.Frame;
import seul.bowling.domain.Player;
import seul.bowling.domain.Players;
import seul.bowling.view.InputView;
import seul.bowling.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();

        int peopleNumber = Integer.parseInt(inputView.inputPeopleNumber());

        List<String> names = new ArrayList<>();
        for (int i = 0; i < peopleNumber; i++) {
            String playerName = inputView.inputPlayerName(i);
            names.add(playerName);
        }

        Players players = new Players(names);
        OutputView.printScoreBoard(players.getPlayers());

        viewScoreBoard(players, inputView);
    }


    public static void viewScoreBoard(Players players, InputView inputView) {
        boolean isEnd;
        do {
            isEnd = playGame(players, inputView);
        } while (!isEnd);
    }

    private static boolean playGame(Players players, InputView inputView) {
        boolean isEnd = true;
        for (Player player : players.getPlayers()) {
            boolean endGame = playBall(players, player.getName(), inputView);

            if (isEnd) {
                isEnd = endGame;
            }
        }

        return isEnd;
    }

    private static boolean playBall(Players players, String name, InputView inputView) {
        Frame frame;
        Player player;
        do {
            player = players.getPlayer(name);
            int clearPinCount = inputView.inputClearPin(name, player.getFrames());

            frame = players.play(name, clearPinCount);

            OutputView.printScoreBoard(players.getPlayers());
        } while (!frame.endFrame());

        return player.isEnd();
    }
}