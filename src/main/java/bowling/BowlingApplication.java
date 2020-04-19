package bowling;

import bowling.domain.Player;
import bowling.domain.bowlinggame.BowlingGames;
import bowling.domain.frame.Frame;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.List;
import java.util.stream.Collectors;

public class BowlingApplication {
    public static void main(String[] args) {
        BowlingGames bowlingGames = ready(InputView.inputPlayers());
        ResultView.printReadyToBowlingGame(bowlingGames);

        while (!bowlingGames.isOverAllGames()) {
            playBowlingGames(bowlingGames);
        }
    }

    private static BowlingGames ready(List<String> inputPlayers) {
        List<Player> players = inputPlayers.stream()
                .map(Player::new)
                .collect(Collectors.toList());
        return BowlingGames.of(players);
    }

    private static void playBowlingGames(BowlingGames bowlingGames) {
        for (int i = 0; i < bowlingGames.size(); i++) {
            bowlingGames.addNextFrame(i);
            playBowling(bowlingGames, i);
        }
    }

    private static void playBowling(BowlingGames bowlingGames, int index) {
        Frame frame = bowlingGames.getLastFrame(index);
        while (frame.isPlayable()) {
            frame.addScore(InputView.relaseBowling(bowlingGames.getPlayer(index)));
            ResultView.printBowlingScores(bowlingGames);
        }
    }
}
