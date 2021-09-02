package bowling;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.pins.Pins;
import bowling.domain.player.BowlingPlayer;
import bowling.domain.player.BowlingPlayers;
import bowling.domain.player.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.List;

public class App {

    public static void main(String[] args) {

        int inputPlayerCount = InputView.getInputPlayerCount();
        List<String> inputPlayerNames = InputView.getInputPlayerNames(inputPlayerCount);
        BowlingPlayers bowlingPlayers = BowlingPlayers.of(inputPlayerNames);
        ResultView.printBoard(bowlingPlayers);

        while (!bowlingPlayers.isFinish()) {
            BowlingPlayer currentPlayer = bowlingPlayers.getCurrentPlayer();
            int pins = InputView.getInputPitch(currentPlayer.getPlayerName());
            bowlingPlayers.bowl(Pins.of(pins));
            ResultView.printBoard(bowlingPlayers);
        }
    }
}
