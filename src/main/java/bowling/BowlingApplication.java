package bowling;

import bowling.game.BowlingGame;
import bowling.game.ScoreBoard;
import bowling.game.frame.Frame;
import bowling.game.frame.Frames;
import bowling.player.domain.Player;
import bowling.player.vo.Name;
import bowling.view.InputView;
import bowling.view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BowlingApplication {
    public static void main(String[] args) {
        int numberOfPlayer = InputView.inputNumberOfPlayer();
        List<String> names = InputView.inputNames(numberOfPlayer);

        List<Player> players = names.stream()
                .map(Player::new)
                .collect(Collectors.toList());

        BowlingGame bowlingGame = new BowlingGame(players);

        OutputView.printScoreBoard(bowlingGame.getPlayersFrames());

        while (!bowlingGame.isEndGame()) {
            Name name = bowlingGame.getCurrentPlayerName();
            int pinCount = InputView.inputPinCount(name);

            bowlingGame.bowlCurrentPlayer(pinCount);

            Map<Player, Frames> playerFrames = bowlingGame.getPlayersFrames();

            OutputView.printScoreBoard(playerFrames);
        }
    }
}
