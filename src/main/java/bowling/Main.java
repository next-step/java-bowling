package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.BowlingGames;
import bowling.domain.Pin;
import bowling.domain.Players;
import bowling.view.InputView;
import bowling.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        int numberOfPeople = InputView.inputNumberOfPeople();

        List<String> playersName = IntStream.range(0, numberOfPeople)
                .mapToObj(InputView::inputPlayerName)
                .collect(Collectors.toList());

        Players players = new Players(playersName);
        OutputView.printDefault(players);

        BowlingGames bowlingGames = new BowlingGames(players);

        while (!bowlingGames.isEndGame()) {
            BowlingGame currentGame = bowlingGames.getCurrentGame();
            currentGame.play(new Pin(InputView.inputFramePitches(currentGame.getPlayerName())));
            OutputView.printResult(bowlingGames);
        }
    }
}
